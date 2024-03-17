package com.maxdoro.employer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.maxdoro.employer.data.local.model.CachedEmployer
import com.maxdoro.employer.data.local.model.EmployerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CachedEmployerDao {
    @Query("SELECT * FROM CachedEmployer WHERE filter = :filter")
    fun getCachedEmployer(filter: String): Flow<CachedEmployer?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateCachedEmployer(cachedEmployer: CachedEmployer)

    @Query("DELETE FROM CachedEmployer")
    fun deleteAllCachedEmployers()

    @Transaction
    fun updateCachedEmployers(cachedEmployer: CachedEmployer) {
        deleteAllCachedEmployers()
        insertOrUpdateCachedEmployer(cachedEmployer)
    }
}
