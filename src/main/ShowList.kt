package main

class ShowList {

    //리스트 출력
    fun showTypeList(items:List<MenuType>){
        for((i,value) in items.withIndex()) {
            println("${i+1}. ${value.name}")
        }
        println("${items.size+1}. 주문하기")
        println("${items.size+2}. 달아나기")
    }

    fun showItemList(items:List<MenuItem>){
        for((i,value) in items.withIndex()) {
            println("${i+1}. ${"%-10s".format(value.name)} | W ${"%5d".format(value.price)} | ${value.description}")
        }
        println("${items.size+1}. 뒤로가기")
    }


    //리스트 추가 , 제거 , 수정 등

}