package org.d3if3062.asessment1.ui.content

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.d3if3062.asessment1.R
import org.d3if3062.asessment1.model.ListTaskModel
import org.d3if3062.asessment1.ui.component.ListItem

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HistoryTaskScreen1(navController: NavHostController, viewModel: ListTaskModel){
    val allTasks by remember { viewModel.getAllTasks() }.observeAsState(initial = emptyList())

    val incompleteTasks = allTasks.filter { it.status } // Filter hanya tugas dengan status false

    if (incompleteTasks.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.empty_list))
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