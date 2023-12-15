package main

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import kotlin.random.Random

//쓰레드
import kotlinx.coroutines.*
import java.util.*
import kotlin.concurrent.thread


const val BANK_HOUR: Int = 11
const val BANK_MIN_START: Int = 10
const val BANK_MIN_END: Int = 20


var typeList: List<MenuType> = listOf()
var itemList: MutableList<List<MenuItem>> = mutableListOf()

var myCart = Cart(Random.nextInt(20000, 100000))

var wait: Int = 0


fun inputNum(max:Int):Int{
    var n:Int=0
    while(true) {
        print("원하시는 항목을 숫자로 입력해주세요 : ")
        try {
            n = readLine()!!.toInt()
        } catch (e: Exception) {
            println("잘못된 입력입니다. 다시 입력해주세요. (1~${max})")
            continue
        }

        if( n in 1..max)
            break
        else{
            println("입력 범위를 초과하였습니다. 다시 입력해주세요. (1~${max})")
            continue
        }
    }
    return n
}

fun showTypeList(items:List<MenuType>){
    println("-".repeat(44))
    println("[THE SPARTAN GARDEN]")
    for((i,value) in items.withIndex()) {
        println("${i+1}. ${"%-10s".format(value.name)}| ${value.desc}")
    }
    println("\n[Order Menu]")
    println("${items.size+1}. 주문하기")
    println("${items.size+2}. 종료하기")
}

fun showItemList(items:List<MenuItem>){
    println("-".repeat(44))
    println("\n[ ${items[0].name} 메뉴 ]")
    for((i,value) in items.withIndex()) {
        println("${i+1}. ${"%-20s".format(value.name)} | W ${"%5d".format(value.price)} | 재고 : ${"%3d".format(value.ea)} | ${value.name}")
    }
    println("${items.size+1}. 뒤로가기")
}

fun typeInit(): List<MenuType> {
    var t_list: List<MenuType> = listOf(
        MenuType("한 송이 꽃", "꽃 한 송이로 전하는 진심"),
        MenuType("꽃 다발", "꽃 여러 송이로 전하는 더 큰 진심"),
        MenuType("다육이", "귀여운 다육이")
    )
    return t_list
}

fun itemInit(t_name: String, t_desc: String): List<MenuItem> {
    var i_list: List<MenuItem> = listOf()
    when (t_name) {
        "한 송이 꽃" -> {
            i_list = listOf(
                MenuItem("장미", 5000, 3, "사랑, 아름다움, 존경"),
                MenuItem("해바라기", 3000, 5, "희망, 햇살, 긍정"),
                MenuItem( "튤립", 6000, 1, "기쁨, 행복, 사랑의 고백"),
                MenuItem( "백합", 7000, 2, "순수, 우아함, 감사"),
                MenuItem( "안개꽃", 4500, 5, "신비로움, 아름다움, 그리움")
            )
        }

        "꽃 다발" -> {
            i_list = listOf(
                MenuItem("로즈 부케", 12000, 2, "사랑의 메시지를 전하며"),
                MenuItem("선물용 부케", 15000, 3, "특별한 날에 어울리는 부케"),
                MenuItem("계절 꽃 부케", 18000, 1, "다양한 꽃으로 만든 아름다운 부케"),
                MenuItem("프리저브드 플라워 부케", 25000, 5, "오래도록 싱그러운 꽃 향을 즐길 수 있는 부케"),
                MenuItem("로맨틱한 꽃과 초콜릿 세트", 30000, 6, "로즈와 고급 초콜릿이 어우러진 세련된 부케"),
                MenuItem("특별한 날을 위한 프리미엄 부케", 50000, 4, "특별한 순간을 만들어 드리는 고급 부케")
            )
        }

        "다육이" -> {
            i_list = listOf(
                MenuItem("선인장", 8000, 5, "마음의 평화와 안정을 주는 실내 식물"),
                MenuItem("다육이 세트", 12000, 2, "다양한 다육이가 함께 모여 있는 다육이 세트"),
                MenuItem("애기 선인장", 5000, 16, "작고 귀여운 애기 선인장"),
                MenuItem("다육이 케이크", 18000, 3, "화려한 다육이로 만든 케이크 모양의 다육이")
            )
        }
    }

    return i_list
}

