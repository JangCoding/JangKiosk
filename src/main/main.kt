package main

import kotlin.random.Random

fun main() {
    var menu = Menu()
    var myCart = Cart()
    val input = GetInput()
    myCart.money = Random.nextInt(10000,100000)
    var e = EditList()
    e.showTypeList(menu.types)
    e.showItemList(menu.single.items)
    println("내 소지금 ${myCart.money}")

    while(true)
    {
        e.showItemList(menu.single.items)
        var i = input.inputNum(menu.single.items.size)
        myCart.addItem(menu.single.items[i-1])
        myCart.showCart()
    }


}