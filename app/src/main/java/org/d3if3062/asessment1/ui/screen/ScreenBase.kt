package org.d3if3062.asessment1.ui.screen

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.room.util.copy
import kotlinx.coroutines.launch
import org.d3if3062.asessment1.R
import org.d3if3062.asessment1.navigation_controller.NaviationGraph
import org.d3if3062.asessment1.navigation_controller.Screen
import org.d3if3062.asessment1.ui.component.shareLink

// Di sederhanakan dengan menggunakan enum class, cara sebelumnya ada di file ScreenBaseOld.kt
enum class BottomNavigationScreen(
    val route: String,
    val iconResId: Int,
    val textResId: Int
) {
    TASK("Task", R.drawable.assignment, R.string.task),
    HISTORY("History", R.drawable.history, R.string.history)
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    //variable untuk bottom navigation
    val bottomNavigationItems = BottomNavigationScreen.entries.toTypedArray()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val context = LocalContext.current

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
                    modifier = Modifier.widthIn(max = LocalConfiguration.current.screenWidthDp.dp * 0.60f)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            val gradientColors = listOf(
                                Color(0xFF8A2BE2), // Purple color menggunakan kode hex
                                //Color(0xFFADD8E6), // Light Blue color menggunakan kode hex
                                Color(0xFF00FFFF) // Cyan color menggunakan kode hex
                            )
                            Image(
                                painter = painterResource(id = R.drawable.github),
                                contentDescription = stringResource(id = R.string.about_dev),
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .widthIn(max = LocalConfiguration.current.screenWidthDp.dp * 0.45f)
                                    .aspectRatio(1f) // menjaga gambar agar tetap proporsional
                                    .clip(CircleShape)
                                    .border(
                                        2.dp,
                                        Brush.linearGradient(colors = gradientColors),
                                        CircleShape
                                    )
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = stringResource(id = R.string.git_username),
                                style = TextStyle(brush = Brush.linearGradient(colors = gradientColors), fontSize = 38.sp)
                            )
                            Spacer(modifier = Modifier.height(86.dp))
                            Button(
                                onClick = {shareLink(context, url = context.getString(R.string.github_URL))},
                                modifier = Modifier
                                    .defaultMinSize(minWidth = 165.dp, minHeight = 38.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.git_button),
                                    fontSize = 16.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(18.dp))
                            Button(
                                onClick = {
                                    // Handle klik tombol untuk menautkan ke profil GitHub
                                    val text = context.getString(R.string.instagram_URL)
                                    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                                },
                                modifier = Modifier
                                    .defaultMinSize(minWidth = 165.dp, minHeight = 38.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.instagram_button),
                                    fontSize = 16.sp
                                )
                            }

                        }
                        Text(
                            text = stringResource(id = R.string.copy_right),
                            fontSize = 12.sp,
                            color = Color.Gray,
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(16.dp)
                        )
                    }
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
                                        contentDescription = ""
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
