package com.example.keepfit.History.View

import android.icu.util.Calendar
import android.widget.CalendarView
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Calendar
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.keepfit.ui.theme.*
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

import java.util.*

@Composable
@Preview
fun HistoryScreen() {
Box(modifier = Modifier
    .fillMaxSize()
    .background(MainBack)

){
    Column {
        HeaderHis()
        CalanderAndTime()
        HistoryPage(dataList = listOf(
            Triple("name1", "10000", "12/05/23"),
            Triple("name2", "10000", "11/05/23"),
            Triple("name3", "7500", "10/05/23"),
            Triple("name4", "10000", "09/05/23"),
            Triple("name5", "10000", "07/05/23"),
            Triple("name6", "10000", "29/04/23"),

        ))
    }
}
}


@Composable
fun HistoryPage(dataList: List<Triple<String, String, String>>) {

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 0.dp, bottom = 58.dp)
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MainBack)

        )
        {
            items(dataList) { item ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(25.dp))
//                        .border(width = 4.dp, color = Color.Black)
                        .shadow(elevation = 3.dp,),

                    ) {
                    Row (
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp)
                            .background(darkBlue)
                            .height(80.dp)
                    ) {
                        Column(modifier = Modifier .padding(1.dp)) {
                            Text(
                                text = item.first,
//                                style = MaterialTheme.typography.h6,
                                style = TextStyle(
                                  fontFamily = FontFamily.Serif,
                                    fontSize = 20.sp,
                                    color = ButtonOrange,
                                    fontWeight = FontWeight.Bold
                                ),
                                modifier = Modifier.padding(start = 10.dp)
                            )
                            Text(
                                text = item.second,
                                color = LLightOrange,
                                style = MaterialTheme.typography.h5,
                                modifier = Modifier.padding(start = 10.dp)
                            )

                        }
                        Text(
                            text = item.third,
                            color = Color.White,

                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.padding(end = 10.dp),
                         )
                    }

                }
            }
        }
    }
}






@Composable
fun HeaderHis(
){
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .background(HeaderOrange)
            .height(80.dp)
    ) {
        Column {
            Text(
                text = "History",
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}


@Composable
fun CalanderAndTime(){

        var pickedDate by remember {
            mutableStateOf(LocalDate.now())
        }
        var pickedTime by remember {
            mutableStateOf(LocalTime.NOON)
        }
        val formattedDate by remember {
            derivedStateOf {
                DateTimeFormatter
                    .ofPattern("MMM dd yyyy")
                    .format(pickedDate)
            }
        }
        val formattedTime by remember {
            derivedStateOf {
                DateTimeFormatter
                    .ofPattern("hh:mm")
                    .format(pickedTime)
            }
        }

        val dateDialogState = rememberMaterialDialogState()
        val timeDialogState = rememberMaterialDialogState()

        Column(
            modifier = Modifier
//                .background(Color.Cyan)
                .fillMaxWidth()
                .padding(top = 5.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                dateDialogState.show()
            }) {
                Text(text = "Pick date")
            }
            Text(text = formattedDate)
//            Spacer(modifier = Modifier.height(16.dp))
//            Button(onClick = {
//                timeDialogState.show()
//            }) {
//                Text(text = "Pick time")
//            }
//            Text(text = formattedTime)
        }

    MaterialDialog(
        dialogState = dateDialogState,
        properties = DialogProperties(
//            dismissOnBackPress = true
        ),
    buttons = {
        positiveButton(text = "Ok"){

        }
        negativeButton(text = "Cancel")

    }
    ) {
this.datepicker(
    initialDate = LocalDate.now(),
    title = "Pick a date!!",
//    colors = DatePickerDefaults.colors(),
//allowedDateValidator = {
//it.dayOfMonth % 2 == 1
// }

){
    //changing the picked date to the state date
    pickedDate = it
}
    }

    MaterialDialog(
        dialogState = timeDialogState,
        properties = DialogProperties(
//            dismissOnBackPress = true
        ),
        buttons = {
            positiveButton(text = "Ok"){

            }
            negativeButton(text = "Cancel")
        }
    ) {
        this.timepicker(
            initialTime = LocalTime.NOON,
            title = "Pick a time!!",
            timeRange = LocalTime.MIDNIGHT..LocalTime.NOON

        ){
            //changing the picked date to the state date
            pickedTime = it
        }
    }


}




