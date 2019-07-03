package ru.skillbranch.dev_intensive.extensions

import java.lang.UnsupportedOperationException
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR
const val YEAR = 365 * DAY

enum class TimeUnits constructor(val denomination: String){
    SECOND("second(s)"),
    MINUTE("minute(s)"),
    HOUR("hour(s)"),
    DAY("day(s)");
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
        this.time in 0L until SECOND
        -> "только что"
        this.time in SECOND until 2 * SECOND
        -> "${this.time / SECOND} секунду назад"
        this.time in 2 * SECOND until 5 * SECOND
        -> "${this.time / SECOND} секунды назад"
        this.time in 5 * SECOND until 10 * SECOND
        -> "${this.time / SECOND} секунд назад"
        this.time in 10 * SECOND until MINUTE
        -> "несколько секунд назад"
        this.time in MINUTE until 2 * MINUTE
        -> "${this.time / MINUTE} минуту назад"
        this.time in 2 * MINUTE until 10 * MINUTE
        -> "${this.time / MINUTE} минут назад"
        this.time in 10 * MINUTE until HOUR
        -> "несколько минут назад"
        this.time in HOUR until 2 * HOUR
        -> "${this.time / DAY} час назад"
        this.time in 2 * HOUR until 5 * HOUR
        -> "${this.time / DAY} часа назад"
        this.time in 5 * HOUR until 10 * HOUR
        -> "${this.time / DAY} часов назад"
        this.time in DAY until 2 * DAY
        -> "${this.time / DAY} день назад"
        this.time in 2 * DAY until 5 * DAY
        -> "${this.time / DAY} дня назад"
        this.time in 5 * DAY until 10 * DAY
        -> "${this.time / DAY} дней назад"
        this.time in 10 * DAY until YEAR
        -> "несколько дней назад"
        this.time > YEAR
        -> "более года назад"
        else
        -> throw UnsupportedOperationException("this time ${this.time} cannot be calculated")
    }
}