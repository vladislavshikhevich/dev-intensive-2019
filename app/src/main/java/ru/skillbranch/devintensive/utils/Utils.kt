package ru.skillbranch.devintensive.utils

import com.ibm.icu.text.Transliterator

object Utils {

    enum class TransliterationUnits(val denomination: String) {
        CYRILLIC_TO_LATIN("Russian-Latin/BGN")
    }

    fun parseFullName(fullName: String?) : Pair<String?, String?> {
        if(fullName.isNullOrEmpty() || fullName.isNullOrBlank()) return null to null

        val parts : List<String>? = fullName.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)

        return firstName to lastName
    }

    fun transliteration(
        payLoad: String,
        divider: String = " ",
        transliterationUnits: TransliterationUnits = Utils.TransliterationUnits.CYRILLIC_TO_LATIN) : String {
        val toLatinTrans = Transliterator.getInstance(transliterationUnits.denomination)
        val parts : List<String>? = payLoad.split(" ")
        return "${toLatinTrans.transliterate(parts!![0])}$divider${toLatinTrans.transliterate(parts[1])}"
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
}