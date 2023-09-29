package com.mest.mestroll

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.app.ActivityCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import com.mest.mestroll.core.data.local.preferences.Preferences
import com.mest.mestroll.ui.screens.MestrollApp
import com.mest.mestroll.ui.theme.MestrollTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var preferences: Preferences
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MestrollTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RequestPermission(
                        permission = Manifest.permission.ACCESS_FINE_LOCATION.toString(),
                        {
                            preferences.putLat(it)
                        },
                        {
                            preferences.putLng(it)
                        }
                    )
                    MestrollApp(
                        preferences.getIsLoggedIn(),
                        putLogIn = {
                            preferences.isLoggedIn(it)
                        }
                    )
                }
            }
        }
    }

    @ExperimentalPermissionsApi
    @Composable
    fun RequestPermission(
        permission: String,
        onLat: (String) -> Unit,
        onLng: (String) -> Unit,
        rationaleMessage: String = "To use this app's functionalities, you need to give us the permission.",
    ) {
        val permissionState = rememberPermissionState(permission)

        HandleRequest(
            permissionState = permissionState,
            deniedContent = { shouldShowRationale ->
                PermissionDeniedContent(
                    rationaleMessage = rationaleMessage,
                    shouldShowRationale = shouldShowRationale
                ) { permissionState.launchPermissionRequest() }
            },
            content = {
                /*   Content(
                       text = "PERMISSION GRANTED!",
                       showButton = false
                       save location into share preferences
                   ) {}*/

                val location =  LocationServices.getFusedLocationProviderClient(this)
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return@HandleRequest
                }
                location.lastLocation.addOnSuccessListener {
                    if (it != null){
                        val mLatitude = it.latitude;
                        val mLongitude = it.longitude;
                        // save to phone
                        onLat(
                            mLatitude.toString()
                        )

                        onLng(
                            mLongitude.toString()
                        )

                        Log.d("TAG", "Location... RequestPermission: mLatitude ${mLatitude} mLongitude ${mLongitude}")
                    }
                }
            }
        )
    }

    @ExperimentalPermissionsApi
    @Composable
    fun HandleRequest(
        permissionState: PermissionState,
        deniedContent: @Composable (Boolean) -> Unit,
        content: @Composable () -> Unit
    ) {
        when (permissionState.status) {
            is PermissionStatus.Granted -> {
                content()
            }
            is PermissionStatus.Denied -> {
                deniedContent((permissionState.status as PermissionStatus.Denied).shouldShowRationale)
            }
        }
    }

    @Composable
    fun Content(showButton: Boolean = true, onClick: () -> Unit) {
        if (showButton) {
            val enableLocation = remember { mutableStateOf(true) }
            if (enableLocation.value) {
                CustomDialogLocation(
                    title = "Turn On Location Service",
                    desc = "For easy attendance taking.\n\nGive this app a permission to proceed. If it doesn't work, then you'll have to do it manually from the settings.",
                    enableLocation,
                    onClick
                )
            }
        }
    }

    @ExperimentalPermissionsApi
    @Composable
    fun PermissionDeniedContent(
        rationaleMessage: String,
        shouldShowRationale: Boolean,
        onRequestPermission: () -> Unit
    ) {

        if (shouldShowRationale) {

            AlertDialog(
                onDismissRequest = {},
                title = {
                    Text(
                        text = "Permission Request",
                        style = TextStyle(
                            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                text = {
                    Text(rationaleMessage)
                },
                confirmButton = {
                    Button(onClick = onRequestPermission) {
                        Text("Give Permission")
                    }
                }
            )

        }
        else {
            Content(onClick = onRequestPermission)
        }
    }

    @Composable
    fun CustomDialogLocation(
        title: String? = "Message",
        desc: String? = "Your Message",
        enableLocation: MutableState<Boolean>,
        onClick: () -> Unit
    ) {
        Dialog(
            onDismissRequest = { enableLocation.value = false}
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 20.dp)
                    // .width(300.dp)
                    // .height(164.dp)
                    .background(
                        color = MaterialTheme.colorScheme.onPrimary,
                        shape = RoundedCornerShape(25.dp, 5.dp, 25.dp, 5.dp)
                    )
                    .verticalScroll(rememberScrollState())

            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    //.........................Image: preview
                    Image(
                        painter = painterResource(id = R.drawable.logooo),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        /*  colorFilter  = ColorFilter.tint(
                              color = MaterialTheme.colorScheme.primary
                          ),*/
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .height(320.dp)
                            .fillMaxWidth(),

                        )
                    //.........................Spacer
                    //.........................Text: title
                    Text(
                        text = title!!,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            //  .padding(top = 5.dp)
                            .fillMaxWidth(),
                        letterSpacing = 2.sp,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    //.........................Text : description
                    Text(
                        text = desc!!,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                            .fillMaxWidth(),
                        letterSpacing = 1.sp,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    //.........................Spacer
                    Spacer(modifier = Modifier.height(24.dp))

                    //.........................Button : OK button
                    val cornerRadius = 16.dp
                    val gradientColors = listOf(Color(0xFFff669f), Color(0xFFff8961))
                    val roundedCornerShape = RoundedCornerShape(topStart = 30.dp,bottomEnd = 30.dp)

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 32.dp, end = 32.dp),
                        onClick=onClick,
                        contentPadding = PaddingValues(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(cornerRadius)
                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    brush = Brush.horizontalGradient(colors = gradientColors),
                                    shape = roundedCornerShape
                                )
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text ="Enable",
                                fontSize = 20.sp,
                                color = Color.White
                            )
                        }
                    }


                    //.........................Spacer
                    Spacer(modifier = Modifier.height(12.dp))


                    TextButton(onClick = {
                        enableLocation.value = false
                    }) { Text("Cancel", style = MaterialTheme.typography.labelLarge) }


                    Spacer(modifier = Modifier.height(24.dp))

                }
            }
        }
    }
}
