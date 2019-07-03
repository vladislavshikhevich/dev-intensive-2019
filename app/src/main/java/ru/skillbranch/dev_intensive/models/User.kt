package ru.skillbranch.dev_intensive.models

import ru.skillbranch.dev_intensive.utils.Utils
import java.util.*

data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    var lastVisit: Date? = null,
    var isOnline: Boolean = false
) {

    constructor(id: String, firstName: String?, lastName: String?) : this(
        id,
        firstName,
        lastName,
        avatar = null
    )

    companion object Factory {

        private var lastId = -1

        fun makeUser(fullName: String?) : User {
            lastId++

            val (firstName, lastName) = Utils.parseFullName(fullName)
            return User("$lastId", firstName, lastName)
        }
    }

    class Builder {

        private var id: String = "0"
        private var firstName: String? = "Vlad"
        private var lastName: String? = "Shikhevich"
        private var avatar: String? = null
        private var rating: Int = 0
        private var respect: Int = 0
        private var lastVisit: Date? = Date()
        private var isOnline: Boolean = false

        fun setId(id: String) : Builder {
            this.id = id
            return this
        }

        fun setFirstName(firstName: String?) : Builder {
            this.firstName = firstName
            return this
        }

        fun setLastName(lastName: String?) : Builder {
            this.lastName = lastName
            return this
        }

        fun setAvatar(avatar: String?) : Builder {
            this.avatar = avatar
            return this
        }

        fun setRating(rating: Int) : Builder {
            this.rating = rating
            return this
        }

        fun setRespect(respect: Int) : Builder {
            this.respect = respect
            return this
        }

        fun setLastVisit(lastVisit: Date?) : Builder {
            this.lastVisit = lastVisit
            return this
        }

        fun setOnline(isOnline: Boolean) : Builder {
            this.isOnline = isOnline
            return this
        }

        fun build() : User = User(id, firstName, lastName, avatar, rating, respect, lastVisit, isOnline)
    }
}