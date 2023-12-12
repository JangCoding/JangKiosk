package main

class GetInput {
    fun inputNum(max:Int):Int{
        var n:Int=0
        while(true) {
            println("숫자 입력 : ")
            try {
                n = readLine()!!.toInt()
            } catch (e: Exception) {
                println("잘못된 형식입니다. 다시 입력해주세요. (${max})")
                continue
            }
            if( n in 1..max)
                break
            else{
                println("입력 범위를 초과하셨습니다. 다시 입력해주세요. (1~${max})")
                continue
            }
        }
        return n
    }
}