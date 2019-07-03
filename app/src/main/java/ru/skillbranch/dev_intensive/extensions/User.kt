package ru.skillbranch.dev_intensive.extensions

import ru.skillbranch.dev_intensive.models.User
import ru.skillbranch.dev_intensive.models.UserView
import ru.skillbranch.dev_intensive.utils.Utils

fun User.toUserView() : UserView {
    return UserView(
        id,
        "$firstName $lastName",
        Utils.transliteration("$firstName $lastName"),
        avatar,
        if(lastVisit == null) "Еще ни разу не был" else if(isOnline) "online" else "Последний раз был ${lastVisit?.humanizeDiff()}",
        Utils.toInitials(firstName, lastName)
    )
}