package org.d3if3062.asessment1.navigation_controller

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.d3if3062.asessment1.model.ListTaskModel
import org.d3if3062.asessment1.ui.content.HistoryTaskScreen
import org.d3if3062.asessment1.ui.screen.AddTaskScreen
import org.d3if3062.asessment1.ui.content.ListTaskScreen
import org.d3if3062.asessment1.ui.screen.AnimalApp
import org.d3if3062.asessment1.ui.screen.DetailsTaskScreen
import org.d3if3062.asessment1.ui.screen.EditTaskScreen


@Composable
fun NaviationGraph(navController: NavHostController = rememberNavController(), viewModel: ListTaskModel = viewModel())  {
    NavHost(
        navController = navController,
        startDestination = Screen.Task.route
    ) {
        /*----------------[Main Route]----------------*/

        composable(route = Screen.Task.route) {
            ListTaskScreen(navController, viewModel)
        }
        composable(route = Screen.History.route) {
            HistoryTaskScreen(navController, viewModel)
            //AnimalApp()
        }

        /*----------------[Sub Route]----------------*/

        composable(route = Screen.DetailsTask.route) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toLongOrNull()
            taskId?.let { id ->
                println("Task ID in DetailsTask route: $id") // Mencetak nilai ID untuk memastikan tidak null
                DetailsTaskScreen(navController, viewModel, id)
            } ?: run {
                println("Failed to get Task ID in DetailsTask route")
            }
        }

        composable(route = Screen.AddTask.route) {
            AddTaskScreen(navController, viewModel)
        }

        composable(route = Screen.EditTask.route) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toLongOrNull()
            taskId?.let { id ->
                println("Task ID in DetailsTask route: $id") // Mencetak nilai ID untuk memastikan tidak null
                EditTaskScreen(navController, viewModel, id)
            } ?: run {
                println("Failed to get Task ID in DetailsTask route")
            }
        }


    }
}