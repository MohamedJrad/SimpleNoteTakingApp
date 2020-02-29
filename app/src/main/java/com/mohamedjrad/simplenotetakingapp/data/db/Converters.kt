package com.mohamedjrad.simplenotetakingapp.data.db

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import com.google.gson.Gson
import java.lang.reflect.Type
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset

class Converters {

    @TypeConverter
    fun fromStringToLocalDateTime(st: String): LocalDateTime = LocalDateTime.parse(st)

    @TypeConverter
    fun fromLocalDateTimeToString(dt: LocalDateTime): String = dt.toString()

    @TypeConverter
    fun fromStringListToString(ls: List<String>): String = ls.joinToString("~")

    @TypeConverter
    fun fromStringToStringList(st: String): List<String> = st.split("~")
}