package com.example.a10_thun_mengse_pvh_homework_003.helper

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun dateConverter() : String{
    val today = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("d/M/yyyy") // day/month/year
    val dateString = today.format(formatter) // "9/11/2025"
    return dateString
}