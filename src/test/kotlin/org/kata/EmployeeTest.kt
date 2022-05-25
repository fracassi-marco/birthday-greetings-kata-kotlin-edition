package org.kata

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class EmployeeTest {

    @Test
    fun testBirthday() {
        val employee = Employee("foo", "bar", "1990/01/31", "a@b.c")
        assertThat(employee.isBirthday(XDate("2008/01/30"))).isFalse
        assertThat(employee.isBirthday(XDate("2008/01/31"))).isTrue
    }

    @Test
    fun equality() {
        val base = Employee("First", "Last", "1999/09/01", "first@last.com")
        val same = Employee("First", "Last", "1999/09/01", "first@last.com")
        val different = Employee("First", "Last", "1999/09/01", "boom@boom.com")
        assertThat(base.equals(null)).isFalse
        assertThat(base.equals("")).isFalse
        assertThat(base == same).isTrue
        assertThat(base == different).isFalse
    }
}