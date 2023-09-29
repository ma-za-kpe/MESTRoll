package com.mest.mestroll.core.data.local.cache

import kotlinx.coroutines.flow.Flow

interface MestCache {
    fun getUser(): Flow<List<UserData>>
}