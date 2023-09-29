package com.mest.mestroll.ui.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector
import com.mest.mestroll.R

enum class TopLevelDestination(
    val icon: ImageVector,
    val iconTextId: ImageVector,
    val titleTextId: Int
) {
    HOME(
        icon = Icons.Filled.Menu,
        iconTextId = Icons.Filled.Home,
        titleTextId = R.string.home
    )
}