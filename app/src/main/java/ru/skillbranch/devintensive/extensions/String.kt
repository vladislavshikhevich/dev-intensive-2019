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