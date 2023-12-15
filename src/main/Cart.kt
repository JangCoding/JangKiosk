package main

class Cart(var _money:Int) {
    var money:Int=0
    var total:Int=0
    var items:ArrayList<MenuItem> = arrayListOf()
    init{
        money=_money
    }

    fun addItem(item:MenuItem )  {
        items += item
    }

    fun delItem(item:MenuItem) {
        items.remove(item)
    }

    fun showCart()  {
        total = 0
        println("-".repeat(44))
        println("\n[ 내 장바구니 ]")
        for((idx,i) in items.toSet().withIndex() ){ // .toSet() 중복되지 않는 리스트로 변경해줌
            var name = i.name
            var counts = items.count{it.name==i.name}
            var t = i.price * counts
            total += t
            println("%d. %-10s | %3d 개 | %7d원".format(idx+1, name, counts, t))
        }
        println("\n총 [%7d원] 입니다.".format(total))
    }

    fun payment():Boolean{
        var result = false
        if(money < total ) {
            println("잔액이 [${total - money}원] 부족합니다.\n")
        }
        else{
            money -= total
            println("[잔액 : ${money}원]\n")
            result = true
        }
        return result
    }
}