package main

class ShowList {

    //리스트 출력
    fun showTypeList(items:List<Menu>){
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
            println("${i+1}. ${"%-20s".format(value.name)} | W ${"%5d".format(value.price)} | 재고 : ${"%3d".format(value.ea)} | ${value.desc}")
        }
        println("${items.size+1}. 뒤로가기")
    }


    //리스트 추가 , 제거 , 수정 등

}