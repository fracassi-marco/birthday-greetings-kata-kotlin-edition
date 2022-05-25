package org.kata

fun main() {
    val service = BirthdayService()
    service.sendGreetings("employee_data.txt", XDate(), "localhost", 25)
}