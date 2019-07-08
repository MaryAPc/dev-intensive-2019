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

        fun makeMessage(from: User?, chat: Chat, date: Date, payload: Any?, type: String) : BaseMessage {
            lastMessageId++
            return when (type) {
                "image" -> ImageMessage("$lastMessageId",  from, chat, date = date, image = payload as String)
                else -> TextMessage("$lastMessageId", from, chat, date = date, text = payload as String)
            }
        }
    }
}