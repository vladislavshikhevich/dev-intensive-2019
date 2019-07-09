package ru.skillbranch.devintensive.extensions

fun String.truncate(count: Int = 16) : String {
    return if(this.length >= count)
        "${this.substring(0, count + 1).trim()}..."
    else
        "${this.trim()}..."
}

fun String.stripHtml() : String {

    val openPos = this.indexOf("<")
    val closePos = this.indexOf(">")

    return if (openPos >= 0 && closePos >= 0) {
        var newData = this.substring(0,openPos)
        newData += this.substring(closePos + 1, this.length)
        newData.stripHtml().replace("\\\\s{1,}", "\\u0020")
    }
    else
        this.replace("\\\\s{1,}", "\\u0020")
}