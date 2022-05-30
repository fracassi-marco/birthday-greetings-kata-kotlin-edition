package org.kata

fun main() {
    val service = BirthdayService(EmailNotifier("localhost", 25))
    service.sendGreetings("employee_data.txt", XDate())
}