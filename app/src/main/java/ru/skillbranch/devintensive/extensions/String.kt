package ru.skillbranch.devintensive.extensions

fun String.truncate(truncateLength: Int = 16): String {
    val startString = this

    return if (startString.length > truncateLength) {
        "${startString.dropLast(startString.length - truncateLength).trim()}..."
    } else {
        startString
    }
}

fun String.stripHtml(): String {
    return this
        .replace(Regex("""<.*?>"""), "")
        .replace(Regex("""&(#\d+?\w+?)"""), "")
        .split(Regex(""" +""")).joinToString(" ")
}