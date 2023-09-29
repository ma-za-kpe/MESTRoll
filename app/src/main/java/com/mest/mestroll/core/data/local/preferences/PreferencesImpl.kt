package com.mest.mestroll.core.data.local.preferences

import android.content.Context
import android.content.SharedPreferences
import com.mest.mestroll.core.utils.AppConstants.KEY_IS_ADDRESS
import com.mest.mestroll.core.utils.AppConstants.KEY_IS_EMAIL
import com.mest.mestroll.core.utils.AppConstants.KEY_IS_LAT
import com.mest.mestroll.core.utils.AppConstants.KEY_IS_LNG
import com.mest.mestroll.core.utils.AppConstants.KEY_IS_LOGGED_IN
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesImpl @Inject constructor(
    @ApplicationContext context: Context
): Preferences {
    companion object {
        const val PREFERENCES_NAME = "MESTROLL_PREFERENCES"
    }

    private val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    override fun isLoggedIn(token: Boolean) {
        edit { putBoolean(KEY_IS_LOGGED_IN, token) }
    }

    override fun getIsLoggedIn(): Boolean {
        return preferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    override fun putEmail(email: String) {
        edit { putString(KEY_IS_EMAIL, email) }
    }

    override fun getEmail(): String {
        return preferences.getString(KEY_IS_EMAIL, null).toString()
    }

    override fun putLat(lat: String) {
        edit { putString(KEY_IS_LAT, lat) }
    }

    override fun getLat(): String {
        return preferences.getString(KEY_IS_LAT, null).toString()
    }

    override fun putLng(lng: String) {
        edit { putString(KEY_IS_LNG, lng) }
    }

    override fun getLng(): String {
        return preferences.getString(KEY_IS_LNG, null).toString()
    }

    override fun putAddress(lng: String) {
        edit { putString(KEY_IS_ADDRESS, lng) }
    }

    override fun getAddress(): String {
        return preferences.getString(KEY_IS_ADDRESS, null).toString()
    }

    private inline fun edit(block: SharedPreferences.Editor.() -> Unit) {
        with(preferences.edit()) {
            block()
            commit()
        }
    }
}