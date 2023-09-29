package com.mest.mestroll.core.data.local.cache

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MestCacheImpl : MestCache {
    override fun getUser(): Flow<List<UserData>> {
        return flow {  }
    }
}