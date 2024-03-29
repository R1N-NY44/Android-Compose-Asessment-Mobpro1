package org.d3if3062.asessment1.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.d3if3062.asessment1.model.ListTaskModel
import org.d3if3062.asessment1.navigation_controller.Screen

@Composable
fun DetailsTaskScreen(navController: NavHostController, viewModel: ListTaskModel, taskId: Long) {
    // Pencetakan taskId untuk memastikan nilai tidak null
    println("Task ID in DetailsTaskScreen: $taskId")

    // Cetak taskId untuk memastikan nilainya tidak null
    println("Task ID: $taskId")

    // Menggunakan State untuk menyimpan informasi tugas
    val task by viewModel.data.observeAsState()

    // Menemukan tugas dengan ID yang sesuai
    val selectedTask = task?.find { it.id == taskId }

    // Menampilkan detail tugas jika tugas ditemukan
    if (selectedTask != null) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Details Task Screen")
            Text(text = "Task Title: ${selectedTask.judul}")
            Text(text = "Task Deadline: ${selectedTask.deadLine}")
            Text(text = "Task Description: ${selectedTask.description}")
            Text(text = "Task Status: ${if (selectedTask.status) "Completed" else "Not Completed"}")
            Button(onClick = {
                // Navigasi ke rute EditTask dengan menyertakan taskId
                navController.navigate(Screen.EditTask.withTaskId(taskId))
            }) {
                Text(text = "Edit Task")
            }
            Button(onClick = {
                // Panggil fungsi untuk menandai tugas sebagai selesai
                selectedTask?.let { task ->
                    viewModel.markTaskAsCompleted(task.id)
                }
            }) {
                Text(text = "Mark as Completed")
            }
        }
    } else {
        // Menampilkan pesan jika tugas tidak ditemukan
        Text(text = "Task not found.")
    }
}