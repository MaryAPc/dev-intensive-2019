package ru.skillbranch.devintensive.models

import java.util.*

abstract class BaseMessage(
    val id: String,
    val from: User?,
    val chat: Chat,
    val isIncoming: Boolean = false,
    val date: Date = Date()
) {
    abstract fun formatMessage(): String

    companion object AbstractFactoryMessage{
        var lastMessageId = -1

        fun makeMessage(from: User, chat: Chat, date: Date, payload: Any?, type: String, isIncoming: Boolean = false) : BaseMessage {
            lastMessageId++
            return when (type) {
                "text" -> TextMessage("$lastMessageId", from, chat, date, isIncoming, payload as String)
                "image" -> ImageMessage("$lastMessageId", from, chat, date, isIncoming, payload as String)
                else -> throw IllegalStateException("Неизвестный тип сообщения")
            }
        }
    }
}