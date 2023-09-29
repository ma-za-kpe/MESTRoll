package com.mest.mestroll.core.data.local.cache

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserData(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String
)
