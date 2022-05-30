package org.kata

import org.kata.Messages.Companion.happyBirthdayTo
import java.io.BufferedReader
import java.io.FileReader

class BirthdayService(private val notifier: Notifier) {

    fun sendGreetings(fileName: String, xDate: XDate) {
        val `in` = BufferedReader(FileReader(fileName))
        `in`.readLines().drop(1).forEach { str ->
            val employeeData = str.split(", ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val employee = Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3])
            if (employee.isBirthday(xDate)) {
                notifier.send(happyBirthdayTo(employee), employee.email)
            }
        }
    }
}

