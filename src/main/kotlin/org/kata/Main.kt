package org.kata

fun main() {
    BirthdayService(
        EmailNotifier("localhost", 25),
        FileEmployeeRepository("employee_data.txt")
    ).sendGreetings(XDate())
}