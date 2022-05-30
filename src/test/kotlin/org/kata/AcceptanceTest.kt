package org.kata

import com.dumbster.smtp.SimpleSmtpServer
import com.dumbster.smtp.SmtpMessage
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AcceptanceTest {
    private val birthdayService: BirthdayService = BirthdayService(
        EmailNotifier("localhost", NONSTANDARD_PORT),
        FileEmployeeRepository("employee_data.txt")
    )
    private lateinit var mailServer: SimpleSmtpServer

    @BeforeEach
    fun setUp() {
        mailServer = SimpleSmtpServer.start(NONSTANDARD_PORT)
    }

    @AfterEach
    fun tearDown() {
        mailServer.stop()
        Thread.sleep(200)
    }

    @Test
    fun willSendGreetings_whenItsSomebodysBirthday() {
        birthdayService.sendGreetings(XDate("2008/10/08"))
        assertThat(mailServer.receivedEmailSize).isEqualTo(1)
        val message = mailServer.receivedEmail.next() as SmtpMessage
        assertThat(message.body).isEqualTo("Happy Birthday, dear John!")
        assertThat(message.getHeaderValue("Subject")).isEqualTo("Happy Birthday!")
        val recipients = message.getHeaderValues("To")
        assertThat(recipients.size).isEqualTo(1)
        assertThat(recipients[0]).isEqualTo("john.doe@foobar.com")
    }

    @Test
    fun willNotSendEmailsWhenNobodysBirthday() {
        birthdayService.sendGreetings(XDate("2008/01/01"))
        assertThat(mailServer.receivedEmailSize).isEqualTo(0)
    }

    companion object {
        private const val NONSTANDARD_PORT = 9999
    }
}