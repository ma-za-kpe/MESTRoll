package com.mest.mestroll.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mest.mestroll.core.data.local.preferences.Preferences
import com.mest.mestroll.core.domain.EnterRoom
import com.mest.mestroll.core.domain.EnterRoomUseCase
import com.mest.mestroll.core.domain.LocationUseCase
import com.mest.mestroll.core.utils.snackbar.SnackbarManager
import com.mest.mestroll.core.utils.snackbar.SnackbarMessage
import com.mest.mestroll.core.utils.snackbar.SnackbarMessage.Companion.toSnackbarMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val enterRoomUseCase: EnterRoomUseCase,
    val locationUseCase: LocationUseCase,
    val preferences: Preferences
): ViewModel() {

    fun compareLocation(): Boolean {
        launchCatching {
            locationUseCase(preferences.getLat(), preferences.getLng())
        }
       return preferences.getAddress().contains("12, Aluguingtugui Street, East Legon, Accra, Ayawaso West Municipal District, Greater Accra Region, Ghana")
    }

    val email = preferences.getEmail()

    fun saveEmail(email: String) {
        preferences.putEmail(email)
    }

    fun enterRoom(enterRoom: EnterRoom) {
        launchCatching {
            enterRoomUseCase(enterRoom)
        }

        SnackbarManager.showMessage(SnackbarMessage.StringSnackbar("Entering room"))
    }

    private val _performLocationAction: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val performLocationAction = _performLocationAction.asStateFlow()


    fun setPerformLocationAction(request: Boolean) {
        _performLocationAction.value = request
    }

    fun launchCatching(snackbar: Boolean = true, block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                if (snackbar) {
                    SnackbarManager.showMessage(throwable.toSnackbarMessage())
                }
            },
            block = block
        )
}