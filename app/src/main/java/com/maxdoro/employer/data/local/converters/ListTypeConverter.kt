package com.maxdoro.employer.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.maxdoro.employer.common.fromJson
import com.maxdoro.employer.model.Employer
import kotlinx.collections.immutable.toPersistentList

class ListTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromList(value: List<Employer>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toList(value: String): List<Employer> {
        return try {
            gson.fromJson<List<Employer>>(value)
                .toPersistentList()
        } catch (e: Exception) {
            listOf()
        }
    }
}