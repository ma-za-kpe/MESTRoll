package com.mest.mestroll.core.data.local.cache

import androidx.room.Dao
import kotlinx.coroutines.flow.Flow

@Dao
interface MestRollDao {
    fun getUser(): Flow<List<UserData>>
}