package ru.skillbranch.devintensive.models

import java.util.*

class ImageMessage(
    id: String,
    from: User?,
    chat: Chat,
    date: Date,
    isIncoming: Boolean,
    var image: String
) : BaseMessage(id, from, chat, isIncoming, date) {

    override fun formatMessage(): String {
        return "id: $id ${from?.firstName} ${if (isIncoming) "отправил" else "получил"} изображение \"$image\" $date"
    }
}