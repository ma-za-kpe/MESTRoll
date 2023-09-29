package com.mest.mestroll.core.data.remote.model.location


import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("address")
    val address: Address,
    @SerializedName("boundingbox")
    val boundingbox: List<String>,
    @SerializedName("display_name")
    val displayName: String,
    @SerializedName("lat")
    val lat: String,
    @SerializedName("licence")
    val licence: String,
    @SerializedName("lon")
    val lon: String,
    @SerializedName("osm_id")
    val osmId: String,
    @SerializedName("osm_type")
    val osmType: String,
    @SerializedName("place_id")
    val placeId: String
)