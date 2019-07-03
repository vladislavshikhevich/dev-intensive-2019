package ru.skillbranch.dev_intensive.utils

import com.ibm.icu.text.Transliterator

object Utils {

    enum class TransliterationUnits(val denomination: String) {
        CYRILLIC_TO_LATIN("Russian-Latin/BGN")
    }

    fun parseFullName(fullName: String?) : Pair<String?, String?> {
        val parts : List<String>? = fullName?.split(" ")

        var s = parts?.getOrNull(0)
        val firstName = if(s.isNullOrEmpty()) "Vlad" else s
        s = parts?.getOrNull(1)
        val lastName = if(s.isNullOrEmpty()) "Shikhevich" else s

        return firstName to lastName
    }

    fun transliteration(
        payLoad: String,
        divider: String = " ",
        transliterationUnits: TransliterationUnits = Utils.TransliterationUnits.CYRILLIC_TO_LATIN) : String {
        val toLatinTrans = Transliterator.getInstance(transliterationUnits.denomination)
        return toLatinTrans.transliterate(payLoad)
    }

    fun toInitials(firstName: String?, lastName: String?) : String? {
        if(firstName.isNullOrEmpty() || lastName.isNullOrEmpty()) return null
        return "${if(firstName.isNullOrEmpty()) "" else firstName[0]} ${if(lastName.isNullOrEmpty()) "" else lastName[0]}"
    }
}