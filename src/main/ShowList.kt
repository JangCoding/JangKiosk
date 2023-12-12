package main

class ShowList {

    //리스트 출력
    fun showTypeList(items:List<MenuType>){
        println("[THE SPARTAN GARDEN]")
        println("원하시는 항목을 골라주세요.\n")
        for((i,value) in items.withIndex()) {
            println("${i+1}. ${value.name}")
        }
        println("${items.size+1}. 주문하기")
        println("${items.size+2}. 종료하기")
    }

    fun showItemList(type:MenuType){
        println("\n[ ${type.name} 메뉴 ]")
        for((i,value) in type.items.withIndex()) {
            println("${i+1}. ${"%-10s".format(value.name)} | W ${"%5d".format(value.price)} | ${value.description}")
        }
        println("${type.items.size+1}. 뒤로가기")
    }


    //리스트 추가 , 제거 , 수정 등

}