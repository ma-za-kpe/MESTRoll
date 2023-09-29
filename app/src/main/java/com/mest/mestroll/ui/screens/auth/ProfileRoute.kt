package com.mest.mestroll.ui.screens.auth

import android.graphics.Color
import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.smallTopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.mest.mestroll.R
import com.mest.mestroll.ui.theme.MestrollTheme
import com.mest.mestroll.ui.theme.blueColor
import com.mest.mestroll.ui.theme.cardGreen
import com.mest.mestroll.ui.theme.greenColor
import com.mest.mestroll.ui.theme.redColor
import com.mest.mestroll.ui.theme.yellowColor

@Composable
fun ProfileRoute(
    onBackClick: () -> Unit = {},
) {
    ProfileScreen(onBackClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
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
                    Text("Profile")
                }
            )
        },
        content = { ip ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(ip),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                UserProfile()
                Spacer(Modifier.height(16.dp))
                Chart()
            }
        }
    )
}

@Composable
fun UserProfile() {
    Column(
        modifier = Modifier
            .background(cardGreen.copy(alpha = 0.2f))
            .width(312.dp)
            .border(2.dp, cardGreen)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val image: Painter = painterResource(id = R.drawable.ic_launcher_background)
        Image(
            painter = image,
            contentDescription = "",
            modifier = Modifier
                .size(172.dp)
                .clip(CircleShape)
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Mark Twain",

            // on below line we are specifying style for our text
            style = TextStyle.Default,
            fontWeight = FontWeight.SemiBold,

            // on below line we are specifying font family.
            fontFamily = FontFamily.Default,


            // on below line we are specifying font style
            fontStyle = FontStyle.Normal,

            // on below line we are specifying font size.
            fontSize = 20.sp
        )
        Spacer(Modifier.height(4.dp))

        Text(
            text = "mark.twain@meltwateer.org",
            color = MaterialTheme.colorScheme.primary,

            // on below line we are specifying style for our text
            style = TextStyle.Default,
            fontWeight = FontWeight.Normal,

            // on below line we are specifying font family.
            fontFamily = FontFamily.Default,


            // on below line we are specifying font style
            fontStyle = FontStyle.Normal,

            // on below line we are specifying font size.
            fontSize = 20.sp
        )

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Avail(number = 54, title = "Present")
            Avail(number = 2, title = "Absent")
        }
    }
}

@Composable
fun Avail(
    number: Int,
    title: String
) {
    Column(
        modifier = Modifier
            .size(48.dp, 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = number.toString(),
            color = MaterialTheme.colorScheme.primary,
            // on below line we are specifying style for our text
            style = TextStyle.Default,
            fontWeight = FontWeight.SemiBold,

            // on below line we are specifying font family.
            fontFamily = FontFamily.Default,


            // on below line we are specifying font style
            fontStyle = FontStyle.Normal,

            // on below line we are specifying font size.
            fontSize = 20.sp
        )
//        Spacer(Modifier.height(4.dp))

        Text(
            text = title,
            // on below line we are specifying style for our text
            style = TextStyle.Default,
            fontWeight = FontWeight.Normal,

            // on below line we are specifying font family.
            fontFamily = FontFamily.Default,


            // on below line we are specifying font style
            fontStyle = FontStyle.Normal,

            // on below line we are specifying font size.
            fontSize = 12.sp
        )
    }
}

