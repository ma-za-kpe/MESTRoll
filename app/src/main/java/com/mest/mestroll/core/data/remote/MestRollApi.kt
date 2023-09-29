package com.mest.mestroll.core.data.remote

import com.mest.mestroll.core.data.remote.model.dummy.DummyUser
import com.mest.mestroll.core.data.remote.model.location.LocationResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url




interface MestRollApi {
    @GET("api/json/v2/user")
    suspend fun getUser(): DummyUser
    @GET
    fun getLocation(@Url URL: String?): Call<LocationResponse?>?
}