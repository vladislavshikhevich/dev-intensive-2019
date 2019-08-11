package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils

class Profile(
    var nickname: String = "",
    val firstName: String,
    val lastName: String,
    val about: String,
    val repository: String,
    val rating: Int = 0,
    val respect: Int = 0
) {

    init {
        nickname = Utils.transliteration("$firstName $lastName", "_").trim()
    }

    val rank: String = "Junior Android Developer"

    fun toMap(): Map<String, Any> = mapOf(
        "nickName" to nickname,
        "rank" to rank,
        "firstName" to firstName,
        "lastName" to lastName,
        "about" to about,
        "repository" to repository,
        "rating" to rating,
        "respect" to respect
    )
}