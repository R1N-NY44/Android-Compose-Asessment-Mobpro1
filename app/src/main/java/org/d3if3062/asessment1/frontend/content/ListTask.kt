package org.d3if3062.asessment1.frontend.content

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.d3if3062.asessment1.R
import org.d3if3062.asessment1.backend.database.MainViewModel
import org.d3if3062.asessment1.frontend.component.ListItem

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ListTaskScreen(navController: NavHostController, viewModel: MainViewModel) {
//    val allTasks by remember { viewModel.getAllTasks() }.observeAsState(initial = emptyList())
    val allTasks by viewModel.data.collectAsState()

    val incompleteTasks = allTasks.filter { !it.status } // Filter hanya tugas dengan status false

    if (incompleteTasks.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(painter = painterResource(id = R.drawable.empty), tint = MaterialTheme.colorScheme.secondary, contentDescription = stringResource(id = R.string.empty_list))
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