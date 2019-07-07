package ru.skillbranch.devintensive.models

import java.util.*

class TextMessage(
    id: String,
    from: User?,
    chat: Chat,
    date: Date,
    isIncoming: Boolean,
    var text: String
) : BaseMessage(id, from, chat, isIncoming, date) {

    override fun formatMessage(): String {
        return "id: $id ${from?.firstName} ${if (isIncoming) "отправил" else "получил"} сообщение \"$text\" $date"
    }
}