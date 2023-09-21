package com.steegler.exit.di

import android.content.Context
import androidx.room.Room
import com.steegler.exit.data.AppDatabase
import com.steegler.exit.data.dao.RateDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRateDao(@ApplicationContext appContext: Context): RateDao {
        return provideAppDatabase(appContext).rateDao()
    }

    @Singleton
    @Provides
    fun provideAppDatabase(appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "ExIT"
        )
//            .addMigrations(MIGRATION_1_2)
            .build()
    }
}