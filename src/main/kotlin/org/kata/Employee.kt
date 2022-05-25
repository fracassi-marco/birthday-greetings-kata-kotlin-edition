package org.kata

data class Employee(
    val firstName: String,
    private val lastName: String,
    private val birthDateString: String,
    val email: String
) {

    private val birthDate: XDate = XDate(birthDateString)

    fun isBirthday(today: XDate): Boolean {
        return today.isSameDay(birthDate)
    }

    override fun toString(): String {
        return "Employee $firstName $lastName <$email> born $birthDate"
    }
}