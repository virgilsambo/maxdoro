package com.maxdoro.employer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maxdoro.employer.data.local.dao.EmployerDao
import com.maxdoro.employer.data.local.model.EmployerEntity

@Database(
    version = 1,
    entities = [EmployerEntity::class]
)

abstract class MaxdoroDatabase : RoomDatabase() {
    abstract fun employerDao(): EmployerDao
}
