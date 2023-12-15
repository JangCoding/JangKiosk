package main

// 각 메뉴 이름 / 가격 / 설명
class MenuItem(name:String, val price:Int, var ea:Int, desc:String) : Menu(name, desc){
    init{
        this.name = name
        this.desc = desc
    }

    override fun showInfo() {

    }
}