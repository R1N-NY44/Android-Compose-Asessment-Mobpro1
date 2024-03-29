package org.d3if3062.asessment1.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3062.asessment1.R
import org.d3if3062.asessment1.model.ListTaskModel

@Composable
fun EditTaskScreen(navController: NavHostController, viewModel: ListTaskModel, taskId: Long) {
    val task = viewModel.getTaskById(taskId)

    var taskTitle by rememberSaveable { mutableStateOf(task?.judul ?: "") }
    var deadLine by rememberSaveable { mutableStateOf(task?.deadLine ?: "") }
    var description by rememberSaveable { mutableStateOf(task?.description ?: "") }
    var emptyTitle by rememberSaveable { mutableStateOf(false) }
    var emptyDeadLine by rememberSaveable { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = taskTitle,
            onValueChange = { taskTitle = it },
            label = { Text("Add Title") },
            isError = emptyTitle,
            trailingIcon = { ErrorIcon(emptyTitle) },
            supportingText = { ErrorHint(emptyTitle) }
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = deadLine,
            onValueChange = { deadLine = it },
            label = { Text("DD/MM/YYYY") },
            isError = emptyDeadLine,
            trailingIcon = { ErrorIcon(emptyDeadLine) },
            supportingText = { ErrorHint(emptyDeadLine) }
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = description,
            onValueChange = { description = it },
            label = { Text("To do Description") },
            isError = emptyTitle,
            trailingIcon = { ErrorIcon(emptyTitle) },
            supportingText = { ErrorHint(emptyTitle) }
        )

        // Button untuk menyimpan perubahan
        Button(
            onClick = {
                // Memperbarui tugas dengan nilai yang diisi
                task?.let { viewModel.updateTodoById(it.id, taskTitle, deadLine, description, it.status, it.createAt) }

                // Kembali ke layar sebelumnya
                navController.popBackStack()
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(text = "Save Changes")
        }
    }
}
