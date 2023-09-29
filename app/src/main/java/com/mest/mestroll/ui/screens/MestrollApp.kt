package com.mest.mestroll.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mest.mestroll.R
import com.mest.mestroll.ui.screens.auth.LoginRoute
import com.mest.mestroll.ui.screens.auth.ProfileRoute
import com.mest.mestroll.ui.screens.main.AbsentRoute
import com.mest.mestroll.ui.screens.main.AbsentScreen
import com.mest.mestroll.ui.screens.main.MainRoute
import com.mest.mestroll.ui.state.MestrollAppState
import com.mest.mestroll.ui.state.rememberMestrollAppState
import com.mest.mestroll.ui.theme.MestrollTheme
import com.mest.mestroll.ui.theme.dismiss
import com.mest.mestroll.ui.theme.roomnumber

const val homeNavigationRoute = "home_route"
const val loginNavigationRoute = "login_route"
const val profileNavigationRoute = "profile_route"
const val absentNavigationRoute = "absent_route"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MestrollApp(
    isLoggedIn: Boolean,
    putLogIn: (Boolean) -> Unit,
    appState: MestrollAppState = rememberMestrollAppState()
) {
    Scaffold(
        topBar = {
            // Show the top app bar on top level destinations.
            val destination = appState.currentTopLevelDestination
            if (destination != null) {
                TopAppBar(
                    title = {
                        TextButton(
                            onClick = {
                                      //
                            },
                            colors =  ButtonDefaults.buttonColors(
                                contentColor = MaterialTheme.colorScheme.onBackground,
                                containerColor = roomnumber
                            )
                        ) {
                            Text("Room 1")
                        }
                    },
                    actions = {
                        IconButton(
                            modifier = Modifier
                                .size(88.dp),
                            onClick = {
                            appState.navigate(profileNavigationRoute)
                            // SnackbarManager.showMessage(SnackbarMessage.StringSnackbar("User profile"))
                        }) {

                            Image(
                                painter = painterResource(id = R.drawable.profile),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(88.dp),
                                contentScale = ContentScale.FillWidth,
                            )
                        }
                    },
                )
            }
        },
        snackbarHost = {
            SnackbarHost(
                hostState = appState.snackbarHostState,
                modifier = Modifier.padding(8.dp),
                snackbar = { snackbarData ->
                    Snackbar(
                        snackbarData, contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                }
            )
        },
        content = { innerPadding ->
            val start = if (isLoggedIn) {
                 remember { mutableStateOf(homeNavigationRoute) }
            } else {
                remember { mutableStateOf(loginNavigationRoute) }
            }
            MestrollNavHost(
                innerPadding,
                start.value,
                appState,
                putLogIn
            )
        }
    )
}

@Composable
fun MestrollNavHost(
    innerPadding: PaddingValues,
    startRoute: String,
    appState: MestrollAppState,
    putLogIn: (Boolean) -> Unit
) {
    NavHost(
        navController = appState.navController,
        startDestination = startRoute,
    ) {
        composable(homeNavigationRoute) { MainRoute(
            innerPadding,
            {
                appState.navigate(absentNavigationRoute)
            }
        ) }

        composable(absentNavigationRoute) {
            AbsentScreen(
                onBackClick = {
                    appState.popUp()
                }
            )
        }
        composable(profileNavigationRoute) {
            ProfileRoute(
                onBackClick = {
                    appState.popUp()
                }
            )
        }
        composable(loginNavigationRoute) {
            LoginRoute(
                onLogIn = {
                    putLogIn(true)
                     appState.navigateAndPopUp(it, loginNavigationRoute)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MestrollTheme {
        MestrollApp(
            false,
            {})
    }
}