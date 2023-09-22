package com.steegler.exit.di

import android.content.Context
import androidx.room.Room
import com.steegler.exit.data.AppDatabase
import com.steegler.exit.data.dao.RateDao
import com.steegler.exit.domain.BusinessLogic
import com.steegler.exit.domain.repository.OfflineItemsRepository
import com.steegler.exit.domain.repository.RatesRepo
import com.steegler.exit.network.NetworkService
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

    @Singleton
    @Provides
    fun provideLiveRepo(@ApplicationContext appContext: Context): RatesRepo {
        return OfflineItemsRepository(provideRateDao(appContext))
    }

    @Singleton
    @Provides
    fun provideNetworkService(): NetworkService {
        return NetworkService.getService()
    }

    @Singleton
    @Provides
    fun provideBusinessLogic(@ApplicationContext appContext: Context): BusinessLogic {
        val tempRepo = OfflineItemsRepository(provideRateDao(appContext))
        return BusinessLogic.build(tempRepo, provideNetworkService())
    }


}