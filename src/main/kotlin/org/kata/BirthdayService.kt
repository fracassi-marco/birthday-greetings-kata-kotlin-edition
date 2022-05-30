package org.kata

import org.kata.Messages.Companion.happyBirthdayTo

class BirthdayService(private val notifier: Notifier, private val employeeRepository: EmployeeRepository) {

    fun sendGreetings(xDate: XDate) {
        employeeRepository.all().forEach {
            if (it.isBirthday(xDate)) {
                notifier.send(happyBirthdayTo(it), it.email)
            }
        }
    }
}

