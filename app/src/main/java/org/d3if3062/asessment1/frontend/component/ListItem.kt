package org.d3if3062.asessment1.frontend.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import org.d3if3062.asessment1.R
import org.d3if3062.asessment1.backend.database.model.TodoList
import org.d3if3062.asessment1.backend.logic.calculateRemainingTime
import org.d3if3062.asessment1.backend.logic.calculateRemainingTimeString
import org.d3if3062.asessment1.backend.navigation_controller.Screen
import java.text.SimpleDateFormat
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ListItem(navController: NavHostController, todoList: TodoList, onClick: () -> Unit) {
    val remainingTime = remember { mutableStateOf("") }
    val context = LocalContext.current

    // Coroutine untuk pembaruan waktu
    LaunchedEffect(Unit) {
        while (true) {
            val difference = calculateRemainingTime(todoList.deadLine)
            val remainingTimeText = calculateRemainingTimeString(context, difference)
            remainingTime.value = remainingTimeText
            delay(1000) // Menunggu 1 detik sebelum menghitung ulang
        }
    }

    val inputFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    val outputFormat = SimpleDateFormat("EEEE, dd MMMM yyyy - (HH:mm)", Locale.getDefault())
    val date = inputFormat.parse(todoList.deadLine)
    val formattedDate = outputFormat.format(date)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(Screen.DetailsTask.withTaskId(todoList.id))
            }
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = todoList.title ,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = todoList.description,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = formattedDate,
        )
        Text(
            text = stringResource(id = R.string.deadline_remaining) + remainingTime.value,
            style = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ListItemHistory(navController: NavHostController, todoList: TodoList, onClick: () -> Unit) {
    val remainingTime = remember { mutableStateOf("") }
    val context = LocalContext.current

    val inputFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    val outputFormat = SimpleDateFormat("EEEE, dd MMMM yyyy - (HH:mm)", Locale.getDefault())
    val date = inputFormat.parse(todoList.deadLine)
    val formattedDate = outputFormat.format(date)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(Screen.DetailsTask.withTaskId(todoList.id))
            }
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = todoList.title ,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = todoList.description,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = formattedDate,
        )
    }
}
