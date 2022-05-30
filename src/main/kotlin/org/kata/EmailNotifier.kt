package org.kata

import java.util.*
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class EmailNotifier(private val smtpHost: String, private val smtpPort: Int) : Notifier {

    override fun send(message: Message, recipient: String) {
        val props = Properties()
        props["mail.smtp.host"] = smtpHost
        props["mail.smtp.port"] = smtpPort.toString()
        val session: Session = Session.getInstance(props, null)

        val msg = MimeMessage(session)
        msg.setFrom(InternetAddress("sender@here.com"))
        msg.setRecipient(javax.mail.Message.RecipientType.TO, InternetAddress(recipient))
        msg.subject = message.subject
        msg.setText(message.body)

        Transport.send(msg)
    }
}