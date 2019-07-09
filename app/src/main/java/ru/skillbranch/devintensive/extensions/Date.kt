package ru.skillbranch.devintensive.extensions

import android.util.Log
import ru.skillbranch.devintensive.utils.Utils
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int): String {
        val pluralForm = Utils.getPluralForm(value.toLong(), Utils.getPluralsForms(this))
        return "$value $pluralForm"
    }
}

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy") : String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND) : Date {
    var time = this.time

    time += when(units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = time

    return this
}

fun Date.humanizeDiff(date: Date = Date()) : String  = when {
    abs(this.time - date.time) <= SECOND -> "только что"
    this.time < date.time - 360 * DAY -> "более года назад"
    this.time > date.time + 360 * DAY -> "более чем через год"
    else -> {
        val seconds = (abs(this.time - date.time) / SECOND).toInt()
        val template = if (this > date) "через %s" else "%s назад"
        template.format(
            when (seconds) {
                in 1..45 -> "несколько секунд"
                in 45..75 -> "минуту"
                in 75..45 * 60 -> TimeUnits.MINUTE.plural(seconds / 60)
                in 45 * 60..75 * 60 -> "час"
                in 75 * 60..22 * 3600 -> TimeUnits.HOUR.plural(seconds / 3600)
                in 22 * 3600..26 * 3600 -> "день"
                in 26 * 3600..360 * 86400 -> TimeUnits.DAY.plural(seconds / 86400)
                else -> throw IllegalStateException()
            }
        )
    }
}