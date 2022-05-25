package org.kata

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class XDateTest {

    @Test
    fun getters() {
        val date = XDate("1789/01/24")
        assertThat(date.month).isEqualTo(1)
        assertThat(date.day).isEqualTo(24)
    }

    @Test
    fun isSameDate() {
        val date = XDate("1789/01/24")
        val sameDay = XDate("2001/01/24")
        val notSameDay = XDate("1789/01/25")
        val notSameMonth = XDate("1789/02/25")
        assertThat(date.isSameDay(sameDay)).isTrue
        assertThat(date.isSameDay(notSameDay))
        assertThat(date.isSameDay(notSameMonth))
    }

    @Test
    fun equality() {
        val base = XDate("2000/01/02")
        val same = XDate("2000/01/02")
        val different = XDate("2000/01/04")
        assertThat(base.equals(null)).isFalse
        assertThat(base.equals("")).isFalse
        assertThat(base.equals(base)).isTrue
        assertThat(base.equals(same)).isTrue
        assertThat(base.equals(different)).isFalse
    }
}