package org.d3if3062.asessment1.navigation_controller

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.d3if3062.asessment1.ui.content.HistoryTaskScreen
import org.d3if3062.asessment1.ui.screen.AddTaskScreen
import org.d3if3062.asessment1.ui.content.ListTaskScreen

@Composable
fun NaviationGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Task.route
    ) {
        composable(route = Screen.Task.route) {
            ListTaskScreen()
        }
        composable(route = Screen.History.route) {
            HistoryTaskScreen()
        }
        composable(route = Screen.AddTask.route) {
            AddTaskScreen(navController)
        }
    }
}