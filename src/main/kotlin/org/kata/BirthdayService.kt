package org.kata

import java.io.BufferedReader
import java.io.FileReader
import java.util.*
import javax.mail.Message
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class BirthdayService {

    fun sendGreetings(fileName: String, xDate: XDate, smtpHost: String, smtpPort: Int) {
        val `in` = BufferedReader(FileReader(fileName))
        `in`.readLines().drop(1).forEach { str ->
            val employeeData = str.split(", ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val employee = Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3])
            if (employee.isBirthday(xDate)) {
                val recipient: String = employee.email
                val body = "Happy Birthday, dear ${employee.firstName}!"
                val subject = "Happy Birthday!"
                sendMessage(smtpHost, smtpPort, "sender@here.com", subject, body, recipient)
            }
        }
    }

    private fun sendMessage(
        smtpHost: String,
        smtpPort: Int,
        sender: String,
        subject: String,
        body: String,
        recipient: String
    ) {
        // Create a mail session
        val props = Properties()
        props["mail.smtp.host"] = smtpHost
        props["mail.smtp.port"] = "" + smtpPort
        val session: Session = Session.getInstance(props, null)

        // Construct the message
        val msg: Message = MimeMessage(session)
        msg.setFrom(InternetAddress(sender))
        msg.setRecipient(Message.RecipientType.TO, InternetAddress(recipient))
        msg.setSubject(subject)
        msg.setText(body)

        // Send the message
        Transport.send(msg)
    }
}