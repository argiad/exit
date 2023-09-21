package com.steegler.exit.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.steegler.exit.data.dao.RateDao
import com.steegler.exit.data.entity.RateEntity


@Database(
    entities = [
        RateEntity::class
    ],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun rateDao(): RateDao
}

val MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Since we didn't alter the table, there's nothing else to do here.
    }
}