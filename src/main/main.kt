package main

import kotlin.random.Random

fun main() {
    var menu = Menu()
    var myCart = Cart()
    val input = GetInput()
    myCart.money = Random.nextInt(10000,100000)
    var lists = ShowList()

    var shopping = true
    while(shopping) // selectType
    {
        lists.showTypeList(menu.types)

        var t = input.inputNum(menu.types.size +2 ) -1 // order, cancel
        when(t){
            // Type 안에 선택을 한 경우
            in 0 until menu.types.size-> {
                lists.showItemList(menu.types[t])
                var i = input.inputNum(menu.types[t].items.size+1) - 1
                when(i){
                    in 0 until menu.types[t].items.size->{
                        println("\n[${menu.types[t].items[i].name}]를 장바구니에 넣었습니다.\n")
                        myCart.addItem(menu.types[t].items[i])
                    }
                    else -> println("\n이전 화면으로 돌아갑니다.\n")
                }
            }

            // order
            menu.types.size -> {
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
                            var result = myCart.payment()
                            if (result == 1)
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
                                    println("\n${dist[c].name}(은)는 취소되었습니다.\n")
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
            menu.types.size+1 -> {
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