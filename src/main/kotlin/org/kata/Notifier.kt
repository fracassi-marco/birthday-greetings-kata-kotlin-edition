package org.kata

interface Notifier {
    fun send(message: Message, recipient: String)
}