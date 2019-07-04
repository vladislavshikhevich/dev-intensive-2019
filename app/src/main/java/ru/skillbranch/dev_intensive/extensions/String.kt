package ru.skillbranch.dev_intensive.extensions

fun String.truncate(count: Int = 16) : String {
    return if(this.length >= count)
        "${this.substring(0, count + 1).trim()}..."
    else
        "${this.trim()}..."
}

fun String.strimHtml() : String {
    return ""
}