fun selectItem(t: Int) {
    showItemList(itemList[t])

    //아이템 입력받기
    var i = inputNum(itemList[t].size + 1) - 1
    when (i) {
        in 0 until itemList[t].size -> {
            if (itemList[t][i].ea > 0) {
                itemList[t][i].ea -= 1
                println("\n[${itemList[t][i].name}]를 장바구니에 넣었습니다.\n")
                myCart.addItem(itemList[t][i])
            } else
                println("죄송합니다. 재고가 부족합니다.")
        }

        else -> println("\n이전 화면으로 돌아갑니다.\n")
    }

}

fun timeCheck(): Boolean {
    var nowTime = LocalDateTime.now()
    if (nowTime.hour == BANK_HOUR && nowTime.minute in BANK_MIN_START..BANK_MIN_END)
        return false
    return true
}

fun timeFormater(time: LocalDateTime): String {

    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    // 포맷에 맞게 출력
    val formattedTime: String = time.format(formatter)

    return formattedTime
}

fun confirmOrder() {
    if (myCart.items.size < 1) {
        println("\n장바구니가 비어있습니다.\n")
    } else {
        myCart.showCart()
        println("\n이대로 결제를 진행해드릴까요 ? ( 현재 주문 대기 수 : ${wait} )")
        println("1. 주문하기")
        println("2. 주문취소")
        println("3. 돌아가기")
        var o = inputNum(3)
        when (o) {
            // 주문
            1 -> {
                if (timeCheck() == false) {
                    println(
                        "현재 시각은 오후${LocalDateTime.now().hour}시 ${LocalDateTime.now().minute}분입니다. \n" +
                                "은행 점검 시간은 오후${BANK_HOUR}시 ${BANK_MIN_START}분 ~ 오후 ${BANK_HOUR}시 ${BANK_MIN_END}분이므로 결제할 수 없습니다."
                    )
                } else if (myCart.payment()) {
                    myCart.items = arrayListOf()
                    wait += 1
                    println("\n주문이 완료되었습니다. (${timeFormater(LocalDateTime.now())})\n")
                }
            }
            // 주문취소
            2 -> {
                myCart.showCart()
                println("\n[취소하기]")
                var dist = myCart.items.toSet().toList() // 중복 제거 후 리스트화
                var c = inputNum(myCart.items.size) - 1
                when (c) {
                    in 0 until dist.size -> {
                        println("\n${dist[c].name}(은)는 취소되었습니다.\n")
                        myCart.delItem(dist[c])
                    }

                    else -> println("\n이전 화면으로 돌아갑니다.\n")
                }
            }

            // 돌아가기
            3 -> println("\n이전 화면으로 돌아갑니다.\n")
        }
    }
}

fun confirmExit(_shopping: Boolean): Boolean {
    var shopping = _shopping
    if (myCart.items.size < 1) {
        println("\n다음에 또 오세요 !\n")
        shopping = false
    } else {
        println("\n장바구니에 물건이 남아있습니다. 정말 종료할까요?")
        println("1. 예")
        println("2. 아니오")
        var e = inputNum(2)
        when (e) {
            1 -> {
                println("\n다음에 또 오세요 !\n")
                shopping = false
            }
        }
    }
    return shopping
}

fun showWaits() {
    var timer = Timer()
    timer.schedule(object : TimerTask() {
        override fun run() {
            println("\n 현재 주문 대기수: ${wait}")
        }
    }, 0, 1000)
}


fun main() {
    //각 메뉴 초기화
    typeList = typeInit()
    for (t in typeList) {
        itemList += itemInit(t.name, t.desc) // 이중리스트 itemList[0].i_name : 장미 ...
    }

    showWaits()

    var shopping = true

    runBlocking {       // 코루틴의 스코프 만들기

        while (shopping) // selectType
        {
            var job = launch {     // 코루틴 내용
                showTypeList(typeList)

                //메뉴 입력받기
                var t = inputNum(typeList.size + 2) - 1 // order, cancel
                when (t) {
                    // Type 안에 선택을 한 경우
                    in 0 until typeList.size -> selectItem(t)

                    // order
                    typeList.size -> confirmOrder()

                    // cancel
                    typeList.size + 1 -> shopping = confirmExit(shopping)
                } // when(t)
            } // launch

            job.join()
            for (i in 3 downTo 1) {
                println("[$i]초 후 메인 화면으로 돌아갑니다.")
                delay(100)
            }
        } // while(shopping)
    } //RunBlocking
} // main