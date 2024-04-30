package org.d3if3062.asessment1.frontend.screen

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import org.d3if3062.asessment1.R
import org.d3if3062.asessment1.backend.database.MainViewModel
import org.d3if3062.asessment1.backend.logic.calculateRemainingTime
import org.d3if3062.asessment1.backend.navigation_controller.Screen
import org.d3if3062.asessment1.frontend.component.shareTodo
import org.d3if3062.asessment1.frontend.theme.Asessment1Theme
import java.text.SimpleDateFormat
import kotlin.math.abs

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalsTask(navController: NavHostController, viewModel: MainViewModel, taskId: Long) {
    val task = viewModel.getTaskById(taskId)

    val remainingTime = remember { mutableStateOf("") }
    val taskTitle by rememberSaveable { mutableStateOf(task?.title ?: "") }
    val description by rememberSaveable { mutableStateOf(task?.description ?: "") }
    val format = SimpleDateFormat("dd/MM/yyyy HH:mm")
    val currentDate = format.parse(task?.deadLine) // Ubah string menjadi Date
    val dateString by remember { mutableStateOf(SimpleDateFormat("dd/MM/yyyy").format(currentDate)) }
    val timeString by remember { mutableStateOf(SimpleDateFormat("HH:mm").format(currentDate)) }
    val context = LocalContext.current

    // Coroutine untuk pembaruan waktu
    LaunchedEffect(Unit) {
        while (true) {
            val difference = task?.let { calculateRemainingTime(it.deadLine) } ?: 0
            val remainingTimeText = calculateRemainingTimeStringDetails(context, difference)
            remainingTime.value = remainingTimeText
            delay(1000) // Menunggu 1 detik sebelum menghitung ulang
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(id = R.string.detail_task_title)) },
                actions = {
                    IconButton(onClick = {
                        task?.let {
                            val message = context.getString(R.string.share_Todo, it.title, it.deadLine, it.description)
                            shareTodo(context, message)
                        }
                    })
                    {
                        Icon(
                            painter = painterResource(id = R.drawable.share),
                            contentDescription = stringResource(id = R.string.share)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back)
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                modifier = Modifier.padding(bottom = 5.dp),
                text = { Text(stringResource(id = R.string.edit_task)) },
                icon = { Icon(Icons.Default.Edit, contentDescription = stringResource(id = R.string.edit)) },
                onClick = {
                    navController.navigate(Screen.EditTask.withTaskId(taskId))
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
        ) {
            Column {
                // TextField untuk judul tugas
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    value = taskTitle, readOnly = true, onValueChange = { },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.assignment),
                            contentDescription = null
                        )
                    }
                )

                // Button untuk memilih tanggal dan waktu deadline
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, start = 16.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                        shape = RoundedCornerShape(4.dp),
                        onClick = {}
                    ) {
                        Text(
                            text = dateString,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal
                            )
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.date),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(start = 6.dp)
                        )
                    }
                    //////////////////////////////////////////////
                    OutlinedButton(modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                        shape = RoundedCornerShape(4.dp),
                        onClick = {}
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = timeString,
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.time),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(start = 6.dp)
                            )
                        }
                    }
                }

                // Button untuk memilih tanggal dan waktu deadline
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, bottom = 12.dp, start = 16.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(modifier = Modifier
                        .weight(2f)
                        .height(48.dp),
                        shape = RoundedCornerShape(4.dp),
                        onClick = {}
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = remainingTime.value,
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.deadline),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(start = 6.dp)
                            )
                        }
                    }

                    OutlinedButton(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .height(48.dp),
                        shape = RoundedCornerShape(4.dp),
                        onClick = {
                            task?.let { task ->
                                viewModel.markTask(task.id)
                                navController.popBackStack()
                                navController.navigate(Screen.DetailsTask.withTaskId(taskId))
                            }
                        },
                        border = BorderStroke(1.dp, if (task?.status == false) Color(0xFF00FF00) else Color(0xFFFF0000))
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.done_mark),
                                contentDescription = null,
                                tint = if (task?.status == false) Color(0xFF00FF00) else Color(0xFFFF0000)
                            )
                        }
                    }


                }

                // TextField untuk deskripsi tugas
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    value = description,
                    readOnly = true,
                    onValueChange = { },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.assignment),
                            contentDescription = null
                        )
                    }
                )
            }
        }
    }

}


fun calculateRemainingTimeStringDetails(context: Context, difference: Long): String {
    val absDifference = abs(difference)
    val secondsInMinute = 60
    val secondsInHour = 3600
    val secondsInDay = 86400

    val days = absDifference / secondsInDay
    val hours = (absDifference % secondsInDay) / secondsInHour
    val minutes = ((absDifference % secondsInDay) % secondsInHour) / secondsInMinute
    val seconds = ((absDifference % secondsInDay) % secondsInHour) % secondsInMinute

    return buildString {
        if (difference < 0) {
            append("-")
        }
        if (days > 0) {
            append(context.getString(R.string.deadline_day, days))
        }
        if (hours > 0 || days > 0) {
            append(" ")
            append("$hours"+"h")
        }
        if (minutes > 0 || hours > 0 || days > 0) {
            append(" ")
            append("$minutes"+"m")
        }
        if (seconds > 0 || minutes > 0 || hours > 0 || days > 0) {
            append(" ")
            append("$seconds"+"s")
        }

    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Preview
@Composable
fun DetalsTask() {
    Asessment1Theme {
        val navController = rememberNavController()
        val viewModel = MainViewModel()
        EditTaskScreen(navController, viewModel, 1)
    }
}
