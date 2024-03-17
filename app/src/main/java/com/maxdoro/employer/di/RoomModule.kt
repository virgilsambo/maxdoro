package com.maxdoro.employer.di

import android.content.Context
import androidx.room.Room
import com.maxdoro.employer.common.Constants
import com.maxdoro.employer.data.local.dao.EmployerDao
import com.maxdoro.employer.data.local.MaxdoroDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideCoinDatabase(@ApplicationContext context: Context): MaxdoroDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MaxdoroDatabase::class.java,
            Constants.MAXDORO_DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideEmployerDao(database: MaxdoroDatabase): EmployerDao {
        return database.employerDao()
    }
}
