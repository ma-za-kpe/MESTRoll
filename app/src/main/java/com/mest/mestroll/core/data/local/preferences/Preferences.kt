package com.mest.mestroll.core.data.local.preferences

interface Preferences {
    fun isLoggedIn(token: Boolean)
    fun getIsLoggedIn(): Boolean

    fun putEmail(email: String)
    fun getEmail(): String

    fun putLat(lat: String)
    fun getLat(): String

    fun putLng(lng: String)
    fun getLng(): String

    fun putAddress(lng: String)
    fun getAddress(): String
}