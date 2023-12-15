package main

open class MenuType( name:String, desc:String) : Menu() {
    var name:String
    var desc:String
    init{
        this.name = name
        this.desc = desc
    }
    override fun showInfo(){

    }
}
