package com.mest.mestroll.core.data.remote.model.location


import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("city")
    val city: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("country_code")
    val countryCode: String,
    @SerializedName("county")
    val county: String,
    @SerializedName("house_number")
    val houseNumber: String,
    @SerializedName("road")
    val road: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("suburb")
    val suburb: String
)