package org.kata

class Messages {
    companion object {
        fun happyBirthdayTo(employee: Employee): Message {
            val body = "Happy Birthday, dear ${employee.firstName}!"
            val subject = "Happy Birthday!"
            return Message(subject, body)
        }
    }
}