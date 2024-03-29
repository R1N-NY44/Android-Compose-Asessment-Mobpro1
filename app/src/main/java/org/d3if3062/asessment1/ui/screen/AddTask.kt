package org.d3if3062.asessment1.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3062.asessment1.R
import org.d3if3062.asessment1.ui.theme.Asessment1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(navController: NavHostController) {
    var taskTitle by rememberSaveable { mutableStateOf("") }
    var emptyTitle by rememberSaveable { mutableStateOf(false) }
    var deadLine by rememberSaveable { mutableStateOf("") }
    var emptyDeadLine by rememberSaveable { mutableStateOf(false) }
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(id = R.string.add_task_title)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() })
                    {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = taskTitle,
                singleLine = true,
                isError = emptyTitle,
                onValueChange = { taskTitle = it },
                label = { Text("Add Title") },
                trailingIcon = {ErrorIcon(emptyTitle)},
                supportingText = {ErrorHint(emptyTitle)},
                leadingIcon = { Icon(painter = painterResource(id = R.drawable.assignment), contentDescription = null)
                }
            )


        }
    }

}

/*----------------[Exception]----------------*/
@Composable
fun ErrorHint(isError: Boolean){
    if (isError){
        Text(
            text = stringResource(id = R.string.invalid_input),
            style = MaterialTheme.typography.bodySmall,
            color = Color.Red
        )
    }
}
@Composable
fun ErrorIcon(isError: Boolean){
    if (isError){
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    }
}
/*----------------[Exception]----------------*/

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun AddTaskPreview() {
    Asessment1Theme {
        AddTaskScreen(navController = rememberNavController())
    }
}

