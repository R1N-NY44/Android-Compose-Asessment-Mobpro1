package org.d3if3062.asessment1.backend.logic

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import org.d3if3062.asessment1.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.math.abs

// Fungsi untuk menghitung ulang sisa waktu dalam format yang sesuai
@RequiresApi(Build.VERSION_CODES.O)
fun calculateRemainingTime(deadline: String): Long {
    val deadlineDate = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
    val currentDate = LocalDateTime.now()
    return ChronoUnit.SECONDS.between(currentDate, deadlineDate)
}

// Fungsi untuk menghitung ulang sisa waktu dalam format yang sesuai
fun calculateRemainingTimeString(context: Context, difference: Long): String {
    val absDifference = abs(difference)
    val secondsInMinute = 60
    val secondsInHour = 3600
    val secondsInDay = 86400

    val days = absDifference / secondsInDay
    val hours = (absDifference % secondsInDay) / secondsInHour
    val minutes = ((absDifference % secondsInDay) % secondsInHour) / secondsInMinute
    val seconds = ((absDifference % secondsInDay) % secondsInHour) % secondsInMinute

    return buildString {
        if (difference < 0) {
            append(" ")
            append(context.getString(R.string.deadline_timeout))
            append("(")
            if (days > 0) {
                append(context.getString(R.string.deadline_day, days))
            }
            if (hours > 0 || days > 0) {
                append(" ")
                append(context.getString(R.string.deadline_hour, hours))
            }
            if (minutes > 0 || hours > 0 || days > 0) {
                append(" ")
                append(context.getString(R.string.deadline_minute, minutes))
            }
            if (seconds > 0 || minutes > 0 || hours > 0 || days > 0) {
                append(" ")
                append(context.getString(R.string.deadline_second, seconds))
            }
            append(")")
        } else {
            if (days > 0) {
                append(" ")
                append(context.getString(R.string.deadline_day, days))
            }
            if (hours > 0 || days > 0) {
                append(" ")
                append(context.getString(R.string.deadline_hour, hours))
            }
            if (minutes > 0 || hours > 0 || days > 0) {
                append(" ")
                append(context.getString(R.string.deadline_minute, minutes))
            }
            if (seconds > 0 || minutes > 0 || hours > 0 || days > 0) {
                append(" ")
                append(context.getString(R.string.deadline_second, seconds))
            }
        }
    }
}