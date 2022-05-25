package org.kata

import com.dumbster.smtp.SimpleSmtpServer
import com.dumbster.smtp.SmtpMessage
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class AcceptanceTest {
    private val birthdayService: BirthdayService = BirthdayService()
    private lateinit var mailServer: SimpleSmtpServer

    @Before
    fun setUp() {
        mailServer = SimpleSmtpServer.start(NONSTANDARD_PORT)
    }

    @After
    fun tearDown() {
        mailServer.stop()
        Thread.sleep(200)
    }

    @Test
    fun willSendGreetings_whenItsSomebodysBirthday() {
        birthdayService.sendGreetings("employee_data.txt", XDate("2008/10/08"), "localhost", NONSTANDARD_PORT)
        assertThat(mailServer.getReceivedEmailSize()).isEqualTo(1)
        val message: SmtpMessage = mailServer.getReceivedEmail().next() as SmtpMessage
        assertThat(message.getBody()).isEqualTo("Happy Birthday, dear John!")
        assertThat(message.getHeaderValue("Subject")).isEqualTo("Happy Birthday!")
        val recipients: Array<String> = message.getHeaderValues("To")
        assertThat(recipients.size).isEqualTo(1)
        assertThat(recipients[0]).isEqualTo("john.doe@foobar.com")
    }

    @Test
    fun willNotSendEmailsWhenNobodysBirthday() {
        birthdayService.sendGreetings("employee_data.txt", XDate("2008/01/01"), "localhost", NONSTANDARD_PORT)
        assertThat(mailServer.receivedEmailSize).isEqualTo(0)
    }

    companion object {
        private const val NONSTANDARD_PORT = 9999
    }
}