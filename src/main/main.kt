package main

import kotlin.random.Random

fun main() {
    var menu = Menu()
    var myCart = Cart()
    val input = GetInput()
    myCart.money = Random.nextInt(10000,100000)
    var lists = ShowList()
//    lists.showTypeList(menu.types)
//    lists.showItemList(menu.single.items)
//    println("내 소지금 ${myCart.money}")

    println("[THE SPARTAN GARDEN] 에 당도한 것을 환영한다 !!!!")
    println("아래의 메뉴판을 보고 원하는 바를 고하라 !!!!")

    var shopping = true
    while(shopping) // selectType
    {
        println("[Spartan Garden]")
        lists.showTypeList(menu.types)
        var t = input.inputNum(menu.types.size +2 ) -1 // order, cancel
        when(t){
            // Type 안에 선택을 한 경우
            in 0 until menu.types.size-> {
                println("[ ${menu.types[t].name} 메뉴 ]")
                lists.showItemList(menu.types[t].items)
                var i = input.inputNum(menu.types[t].items.size+1) - 1
                when(i){
                    in 0 until menu.types[t].items.size->{
                        println("좋은 선택이다 !!!!")
                        myCart.addItem(menu.types[t].items[i])
                    }
                    else -> println("신중히 살펴보아라 !!!!")
                }
            }

            // order
            menu.types.size -> {
                myCart.showCart()
                println("이대로 결제를 진행해드릴까요 ?")
                println("1. 주문하기")
                println("2. 주문취소")
                println("3. 돌아가기")
                var o = input.inputNum(2)
                when(o) {
                    // 주문
                    1 -> {
                        var result = myCart.payment()
                        if(result == 1)
                            myCart.items = arrayListOf()
                    }
                    // 취소
                    2 -> println("신중히 살펴보아라 !!!!")
                }
            }

            // cancel
            menu.types.size+1 -> {
                println("다음에 또 오도록 !!!!")
                shopping=false
            }
        } // when(t)
    } // while(shopping)
} // main