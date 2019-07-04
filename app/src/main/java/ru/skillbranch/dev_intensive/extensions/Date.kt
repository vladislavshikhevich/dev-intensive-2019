package ru.skillbranch.dev_intensive.extensions

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
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int) : String {

        val list1 = arrayOf(1, 21, 31, 41, 51)
        val list2 = arrayOf(2, 3, 4, 22, 23, 24, 32, 33, 34, 42, 43, 44, 52, 53, 54)
        val list3 = arrayOf(5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 25, 26, 27, 28, 29, 30, 35, 36, 37, 38, 39, 40, 45, 46, 47, 48, 49, 50, 55, 56, 57, 58, 59)

        var suf = ""
        when(this) {
            SECOND -> {
                suf = when {
                    abs(value) in list1 -> "секунду"
                    abs(value) in list2 -> "секунды"
                    abs(value) in list3 -> "секунд"
                    else -> ""
                }

            }
            MINUTE -> {
                suf = when {
                    abs(value) in list1 -> "минуту"
                    abs(value) in list2 -> "минуты"
                    abs(value) in list3 -> "минут"
                    else -> ""
                }

            }
            HOUR -> {
                suf = when {
                    abs(value) in list1 -> "час"
                    abs(value) in list2 -> "часа"
                    abs(value) in list3 -> "часов"
                    else -> ""
                }

            }
            DAY -> {
                suf = when {
                    abs(value) in list1 -> "день"
                    abs(value) in list2 -> "дня"
                    abs(value) in list3 -> "дней"
                    else -> ""
                }

            }
        }
        return "$value $suf"
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

fun Date.humanizeDiff(date: Date = Date()) : String {
    this.time = date.time - this.time
    return when {
        this.time < -SECOND && this.time > -45 * SECOND
        -> "через несколько секунд"
        this.time < -45 * SECOND && this.time > -75 * SECOND
        -> "через минуту"
        this.time < -75 * SECOND && this.time > -45 * MINUTE
        -> "через ${abs(this.time / MINUTE)} минут"
        this.time < -45 * MINUTE && this.time > -75 * MINUTE
        -> "через час"
        this.time < -75 * MINUTE && this.time > -22 * HOUR
        -> "через ${abs(this.time / HOUR)} часов"
        this.time < -22 * HOUR && this.time > -26 * HOUR
        -> "через день"
        this.time < -26 * HOUR && this.time > -360 * DAY
        -> "через ${abs(this.time / DAY)} дней"
        this.time < -360 * DAY
        -> "более чем через год"
        this.time in 0L until SECOND
        -> "только что"
        this.time in SECOND until 45 * SECOND
        -> "несколько секунд назад"
        this.time in 45 * SECOND until 75 * SECOND
        -> "минуту назад"
        this.time in 75 * SECOND until 45 * MINUTE
        -> "${this.time / MINUTE} минут назад"
        this.time in 45 * MINUTE until 75 * MINUTE
        -> "час назад"
        this.time in 75 * MINUTE until 22 * HOUR
        -> "${this.time / HOUR} часов назад"
        this.time in 22 * HOUR until 26 * HOUR
        -> "день назад"
        this.time in 26 * HOUR until 360 * DAY
        -> "${this.time / DAY} дней назад"
        this.time > 360 * DAY
        -> "более года назад"
        else
        -> throw UnsupportedOperationException("this time ${this.time} cannot be calculated")
    }
}