package ru.skillbranch.devintensive.extensions

fun String.truncate(count: Int = 16) : String {
    if(this.trim().length <= count) return this.trim()
    return "${this.trim().substring(0, Math.min(this.length, count)).trim()}..."
}

fun String.stripHtml() : String {
    return this
        .replace(Regex("<.*?>"), "")
        .replace(Regex(
            "(&amp;|&nbsp;|&quot;|&apos;|&lt;|&gt;|&#34;|&#39;|&#38;|&#60;|&#62;)"
        ), "")
        .replace(Regex(" {2,}"), " ")
}

fun String.isStartsUpperCase(): Boolean {
    if(this.isEmpty()) return false

    return this.toCharArray()[0].isUpperCase()
}

fun String.isStartsLowerCase(): Boolean {
    if(this.isEmpty()) return false

    return this.toCharArray()[0].isLowerCase()
}

fun String.containsDigit(): Boolean {
    if(this.isEmpty()) return false

    this.toCharArray().forEach {
        if (!Character.isLetter(it))
            return false
    }

    return true
}

fun String.isOnlyDigits(): Boolean {
    if(this.isEmpty()) return false

    this.toCharArray().forEach {
        if (Character.isLetter(it))
            return false
    }

    return true
}

fun String.isSeriesValidate(): Boolean {
    if(this.isEmpty() || this.length != 7) return false

    return isOnlyDigits()
}