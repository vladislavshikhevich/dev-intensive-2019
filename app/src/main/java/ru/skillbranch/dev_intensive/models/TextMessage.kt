package ru.skillbranch.dev_intensive.models

import ru.skillbranch.dev_intensive.extensions.humanizeDiff
import java.util.*

class TextMessage(
    id: String,
    from: User?,
    chat: Chat,
    isComing: Boolean = false,
    date: Date = Date(),
    var text: String?
) : BaseMessage(id, from, chat, isComing, date) {

    override fun formatMessage(): String = "id:$id ${from?.firstName} " +
            "${if(isIncoming) "получил сообщение" else "отправил"} сообщение \"$text\" ${date.humanizeDiff()}"
}