package com.mest.mestroll.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mest.mestroll.R
import com.mest.mestroll.core.domain.EnterRoom
import com.mest.mestroll.ui.theme.MestrollTheme
import com.mest.mestroll.ui.theme.absentRoomBg
import com.mest.mestroll.ui.theme.confirm
import com.mest.mestroll.ui.theme.dismiss
import com.mest.mestroll.ui.theme.roomBg
import com.mest.mestroll.ui.vm.MainViewModel

@Composable
fun MainRoute(
    innerPadding: PaddingValues,
    onNavigate: () -> Unit,
    mainViewModel: MainViewModel = hiltViewModel()
) {

    MainScreen(
        innerPadding,
        {
            mainViewModel.enterRoom(it)
        },
        onNavigate,
        mainViewModel.compareLocation()
    )
}

@Composable
fun MainScreen(
    innerPadding: PaddingValues,
    enterRoom: (EnterRoom) -> Unit,
    onNavigate: () -> Unit,
    compareLocation: Boolean
) {
    LazyColumn(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {
        item {
            Text(
                text = "Pick a room",
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle.Default,
                fontWeight = FontWeight.Medium,

                // on below line we are specifying font family.
                fontFamily = FontFamily.Default,

                // on below line we are specifying font style
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center,

                // on below line we are specifying font size.
                fontSize = 32.sp
            )
        }
        items(rooms) { room ->
            RoomItem(
                room,
                enterRoom,
                onNavigate,
                compareLocation
            )
        }
    }
}

@Composable
fun RoomItem(
    room: Room,
    enterRoom: (EnterRoom) -> Unit,
    onNavigate: () -> Unit,
    compareLocation: Boolean
) {
    val paddingModifier = Modifier
        .fillMaxWidth()
        .padding(24.dp)

    var openDialog by remember {
        mutableStateOf(false)
    }

    var openLocationDialog by remember {
        mutableStateOf(false)
    }

    if (openLocationDialog) {
        AlertDialog(
            title = {
                Text(text = "Wrong Location")
            },
            text = {
                Text(text = "You muct be at the mest compuse to to rol call")
            },
            onDismissRequest = {
                openLocationDialog = false
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openLocationDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.onBackground,
                        containerColor = dismiss
                    )
                ) {
                    Text("Dismiss")
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openLocationDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.onBackground,
                        containerColor = confirm
                    )
                ) {
                    Text("Confirm")
                }
            }
        )
    }

    if (openDialog) {
        AlertDialog(
            title = {
                Text(text = "Room 1")
            },
            text = {
                Text(text = "Do you want to confirm entry into room one?")
            },
            onDismissRequest = {
                openDialog = false
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.onBackground,
                        containerColor = dismiss
                    )
                ) {
                    Text("Dismiss")
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        enterRoom(EnterRoom("", 0))
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = MaterialTheme.colorScheme.onBackground,
                        containerColor = confirm
                    )
                ) {
                    Text("Confirm")
                }
            }
        )
    }

    val bg = if (room.id == 4) {
        absentRoomBg
    } else {
        roomBg
    }

    Card(
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = paddingModifier
            .clickable {
                // check if the locations match
                // pass lat and lng
                if (compareLocation) {
                    if (room.id == 4) {
                        onNavigate()
                    } else {
                        openDialog = true
                    }
                } else {
                    openLocationDialog = true
                }
            }
            .size(
                312.dp, 100.dp
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .background(bg)
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = room.img),
                contentDescription = "",
                modifier = Modifier
                    .weight(1f)
                    .size(172.dp)
                    .clip(CircleShape)
            )

            Text(
                text = room.name,
                modifier = paddingModifier
                    .weight(3f),
                style = TextStyle.Default,
                fontWeight = FontWeight.SemiBold,

                // on below line we are specifying font family.
                fontFamily = FontFamily.Default,


                // on below line we are specifying font style
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Center,

                // on below line we are specifying font size.
                fontSize = 24.sp
            )

            Image(
                painter = painterResource(id = room.img),
                contentDescription = "",
                modifier = Modifier
                    .weight(1f)
                    .size(172.dp)
                    .clip(CircleShape)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MestrollTheme {
        MainScreen(PaddingValues(3.dp), {}, {}, false)
    }
}

val rooms = listOf<Room>(
    Room(
        1,
        "Room 1",
        R.drawable.imgg,
    ),
    Room(
        2,
        "Room 2",
        R.drawable.img
    ),
    Room(
        3,
        "Room 3",
        R.drawable.imgg,
    ),
    Room(
        4,
        "Absent",
        R.drawable.imgggg
    )
)

data class Room(
    val id: Int,
    val name: String,
    val img: Int,
)
