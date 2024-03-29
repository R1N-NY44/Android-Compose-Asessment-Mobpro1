package org.d3if3062.asessment1.ui.screen

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3062.asessment1.navigation_controller.Screen

@Composable
fun DetailsTaskScreen(navController: NavHostController = rememberNavController()) {
    Text(text = "Add Task Screen")
    Button(onClick = { navController.navigate(Screen.AddTask.route) }) {
        Text("To History Task")
    }
}