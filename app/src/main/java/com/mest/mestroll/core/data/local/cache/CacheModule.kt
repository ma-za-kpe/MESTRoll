package com.mest.mestroll.core.data.local.cache

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {
    @Binds
    abstract fun bindCache(cache: MestCacheImpl): MestCache

    companion object {

        @Provides
        @Singleton
        fun provideDatabase(
            @ApplicationContext context: Context
        ): PombeDatabase {
            return Room.databaseBuilder(
                context,
                PombeDatabase::class.java,
                "mestroll.db"
            )
                .build()
        }

        @Provides
        fun provideMestRollDao(
            pombeDatabase: PombeDatabase
        ): MestRollDao = pombeDatabase.mestRollDao()
    }
}