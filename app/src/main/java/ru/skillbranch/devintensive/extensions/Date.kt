package ru.skillbranch.devintensive.extensions

import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*


const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val simpleFormat = SimpleDateFormat(pattern, Locale("ru"))
    return simpleFormat.format(this)
}

fun Date.add(value: Int, unit: TimeUnits): Date {
    var time = this.time

    time += when (unit) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = time
    return this
}

fun Date.humanizeDiff(currentDate: Date = Date()): String {
    val modifiedTime = this.time
    val currentTime = currentDate.time
    val absDiff = abs(currentTime - modifiedTime)
    val isPast = currentTime - modifiedTime > 0

    return when {
        (absDiff / SECOND <= 1) -> "только что"
        (absDiff / SECOND in 2..45) -> if (isPast) "несколько секунд назад" else "через несколько секунд"
        (absDiff / SECOND in 46..75) -> if (isPast) "минуту назад" else "через минуту"
        (absDiff / MINUTE <= 45) -> if (isPast) "${TimeUnits.MINUTE.plural((absDiff / MINUTE).toInt())} назад" else
            "через ${TimeUnits.MINUTE.plural((absDiff / MINUTE).toInt())}"
        (absDiff / MINUTE <= 75) -> if (isPast) "час назад" else "через час"
        (absDiff / HOUR <= 22) -> if (isPast) "${TimeUnits.HOUR.plural((absDiff / HOUR).toInt())} назад" else
            "через ${TimeUnits.HOUR.plural((absDiff / HOUR).toInt())}"
        (absDiff / HOUR <= 26) -> if (isPast) "день назад" else "через день"
        (absDiff / DAY <= 360) -> if (isPast) "${TimeUnits.DAY.plural((absDiff / DAY).toInt())} назад" else
            "через ${TimeUnits.DAY.plural((absDiff / DAY).toInt())}"
        (absDiff / DAY >= 360) -> if (isPast) "более года назад" else "более чем через год"
        else -> "время неизвестно"
    }
}

enum class TimeUnits {
    SECOND, MINUTE, HOUR, DAY;

    fun plural(value: Int): String {
        val mod = value % 10

        when {
            mod == 1 -> return when (this@TimeUnits) {
                SECOND -> "$value секунду"
                MINUTE -> "$value минуту"
                HOUR -> "$value час"
                DAY -> "$value день"
            }
            mod in 2..4 -> return when (this@TimeUnits) {
                SECOND -> "$value секунды"
                MINUTE -> "$value минуты"
                HOUR -> "$value часа"
                DAY -> "$value дня"
            }
            mod > 4 -> return when (this@TimeUnits) {
                SECOND -> "$value секунд"
                MINUTE -> "$value минут"
                HOUR -> "$value часов"
                DAY -> "$value дней"
            }
            else -> throw IllegalStateException("Невозможно привести время")
        }
    }
}
