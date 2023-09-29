package com.mest.mestroll.ui.screens.auth

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.mest.mestroll.R
import com.mest.mestroll.ui.screens.homeNavigationRoute
import com.mest.mestroll.ui.theme.LocalSpacing
import com.mest.mestroll.ui.theme.MestrollTheme
import com.mest.mestroll.ui.theme.blutexBg
import com.mest.mestroll.ui.vm.MainViewModel

@Composable
fun LoginRoute(
    onLogIn: (String) -> Unit,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    LoginScreen(
        onLogIn,
        {
            mainViewModel.saveEmail(it)
        }
    )
}

@Composable
fun LoginScreen(
    onLogIn: (String) -> Unit,
    saveEmail: (String) -> Unit,
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    val token = stringResource(R.string.default_web_client_id)
    val launcherNav = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        // navController.navigate(Screen.MainScreen.route)
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
    ) {
        try {
            val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                .getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
            FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // save data to room db
                        saveEmail(task.result.user?.email.toString())
                        Log.d("TAG", "email ${task.result.user?.email}")
                        // navigate
                        onLogIn(homeNavigationRoute)
                    }
                }
        }
        catch (e: ApiException) {
            Log.e("TAG", "GoogleSign in Failed", e)
        }
    }

    Box(
        Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.logtwo),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = Modifier
                    .size(208.dp, 205.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
            )

            Text(
                text = "Welcome to MESTROLL",
                style = TextStyle.Default,
                color = blutexBg,
                fontWeight = FontWeight.SemiBold,

                // on below line we are specifying font family.
                fontFamily = FontFamily.Default,


                // on below line we are specifying font style
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center,

                // on below line we are specifying font size.
                fontSize = 24.sp
            )

            Spacer(Modifier.height(32.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .clickable {
                        val gso = GoogleSignInOptions
                            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestIdToken(token)
                            .requestEmail()
                            .build()
                        val googleSignInClient = GoogleSignIn.getClient(context, gso)
                        launcher.launch(googleSignInClient.signInIntent)
                    }
                    .clip(RoundedCornerShape(spacing.spaceMedium))
                    .border(
                        1.dp,
                        blutexBg,
                        RoundedCornerShape(spacing.spaceMedium)
                    )
                    .size(324.dp, 56.dp)

            ) {
                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "",
                    modifier = Modifier
                        .size(24.dp),
                    contentScale = ContentScale.FillWidth,
                )

                Text(
                    text = "Log in with Google",
                    style = TextStyle.Default,
                    color = blutexBg,
                    fontWeight = FontWeight.SemiBold,

                    // on below line we are specifying font family.
                    fontFamily = FontFamily.Default,


                    // on below line we are specifying font style
                    fontStyle = FontStyle.Normal,
                    textAlign = TextAlign.Center,

                    // on below line we are specifying font size.
                    fontSize = 24.sp
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.logone),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            contentScale = ContentScale.FillWidth
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MestrollTheme {
        LoginScreen({ }, {})
    }
}
