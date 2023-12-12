package main

class Cart {
    var money:Int=0
    var total:Int=0
    var items: ArrayList<MenuItem> = arrayListOf()

    fun addItem(item:MenuItem )
    {
        items += item
    }

    fun showCart()
    {
        for(i in items.toSet()){ // .toSet() 중복되지 않는 리스트로 변경해줌
            var name = i.name
            var counts = items.count{it.name==i.name}
            var total = i.price * counts
            println("%-10s | %3d 개 | %10d원".format(name, counts, total))
        }
    }
}