package org.d3if3062.asessment1.navigation_controller

sealed class Screen(val route: String) {

    object Base : Screen("home")
    object Task : Screen("Task")
    object History : Screen("History")
    object AddTask : Screen("history/addTask")
    object TaskDetail : Screen("task/detail/{taskId}")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}