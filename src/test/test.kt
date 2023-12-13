package test

//클래스
data class MenuItem(val name:String,val price:Int,val description:String){
}

fun init() // 함수
{
    var drinks =
        listOf(
            MenuItem("아이스아메리카노", 5000, "얼죽아"),
            MenuItem("아이스아메리카노", 5000, "얼죽아"),
            MenuItem("아이스아메리카노", 5000, "얼죽아"),
            MenuItem("아이스아메리카노", 5000, "얼죽아")
        )
    var burgers =
        listOf(
            MenuItem("아이스아메리카노", 5000, "얼죽아"),
            MenuItem("아이스아메리카노", 5000, "얼죽아"),
            MenuItem("아이스아메리카노", 5000, "얼죽아"),
            MenuItem("아이스아메리카노", 5000, "얼죽아")
        )
    var beers =
        listOf(
            MenuItem("아이스아메리카노", 5000, "얼죽아"),
            MenuItem("아이스아메리카노", 5000, "얼죽아"),
            MenuItem("아이스아메리카노", 5000, "얼죽아"),
            MenuItem("아이스아메리카노", 5000, "얼죽아")
        )
}

fun main() {
    init() // 함수
}