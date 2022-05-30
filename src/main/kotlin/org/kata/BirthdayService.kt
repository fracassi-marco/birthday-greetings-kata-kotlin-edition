package org.kata

import org.kata.Messages.Companion.happyBirthdayTo

class BirthdayService(private val notifier: Notifier, private val employeeRepository: EmployeeRepository) {

    fun sendGreetings(xDate: XDate) {
        employeeRepository.all()
            .filter { it.isBirthday(xDate) }
            .forEach { notifier.send(happyBirthdayTo(it), it.email) }
    }
}