@Composable
fun Chart() {
    // on below line we are creating a column
    // and specifying a modifier as max size.
    Column(
        modifier = Modifier
            .background(cardGreen.copy(alpha = 0.2f))
            .width(312.dp)
            .border(2.dp, cardGreen)
            .padding(16.dp)
    ) {
        // on below line we are again creating a column
        // with modifier and horizontal and vertical arrangement
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // on below line we are creating a simple text
            // and specifying a text as Web browser usage share
            Text(
                text = "Statistics",

                // on below line we are specifying style for our text
                style = TextStyle.Default,

                // on below line we are specifying font family.
                fontFamily = FontFamily.Default,

                // on below line we are specifying font style
                fontStyle = FontStyle.Normal,

                // on below line we are specifying font size.
                fontSize = 20.sp
            )

            // on below line we are creating a column and
            // specifying the horizontal and vertical arrangement
            // and specifying padding from all sides.
            Column(
                modifier = Modifier
                    .padding(18.dp)
                    .size(320.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // on below line we are creating a cross fade and
                // specifying target state as pie chart data the
                // method we have created in Pie chart data class.
                Crossfade(targetState = getPieChartData) { pieChartData ->
                    // on below line we are creating an
                    // android view for pie chart.
                    AndroidView(factory = { context ->
                        // on below line we are creating a pie chart
                        // and specifying layout params.
                        PieChart(context).apply {
                            layoutParams = LinearLayout.LayoutParams(
                                // on below line we are specifying layout
                                // params as MATCH PARENT for height and width.
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT,
                            )
                            // on below line we are setting description
                            // enables for our pie chart.
                            this.description.isEnabled = false

                            // on below line we are setting draw hole
                            // to false not to draw hole in pie chart
                            this.isDrawHoleEnabled = false

                            // on below line we are enabling legend.
                            this.legend.isEnabled = true

                            // on below line we are specifying
                            // text size for our legend.
                            this.legend.textSize = 14F

                            // on below line we are specifying
                            // alignment for our legend.
                            this.legend.horizontalAlignment =
                                Legend.LegendHorizontalAlignment.CENTER

                            // on below line we are specifying entry label color as white.
                            this.setEntryLabelColor(resources.getColor(R.color.white))
                        }
                    },
                        // on below line we are specifying modifier
                        // for it and specifying padding to it.
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(5.dp), update = {
                            // on below line we are calling update pie chart
                            // method and passing pie chart and list of data.
                            updatePieChartWithData(it, pieChartData)
                        })
                }
            }
        }
    }
}

fun updatePieChartWithData(
    // on below line we are creating a variable
    // for pie chart and data for our list of data.
    chart: PieChart,
    data: List<PieChartData>
) {
    // on below line we are creating
    // array list for the entries.
    val entries = ArrayList<PieEntry>()

    // on below line we are running for loop for
    // passing data from list into entries list.
    for (i in data.indices) {
        val item = data[i]
        entries.add(PieEntry(item.value ?: 0.toFloat(), item.browserName ?: ""))
    }

    // on below line we are creating
    // a variable for pie data set.
    val ds = PieDataSet(entries, "")

    // on below line we are specifying color
    // int the array list from colors.
    ds.colors = arrayListOf(
        greenColor.toArgb(),
        blueColor.toArgb(),
        redColor.toArgb(),
        yellowColor.toArgb(),
    )
    // on below line we are specifying position for value
    ds.yValuePosition = PieDataSet.ValuePosition.INSIDE_SLICE

    // on below line we are specifying position for value inside the slice.
    ds.xValuePosition = PieDataSet.ValuePosition.INSIDE_SLICE

    // on below line we are specifying
    // slice space between two slices.
    ds.sliceSpace = 2f

    // on below line we are specifying text color
    ds.valueTextColor = Color.WHITE

    // on below line we are specifying
    // text size for value.
    ds.valueTextSize = 18f

    // on below line we are specifying type face as bold.
    ds.valueTypeface = Typeface.DEFAULT_BOLD

    // on below line we are creating
    // a variable for pie data
    val d = PieData(ds)

    // on below line we are setting this
    // pie data in chart data.
    chart.data = d

    // on below line we are
    // calling invalidate in chart.
    chart.invalidate()
}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    MestrollTheme {
        ProfileScreen({ })
    }
}


@Preview(showBackground = true)
@Composable
fun AvailPreview() {
    MestrollTheme {
        Avail(
            54,
            "Present"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserProfilePreview() {
    MestrollTheme {
        UserProfile()
    }
}

@Preview(showBackground = true)
@Composable
fun PieChartPreview() {
    MestrollTheme {
        Chart()
    }
}

data class PieChartData(
    var browserName: String?,
    var value: Float?
)

// on below line we are creating a method
// in which we are passing all the data.
val getPieChartData = listOf(
    PieChartData("Absent", 89.68F),
    PieChartData("Present", 9.60F)
)