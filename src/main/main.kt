package main

import java.time.LocalDateTime

import kotlin.random.Random

const val BANK_HOUR: Int = 14
const val BANK_MIN_START: Int = 40
const val BANK_MIN_END: Int = 42

// MenuComponent > Menutype > MenuItem
// name, descript > type     > price

fun typeInit():List<MenuType>{
    var t_list:List<MenuType> = listOf(
        MenuType("한 송이 꽃", "한 송이 만으로도 마음을 전달하는 데는 충분합니다."),
        MenuType("꽃 다발", "그러면 꽃 다발은 어떻겠어요?"),
        MenuType("다육이", "귀여운 다육이")
    )
    return t_list
}
fun itemInit(t_name: String, t_desc: String): List<MenuItem> {
    var i_list: List<MenuItem> = listOf()
    when(t_name) {
        "한 송이 꽃" -> {
            i_list = listOf(
                MenuItem(t_name, t_desc, "장미", 5000, "사랑, 아름다움, 존경"),
                MenuItem(t_name, t_desc, "해바라기", 3000, "희망, 햇살, 긍정"),
                MenuItem(t_name, t_desc, "튤립", 6000, "기쁨, 행복, 사랑의 고백"),
                MenuItem(t_name, t_desc, "백합", 7000, "순수, 우아함, 감사"),
                MenuItem(t_name, t_desc, "안개꽃", 4500, "신비로움, 아름다움, 그리움")
            )
        }
        "꽃 다발" -> {
            i_list = listOf(
                MenuItem(t_name, t_desc, "로즈 부케", 12000, "사랑의 메시지를 전하며"),
                MenuItem(t_name, t_desc, "선물용 튤립 부케", 15000, "특별한 날에 어울리는 부케"),
                MenuItem(t_name, t_desc, "색상감 넘치는 꽃다발", 18000, "다양한 꽃으로 만든 아름다운 부케"),
                MenuItem(t_name, t_desc, "프리저브드 플라워 부케", 25000, "오래도록 싱그러운 꽃 향을 즐길 수 있는 부케"),
                MenuItem(t_name, t_desc, "로맨틱한 꽃과 초콜릿 세트", 30000, "로즈와 고급 초콜릿이 어우러진 세련된 부케"),
                MenuItem(t_name, t_desc, "특별한 날을 위한 프리미엄 부케", 50000, "특별한 순간을 만들어 드리는 고급 부케")
            )
        }
        "다육이" -> {
            i_list = listOf(
                MenuItem(t_name, t_desc, "선인장", 8000, "마음의 평화와 안정을 주는 실내 식물"),
                MenuItem(t_name, t_desc, "다육이 세트", 12000, "다양한 다육이가 함께 모여 있는 다육이 세트"),
                MenuItem(t_name, t_desc, "애기 선인장", 5000, "작고 귀여운 애기 선인장"),
                MenuItem(t_name, t_desc, "다육이 케이크", 18000, "화려한 다육이로 만든 케이크 모양의 다육이")
            )
        }
    }

    return i_list
}

fun timeCheck():Boolean{
    var nowTime = LocalDateTime.now()
    if(nowTime.hour == BANK_HOUR && nowTime.minute in BANK_MIN_START..BANK_MIN_END)
        return false
    return true
}


fun main() {
    var typeList:List<MenuType> = listOf()
    var itemList:MutableList<List<MenuItem>> = mutableListOf()


    //각 메뉴 초기화
    typeList = typeInit()
    for(t in typeList){
        itemList += itemInit(t.t_name, t.t_desc) // 이중리스트 itemList[0].i_name : 장미 ...
    }



    var myCart = Cart(Random.nextInt(10000,100000))
    val input = GetInput()
    var lists = ShowList()


    var shopping = true
    while(shopping) // selectType
    {
        lists.showTypeList(typeList)

        //메뉴 입력받기
        var t = input.inputNum(typeList.size +2 ) -1 // order, cancel
        when(t){
            // Type 안에 선택을 한 경우
            in 0 until typeList.size-> {

                lists.showItemList(itemList[t])

                //아이템 입력받기
                var i = input.inputNum(itemList[t].size+1) - 1
                when(i){
                    in 0 until itemList[t].size->{
                        println("\n[${itemList[t][i].i_name}]를 장바구니에 넣었습니다.\n")
                        myCart.addItem(itemList[t][i])
                    }
                    else -> println("\n이전 화면으로 돌아갑니다.\n")
                }
            }

            // order
            typeList.size -> {
                if(myCart.items.size < 1 ){
                    println("\n장바구니가 비어있습니다.\n")
                }
                else {
                    myCart.showCart()
                    println("\n이대로 결제를 진행해드릴까요 ?")
                    println("1. 주문하기")
                    println("2. 주문취소")
                    println("3. 돌아가기")
                    var o = input.inputNum(3)
                    when (o) {
                        // 주문
                        1 -> {
                            if(timeCheck()==false)
                            {
                                println("현재 시각은 오후${LocalDateTime.now().hour}시 ${LocalDateTime.now().minute}분입니다. \n" +
                                        "은행 점검 시간은 오후${BANK_HOUR}시 ${BANK_MIN_START}분 ~ 오후 ${BANK_HOUR}시 ${BANK_MIN_END}분이므로 결제할 수 없습니다.")
                            }
                            else if ( myCart.payment())
                                myCart.items = arrayListOf()
                        }
                        // 주문취소
                        2 -> {
                            myCart.showCart()
                            println("\n[취소하기]")
                            var dist = myCart.items.toSet().toList() // 중복 제거 후 리스트화
                            var c = input.inputNum(myCart.items.size) - 1
                            when (c) {
                                in 0 until dist.size -> {
                                    println("\n${dist[c].i_name}(은)는 취소되었습니다.\n")
                                    myCart.delItem(dist[c])
                                }
                                else -> println("\n이전 화면으로 돌아갑니다.\n")
                            }
                        }

                        // 돌아가기
                        3 -> println( "\n이전 화면으로 돌아갑니다.\n" )
                    }
                }
            } // end order

            // cancel
            typeList.size+1 -> {
                if(myCart.items.size <1) {
                    println("\n다음에 또 오세요 !\n")
                    shopping = false
                }
                else {
                    println("\n장바구니에 물건이 남아있습니다. 정말 종료할까요?")
                    println("1. 예")
                    println("2. 아니오")
                    var e = input.inputNum(2)
                    when(e){
                        1 ->{
                            println("\n다음에 또 오세요 !\n")
                            shopping = false
                        }
                    }
                }
            }
        } // when(t)
    } // while(shopping)
} // main