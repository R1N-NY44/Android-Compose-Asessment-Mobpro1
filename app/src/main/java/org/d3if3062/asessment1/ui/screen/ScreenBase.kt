package org.d3if3062.asessment1.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import org.d3if3062.asessment1.R
import org.d3if3062.asessment1.navigation_controller.NaviationGraph
import org.d3if3062.asessment1.navigation_controller.Screen

// Di sederhanakan dengan menggunakan enum class, cara sebelumnya ada di file ScreenBaseOld.kt
enum class BottomNavigationScreen(
    val route: String,
    val iconResId: Int,
    val textResId: Int
) {
    TASK("Task", R.drawable.assignment, R.string.task),
    HISTORY("History", R.drawable.history, R.string.history)
}

enum class zoo(
    val nama: String,
    val jenis: String,
    val jenisMakanan: String
) {
    KUCING("Kucing", "Mamalia", "Karnivora"),
    KELINCI("Kelinci", "Mamalia", "Herbivora"),
    KERBAU("Kerbau", "Mamalia", "Herbivora")

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    //variable untuk bottom navigation
    val bottomNavigationItems = BottomNavigationScreen.entries.toTypedArray()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    //Double Routing cara sesat yg saya dapatkan dari eksperimen saya
    // :'v masih belum menemukan cara yg pas di internet
    //jika route aktif saat ini tidak terdapat pada bottomNavigationItems maka akan menampilkan default routing
    if (currentRoute !in bottomNavigationItems.map { it.route }) {
        NaviationGraph(navController)
    } else {

    //Default Routing
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            scrimColor = Color.Black.copy(alpha = 0.5f),
            drawerContent = {
                ModalDrawerSheet(
                    drawerShape = RectangleShape,
                    modifier = Modifier.requiredWidth(250.dp)
                ) {
                    Text("Drawer title", modifier = Modifier.padding(12.dp))
                    NavigationDrawerItem(
                        modifier = Modifier.padding(12.dp),
                        label = { Text(text = "Drawer Item") },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.outline_auto_awesome_mosaic_24),
                                contentDescription = "Localized description"
                            )
                        },
                        selected = true,
                        onClick = { /*TODO*/ }
                    )
                    Divider()
                }
            },
        ) {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = { Text(stringResource(id = R.string.app_name)) },
                            actions = {
                                IconButton(onClick = { scope.launch { drawerState.apply { if (isClosed) open() else close() } } })
                                {
                                    Icon(
                                        painter = painterResource(id = R.drawable.outline_auto_awesome_mosaic_24),
                                        contentDescription = stringResource(id = R.string.about_dev)
                                    )
                                }
                            },
                            navigationIcon = {
                                //Niatnya dibuatkan agar bisa mendeteksi mode hp (dark/light),  kemudian jika di tekan akan merubah mode aplikasi ke dark/light, Upcoming feature
                                IconButton(onClick = { /*TODO*/ })
                                {
                                    Icon(
                                        painter = painterResource(id = R.drawable.dark_mode),
                                        contentDescription = "Localized description"
                                    )
                                }
                            }

                        )
                    },
                    bottomBar = {
                        NavigationBar {
                            bottomNavigationItems.forEach { screen ->
                                NavigationBarItem(
                                    icon = {
                                        val iconPainter = painterResource(id = screen.iconResId)
                                        Icon(painter = iconPainter, contentDescription = null)
                                    },
                                    label = { Text(stringResource(id = screen.textResId)) },
                                    selected = currentRoute == screen.route,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.startDestinationId) {
                                                this.saveState = true
                                            }
                                            this.launchSingleTop = true
                                            this.restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    },
                    floatingActionButton = {
                        ExtendedFloatingActionButton(
                            text = { Text(stringResource(id = R.string.add_task)) },
                            icon = { Icon(Icons.Filled.Add, contentDescription = stringResource(id = R.string.add_task)) },
                            onClick = {
                                navController.navigate(Screen.AddTask.route)
                            }
                        )
                    }

                ) {

                    padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        NaviationGraph(navController)
                    }

                }
            }
        }
    }

    }

}
