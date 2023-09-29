package com.mest.mestroll.ui.screens.main

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mest.mestroll.R
import com.mest.mestroll.ui.theme.MestrollTheme
import com.mest.mestroll.ui.theme.absentRoomBg
import com.mest.mestroll.ui.theme.roomBg

@Composable
fun AbsentScreen(
    onBackClick: () -> Unit = {},
) {
    AbsentRoute(
        onBackClick
    )
}

@Composable
fun CheckButtonComponent() {
    val checked = remember { mutableStateOf(false) }
    val checkOptions = listOf("Breakfast", "lunch", "Dinner")

    Column(
        // we are using column to align our
        // imageview to center of the screen.
        modifier = Modifier
            .fillMaxWidth(),

        // below line is used for
        // specifying vertical arrangement.
        verticalArrangement = Arrangement.Center,

        // below line is used for
        // specifying horizontal arrangement.
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // we are displaying all our
        // radio buttons in column.
        Column(
            modifier = Modifier
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "What meals do you want?",
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle.Default,
                fontWeight = FontWeight.Medium,

                // on below line we are specifying font family.
                fontFamily = FontFamily.Default,


                // on below line we are specifying font style
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Start,

                // on below line we are specifying font size.
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            checkOptions.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = checked.value,
                        onCheckedChange = { isChecked -> checked.value = isChecked },
                        colors = CheckboxDefaults.colors(
                            checkedColor = MaterialTheme.colorScheme.primary,
                            uncheckedColor = MaterialTheme.colorScheme.primary
                        )
                    )
                    Text(text)
                }
            }

            Spacer(Modifier.height(32.dp))

            Button(
                onClick = {
                    //your onclick code here
                },
                shape = RoundedCornerShape(4.dp),
                colors =  ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onBackground,
                    containerColor = absentRoomBg
                ),
                modifier = Modifier
                    .size(232.dp, 80.dp)
            ) {
                Text(
                    text = "SUBMIT",
                    // on below line we are specifying style for our text
                    style = TextStyle.Default,
                    fontWeight = FontWeight.SemiBold,

                    // on below line we are specifying font family.
                    fontFamily = FontFamily.Default,


                    // on below line we are specifying font style
                    fontStyle = FontStyle.Normal,

                    // on below line we are specifying font size.
                    fontSize = 24.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RadioButtonComponent() {
    val radioOptions = listOf("Sick", "Other")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1]) }
    var visible by remember {
        mutableStateOf(false)
    }

    Column(
        // we are using column to align our
        // imageview to center of the screen.
        modifier = Modifier
            .fillMaxWidth(),

        // below line is used for
        // specifying vertical arrangement.
        verticalArrangement = Arrangement.Center,

        // below line is used for
        // specifying horizontal arrangement.
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // we are displaying all our
        // radio buttons in column.
        Column(
            modifier = Modifier
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Why are you missing class?",
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle.Default,
                fontWeight = FontWeight.Medium,

                // on below line we are specifying font family.
                fontFamily = FontFamily.Default,


                // on below line we are specifying font style
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Start,

                // on below line we are specifying font size.
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp))
            // below line is use to set data to
            // each radio button in columns.
            radioOptions.forEach { text ->
                Row(
                    Modifier
                        // using modifier to add max
                        // width to our radio button.
                        .fillMaxWidth()
                        // below method is use to add
                        // selectable to our radio button.
                        .selectable(
                            // this method is called when
                            // radio button is selected.
                            selected = (text == selectedOption),
                            // below method is called on
                            // clicking of radio button.
                            onClick = {
                                if (text == "Other") {
                                    onOptionSelected(text)
                                    visible = true
                                } else {
                                    onOptionSelected(text)
                                }
                            }
                        )
                        // below line is use to add
                        // padding to radio button.
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // below line is use to get context.
                    val context = LocalContext.current

                    // below line is use to
                    // generate radio button
                    RadioButton(
                        // inside this method we are
                        // adding selected with a option.
                        selected = (text == selectedOption),
                        modifier = Modifier,
                        onClick = {
                            // inside on click method we are setting a
                            // selected option of our radio buttons.
                            onOptionSelected(text)

                            // after clicking a radio button
                            // we are displaying a toast message.
                            Toast.makeText(context, text, Toast.LENGTH_LONG).show()
                        }
                    )
                    // below line is use to add
                    // text to our radio buttons.
                    Text(
                        text = text,
                        modifier = Modifier
                    )
                }
            }

            AnimatedVisibility(
                visible = visible
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                var text by remember { mutableStateOf(TextFieldValue("")) }
                TextField(
                    value = text,
                    onValueChange = { newText ->
                        text = newText
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                   placeholder = {
                       Text(text = "Please insert reason")
                   }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AbsentRoute(
    onBackClick: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        onBackClick()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                title = {
                    Text("Absent")
                }
            )
        },
        content = { ip ->

            LazyColumn(
                modifier = Modifier
                    .padding(ip)
                    .fillMaxSize()
            ) {
                item {
                    Text(
                        text = "Tell us more",
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

                item {
                    RadioButtonComponent()
                }

                item {
                    CheckButtonComponent()
                }
            }
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(ip),
//                horizontalAlignment = Alignment.CenterHorizontally,
//            ) {
//                Text(
//                    text = "Tell us more ...",
//                    modifier = Modifier.fillMaxWidth(),
//                    style = TextStyle.Default,
//                    fontWeight = FontWeight.Medium,
//
//                    // on below line we are specifying font family.
//                    fontFamily = FontFamily.Default,
//
//
//                    // on below line we are specifying font style
//                    fontStyle = FontStyle.Normal,
//                    textAlign = TextAlign.Center,
//
//                    // on below line we are specifying font size.
//                    fontSize = 32.sp
//                )
//
//                Spacer(Modifier.height(64.dp))
//
//                AbsentCard()
//
//                Spacer(Modifier.height(32.dp))
//
//                AbsentCard()
//
//                Spacer(Modifier.height(32.dp))
//
//                Button(
//                    onClick = {
//                        //your onclick code here
//                    },
//                    shape = RoundedCornerShape(4.dp),
//                    colors =  ButtonDefaults.buttonColors(
//                        contentColor = MaterialTheme.colorScheme.onBackground,
//                        containerColor = absentRoomBg
//                    ),
//                    modifier = Modifier
//                        .size(232.dp, 80.dp)
//                ) {
//                    Text(
//                        text = "SUBMIT",
//                        // on below line we are specifying style for our text
//                        style = TextStyle.Default,
//                        fontWeight = FontWeight.SemiBold,
//
//                        // on below line we are specifying font family.
//                        fontFamily = FontFamily.Default,
//
//
//                        // on below line we are specifying font style
//                        fontStyle = FontStyle.Normal,
//
//                        // on below line we are specifying font size.
//                        fontSize = 24.sp
//                    )
//                }
//            }
        }
    )
}

@Composable
fun AbsentCard() {
    Card(
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier
            .clickable {

            }
            .size(
                312.dp, 100.dp
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .background(roomBg)
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.imggg),
                contentDescription = "",
                modifier = Modifier
                    .weight(1f)
                    .size(80.dp)
                    .clip(CircleShape)
            )

            Text(
                text = "REASONS",
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
                painter = painterResource(id = R.drawable.dropdownarrow),
                contentDescription = "",
                modifier = Modifier
                    .weight(1f)
                    .size(24.dp, 16.dp)
                    .clip(CircleShape)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CheckbuttonComponentPreview() {
    MestrollTheme {
        CheckButtonComponent()
    }
}

@Preview(showBackground = true)
@Composable
fun RadioButtonComponentPreview() {
    MestrollTheme {
        RadioButtonComponent()
    }
}

@Preview(showBackground = true)
@Composable
fun AbsentPreview() {
    MestrollTheme {
        AbsentScreen()
    }
}


@Preview(showBackground = true)
@Composable
fun AbsentCardPreview() {
    MestrollTheme {
        AbsentCard()
    }
}
