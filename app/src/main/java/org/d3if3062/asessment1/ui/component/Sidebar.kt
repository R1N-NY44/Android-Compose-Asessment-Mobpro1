package org.d3if3062.asessment1.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.d3if3062.asessment1.R

@Composable
fun Sidebar(drawerState: DrawerState, modifier: Modifier){
    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Rtl
    ) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            modifier = Modifier.padding(top = 64.dp),
//            modifier = modifier
//                .size(width = 200.dp , height = 500.dp),
            scrimColor = Color.Black.copy(alpha = 0.5f),
            drawerContent = {
                ModalDrawerSheet {
                    Text("Drawer title", modifier = Modifier.padding(12.dp))
                    NavigationDrawerItem(
                        modifier = Modifier
//                            .size(width = 300.dp, height = 600.dp),
                            .padding(12.dp),

                        label = {
                            Text(text = "Drawer Item");
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = R.drawable.outline_auto_awesome_mosaic_24),
                                contentDescription = "Localized description"
                            )
                        },
                        selected = true,
                        onClick = { /*TODO*/ }
                    )
                    NavigationDrawerItem(
                        label = { Text(text = "Drawer Item") },
                        selected = false,
                        onClick = { /*TODO*/ }
                    )
                    NavigationDrawerItem(
                        label = { Text(text = "Drawer Item") },
                        selected = false,
                        onClick = { /*TODO*/ }
                    )
                    Divider()

                }
            },
        ){
            //content
        }
    }
}