package com.mest.mestroll.core.data.local.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        UserData::class
    ],
    version = 1
)
@TypeConverters(MestRollConvertor::class)
abstract class PombeDatabase : RoomDatabase() {
    abstract fun mestRollDao(): MestRollDao
}