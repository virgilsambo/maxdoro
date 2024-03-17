package com.maxdoro.employer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.maxdoro.employer.data.local.model.EmployerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployerDao {
    @Query("SELECT * FROM EmployerEntity")
    fun getEmployers(): Flow<List<EmployerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addEmployers(employers: List<EmployerEntity>)

    @Query("DELETE FROM EmployerEntity")
    fun deleteAllEmployers()

    @Transaction
    fun updateEmployers(employers: List<EmployerEntity>) {
        deleteAllEmployers()
        addEmployers(employers)
    }
}
