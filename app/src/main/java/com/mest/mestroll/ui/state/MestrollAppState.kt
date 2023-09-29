package com.mest.mestroll.ui.state

import android.content.res.AssetManager
import android.content.res.Resources
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mest.mestroll.ui.models.TopLevelDestination
import com.mest.mestroll.ui.screens.homeNavigationRoute
import com.mest.mestroll.core.utils.snackbar.SnackbarManager
import com.mest.mestroll.core.utils.snackbar.SnackbarMessage.Companion.toMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@Composable
fun rememberMestrollAppState(
    navController: NavHostController = rememberNavController(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    assets: AssetManager = assets(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
): MestrollAppState {
    return remember(
        navController,
        snackbarManager,
        resources,
        assets,
        coroutineScope,
        snackbarHostState,
    ) {
        MestrollAppState(
            navController,
            snackbarManager,
            resources,
            assets,
            coroutineScope,
            snackbarHostState,
        )
    }
}

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

@Composable
@ReadOnlyComposable
fun assets(): AssetManager {
    LocalConfiguration.current
    return LocalContext.current.assets
}

@Stable
class MestrollAppState(
    val navController: NavHostController,
    val snackbarManager: SnackbarManager,
    val resources: Resources,
    val assets: AssetManager,
    val coroutineScope: CoroutineScope,
    val snackbarHostState: SnackbarHostState,
) {

    init {
        coroutineScope.launch {
            snackbarManager.snackbarMessages.filterNotNull().collect { snackbarMessage ->
                val text = snackbarMessage.toMessage(resources)
                snackbarHostState.showSnackbar(text)
            }
        }
    }

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            homeNavigationRoute -> TopLevelDestination.HOME
            else -> null
        }


    fun popUp() {
        navController.popBackStack()
    }

    fun navigate(route: String) {
        navController.navigate(route) { launchSingleTop = true }
    }

    fun navigateAndPopUp(route: String, popUp: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(popUp) { inclusive = true }
        }
    }

    fun clearAndNavigate(route: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(0) { inclusive = true }
        }
    }

}
