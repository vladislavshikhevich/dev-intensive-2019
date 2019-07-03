package ru.skillbranch.dev_intensive.models

import ru.skillbranch.dev_intensive.extensions.humanizeDiff
import java.util.*

class ImageMessage(
    id: String,
    from: User?,
    chat: Chat,
    isComing: Boolean = false,
    date: Date = Date(),
    var image: String?
) : BaseMessage(id, from, chat, isComing, date) {

    override fun formatMessage(): String = "id:$id ${from?.firstName} " +
            "${if(isComing) "получил сообщение" else "отправил"} изображение \"$image\" ${date.humanizeDiff()}"
}