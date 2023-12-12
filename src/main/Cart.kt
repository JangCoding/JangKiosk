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
        total = 0
        for(i in items.toSet()){ // .toSet() 중복되지 않는 리스트로 변경해줌
            var name = i.name
            var counts = items.count{it.name==i.name}
            var t = i.price * counts
            total += t
            println("%-10s | %3d 개 | %7d원".format(name, counts, t))
        }
    }

    fun payment():Int{
        var result = 0
        println("총 %7d원 이다 !!!!".format(total))
        if(money < total ) {
            println("잔액이 ${total - money}원 부족하다 !!!!")
        }
        else{
            println("거래가 성사되었다 !!!!")
            money -= total
            println("잔액 : ${money}")
            result = 1
        }
        return result
    }
}