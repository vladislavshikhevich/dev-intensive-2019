package ru.skillbranch.devintensive.utils

object Utils {

    fun parseFullName(fullName: String?) : Pair<String?, String?> {
        if(fullName.isNullOrEmpty() || fullName.isNullOrBlank()) return null to null

        val parts : List<String>? = fullName.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)

        return firstName to lastName
    }

    fun transliteration(
        payload: String,
        divider: String = " ") : String {

        var trans = payload

        trans = trans.replace("а", "a")
        trans = trans.replace("б", "b")
        trans = trans.replace("в", "v")
        trans = trans.replace("г", "g")
        trans = trans.replace("д", "d")
        trans = trans.replace("е", "e")
        trans = trans.replace("ё", "e")
        trans = trans.replace("ж", "zh")
        trans =  trans.replace("з", "z")
        trans = trans.replace("и", "i")
        trans = trans.replace("й", "i")
        trans = trans.replace("к", "k")
        trans = trans.replace("л", "l")
        trans = trans.replace("м", "m")
        trans = trans.replace("н", "n")
        trans = trans.replace("о", "o")
        trans = trans.replace("п", "p")
        trans = trans.replace("р", "r")
        trans = trans.replace("с", "s")
        trans = trans.replace("т", "t")
        trans = trans.replace("у", "u")
        trans = trans.replace("ф", "f")
        trans = trans.replace("х", "h")
        trans = trans.replace("ц", "c")
        trans = trans.replace("ч", "ch")
        trans = trans.replace("ш", "sh")
        trans = trans.replace("щ", "sh")
        trans = trans.replace("ъ", "")
        trans = trans.replace("ы", "i")
        trans = trans.replace("ь", "")
        trans = trans.replace("э", "e")
        trans = trans.replace("ю", "yu")
        trans = trans.replace("я", "ya")

        trans = trans.replace("А", "A")
        trans = trans.replace("Б", "B")
        trans = trans.replace("В", "V")
        trans = trans.replace("Г", "G")
        trans = trans.replace("Д", "D")
        trans = trans.replace("Е", "E")
        trans = trans.replace("Ё", "E")
        trans = trans.replace("Ж", "Zh")
        trans = trans.replace("З", "Z")
        trans = trans.replace("И", "I")
        trans = trans.replace("Й", "I")
        trans = trans.replace("К", "K")
        trans = trans.replace("Л", "L")
        trans = trans.replace("М", "M")
        trans = trans.replace("Н", "N")
        trans = trans.replace("О", "O")
        trans = trans.replace("П", "P")
        trans = trans.replace("Р", "R")
        trans = trans.replace("С", "S")
        trans = trans.replace("Т", "T")
        trans = trans.replace("У", "U")
        trans = trans.replace("Ф", "F")
        trans = trans.replace("Х", "H")
        trans = trans.replace("Ц", "C")
        trans = trans.replace("Ч", "Ch")
        trans = trans.replace("Ш", "Sh")
        trans = trans.replace("Щ", "Sh")
        trans = trans.replace("Ъ", "")
        trans = trans.replace("Ы", "I")
        trans = trans.replace("Ь", "")
        trans = trans.replace("Э", "E")
        trans = trans.replace("Ю", "Yu")
        trans = trans.replace("Я", "Ya")

        val parts : List<String>? = trans.split(" ")
        return "${parts!![0]}$divider${parts[1]}"
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