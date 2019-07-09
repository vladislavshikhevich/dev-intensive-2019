package ru.skillbranch.devintensive.utils

import com.ibm.icu.text.UTF16.append

object Utils {

    fun parseFullName(fullName: String?) : Pair<String?, String?> {
        if(fullName.isNullOrEmpty() || fullName.isNullOrBlank()) return null to null

        val parts : List<String>? = fullName.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)

        return firstName to lastName
    }

    fun toInitials(firstName: String?, lastName: String?) : String? {

        var i1 = firstName?.getOrNull(0)
        var i2 = lastName?.getOrNull(0)

        if(firstName.isNullOrBlank())
            i1 = null

        if(lastName.isNullOrBlank())
            i2 = null

        return when {
            i1 == null && i2 == null
            -> null
            i1 == null && i2 != null
            -> "$i2".toUpperCase()
            i1 != null && i2 == null
            -> "$i1".toUpperCase()
            else
            -> "$i1$i2".toUpperCase()
        }
    }

    fun transliteration(
        payload: String,
        divider: String = " ") : String = buildString {

        payload.asSequence().forEach {
            append(if (it == ' ') divider else it.transliterate())
        }
    }
}

private val chars = mapOf(
    'а' to "a",
    'б' to "b",
    'в' to "v",
    'г' to "g",
    'д' to "d",
    'е' to "e",
    'ё' to "e",
    'ж' to "zh",
    'з' to "z",
    'и' to "i",
    'й' to "i",
    'к' to "k",
    'л' to "l",
    'м' to "m",
    'н' to "n",
    'о' to "o",
    'п' to "p",
    'р' to "r",
    'с' to "s",
    'т' to "t",
    'у' to "u",
    'ф' to "f",
    'х' to "h",
    'ц' to "c",
    'ч' to "ch",
    'ш' to "sh",
    'щ' to "sh'",
    'ъ' to "",
    'ы' to "i",
    'ь' to "",
    'э' to "e",
    'ю' to "yu",
    'я' to "ya")

private fun Char.transliterate(): String {
    return if (isUpperCase()) {
        chars[this.toLowerCase()]?.toUpperCase() ?: this.toString()
    } else {
        chars[this] ?: this.toString()
    }
}