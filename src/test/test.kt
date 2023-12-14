package test

//클래스import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import kotlin.random.Random

import kotlinx.coroutines.*
import kotlin.concurrent.thread

fun main() {
    runBlocking {
        while (true) {
            var job = launch {
                println("hi")
                var a=readLine()
            }

            job.join()

            for (i in 3 downTo 1) {
                println("[$i]초")
                delay(1000)
            }
        }
    }
}