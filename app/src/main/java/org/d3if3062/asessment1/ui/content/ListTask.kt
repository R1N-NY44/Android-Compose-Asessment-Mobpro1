package org.d3if3062.asessment1.ui.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import org.d3if3062.asessment1.R
import org.d3if3062.asessment1.model.ListTaskModel
import org.d3if3062.asessment1.model.TodoList
import org.d3if3062.asessment1.navigation_controller.Screen

@Composable
fun ListTaskScreen(navController: NavHostController, viewModel: ListTaskModel) {
    val allTasks by remember { viewModel.getAllTasks() }.observeAsState(initial = emptyList())

    val incompleteTasks = allTasks.filter { !it.status } // Filter hanya tugas dengan status false

    if (incompleteTasks.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.list_kosong))
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 84.dp)
        ) {
            items(incompleteTasks) { todo ->
                ListItem(navController, todo) { }
                Divider()
            }
        }
    }
}




@Composable
fun ListItem(navController: NavHostController, todoList: TodoList, onClick: () -> Unit) {
    // Cetak todoList.id untuk memastikan nilainya tidak null
    println("TodoList ID: ${todoList.id}")

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
            text = todoList.judul + "-" + todoList.id,
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
            text = todoList.deadLine + "-" + todoList.createAt,
        )
    }
}
