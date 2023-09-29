package com.mest.mestroll.core.domain

interface MestrollRepository {
    suspend fun enterUser(enterRoom: EnterRoom)
    suspend fun getLocation(lat: String, lng: String)
}