package org.d3if3062.asessment1.navigation_controller

sealed class Screen(val route: String) {

    object Base : Screen("home")
    object Experiment : Screen("ExperimentLevel")
    object Task : Screen("Task")
    object History : Screen("History")

    object AddTask : Screen("addTask")
    object DetailsTask : Screen("Task/detail/{taskId}") {
        // Fungsi ekstensi untuk menyertakan taskId saat membuat rute
        fun withTaskId(taskId: Long): String {
            val routeWithId = route.replace("{taskId}", taskId.toString())
            println("DetailsTask: TaskId included in route: $routeWithId") // Buat nge cek route dengan taskId
            return routeWithId
        }
    }

    object EditTask : Screen("Task/detail/edit/{taskId}") {
        // Fungsi ekstensi untuk menyertakan taskId saat membuat rute
        fun withTaskId(taskId: Long): String {
            val routeWithId = route.replace("{taskId}", taskId.toString())
            println("EditTask: TaskId included in route: $routeWithId") // Buat nge cek route dengan taskId
            return routeWithId
        }
    }


    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}