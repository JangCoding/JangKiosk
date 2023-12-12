package main

class EditList {

    //리스트 출력
    fun showTypeList(items:List<MenuType>){
        for((i,value) in items.withIndex()) {
            println("${i+1}. ${value.name}")
        }
    }

    fun showItemList(items:List<MenuItem>){
        for((i,value) in items.withIndex()) {
            println("${i+1}. ${"%-10s".format(value.name)} | W ${"%5d".format(value.price)} | ${value.description}")
        }
    }


    //리스트 추가 , 제거 , 수정 등

}