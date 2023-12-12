package main

class Cart {
    var money:Int=0
    var total:Int=0
    var items: ArrayList<MenuItem> = arrayListOf()

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

    fun payment():Int{
        var result = 0
        if(money < total ) {
            println("잔액이 [${total - money}원] 부족합니다.\n")
        }
        else{
            println("\n주문이 완료되었습니다.\n")
            money -= total
            println("[잔액 : ${money}]\n")
            result = 1
        }
        return result
    }
}