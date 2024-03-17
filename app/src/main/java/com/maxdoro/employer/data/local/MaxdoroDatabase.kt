package com.maxdoro.employer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.maxdoro.employer.data.local.converters.ListTypeConverter
import com.maxdoro.employer.data.local.dao.CachedEmployerDao
import com.maxdoro.employer.data.local.model.CachedEmployer

@Database(
    version = 1,
    entities = [CachedEmployer::class],
    exportSchema = false
)

@TypeConverters(
    ListTypeConverter::class
)

abstract class MaxdoroDatabase : RoomDatabase() {
    abstract fun cachedEmployerDao(): CachedEmployerDao
}
