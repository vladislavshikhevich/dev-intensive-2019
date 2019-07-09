package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.extensions.TimeUnits.Companion.pluralForm
import java.lang.UnsupportedOperationException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR
const val YEAR = 365 * DAY

enum class TimeUnits {
    SECOND {
        fun plural(value: Int) : String {
            val form = when {
                value % 10 == 1 && value != 11 -> 0
                value % 10 in 2..4 -> 1
                else -> 2
            }
            return arrayListOf(
                "%s секунду",
                "%s секунды",
                "%s секунд"
            )[form].format(value)
        }
    },

    MINUTE {
        fun plural(value: Int) : String {
            val form = when {
                value % 10 == 1 && value != 11 -> 0
                value % 10 in 2..4 -> 1
                else -> 2
            }
            return arrayListOf(
                "%s минуту",
                "%s минуты",
                "%s минут"
            )[form].format(value)
        }
    },

    HOUR {
        fun plural(value: Int) : String {
            val form = when {
                value % 10 == 1 && value != 11 -> 0
                value % 10 in 2..4 -> 1
                else -> 2
            }
            return arrayListOf(
                "%s час",
                "%s часа",
                "%s часов"
            )[form].format(value)
        }
    },

    DAY {
        fun plural(value: Int) : String {
            val form = when {
                value % 10 == 1 && value != 11 -> 0
                value % 10 in 2..4 -> 1
                else -> 2
            }
            return arrayListOf(
                "%s день",
                "%s дня",
                "%s дней"
            )[form].format(value)
        }
    };

    companion object {
        fun pluralForm(value: Int, unit: TimeUnits): String {


            val form = when {
                value % 10 == 1 && value != 11 -> 0
                value % 10 in 2..4 -> 1
                else -> 2
            }
            return arrayListOf(
                "%s секунду",
                "%s секунды",
                "%s секунд",
                "%s минуту",
                "%s минуты",
                "%s минут",
                "%s час",
                "%s часа",
                "%s часов",
                "%s день",
                "%s дня",
                "%s дней"
            )[form + 3 * when (unit) {
                TimeUnits.SECOND -> 0
                TimeUnits.MINUTE -> 1
                TimeUnits.HOUR -> 2
                TimeUnits.DAY -> 3
            }].format(value)
        }
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
    abs(this.time - date.time) <= SECOND && date > this -> "только что"
    this.time < date.time - 360 * DAY -> "более года назад"
    this.time > date.time + 360 * DAY -> "более чем через год"
    else -> {
        val seconds = (abs(this.time - date.time) / SECOND).toInt()
        val template = if (this > date) "через %s" else "%s назад"
        template.format(
            when (seconds) {
                in 1..45 -> "несколько секунд"
                in 45..75 -> "минуту"
                in 75..45 * 60 -> pluralForm(seconds / 60, TimeUnits.MINUTE)
                in 45 * 60..75 * 60 -> "час"
                in 75 * 60..22 * 3600 -> pluralForm(seconds / 3600, TimeUnits.HOUR)
                in 22 * 3600..26 * 3600 -> "день"
                in 26 * 3600..360 * 86400 -> pluralForm(seconds / 86400, TimeUnits.DAY)
                else -> throw IllegalStateException()
            }
        )
    }
}