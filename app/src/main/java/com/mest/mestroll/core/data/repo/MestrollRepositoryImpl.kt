package com.mest.mestroll.core.data.repo

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import com.mest.mestroll.core.data.local.preferences.Preferences
import com.mest.mestroll.core.data.remote.MestRollApi
import com.mest.mestroll.core.data.remote.model.location.LocationResponse
import com.mest.mestroll.core.domain.EnterRoom
import com.mest.mestroll.core.domain.MestrollRepository
import com.mest.mestroll.core.utils.snackbar.SnackbarManager
import com.mest.mestroll.core.utils.snackbar.SnackbarMessage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.AccessController.getContext
import javax.inject.Inject


class MestrollRepositoryImpl @Inject constructor(
    val mestRollApi: MestRollApi,
    val preferences: Preferences
) : MestrollRepository {
    override suspend fun enterUser(enterRoom: EnterRoom) {
        TODO("Not yet implemented")
    }

    override suspend fun getLocation(lat: String, lng: String) {
        mestRollApi.getLocation("https://us1.locationiq.com/v1/reverse?key=pk.d0890c298b82d74cd156e9feccdf6e65&lat=${lat}&lon=${lng}&format=json")
            ?.enqueue(object : Callback<LocationResponse?> {
                override fun onResponse(
                    call: Call<LocationResponse?>,
                    response: Response<LocationResponse?>
                ) {
                    // save to
                    Log.d("TAG", "onResponse: ${response.body()?.displayName.toString()}")
                    preferences.putAddress(response.body()?.displayName.toString())
                }

                override fun onFailure(call: Call<LocationResponse?>, t: Throwable) {
                    SnackbarManager.showMessage(SnackbarMessage.StringSnackbar("Error ${t.message}"))
                }
            })
    }
}