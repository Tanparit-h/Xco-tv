package com.zicure.xcotv.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.items
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.Text
import com.zicure.xcotv.utils.DrawerScreen
import com.zicure.xcotv.utils.fontFCIconic
import com.zicure.xcotv.utils.grayFFAAAAAA
import com.zicure.xcotv.utils.screenList

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun VideoDrawer(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    navController: NavController,
    onClickAction: (route: String) -> Unit,
    builder: @Composable () -> Unit
) {
    val navItemColor = NavigationDrawerItemDefaults.colors(
//        selectedContainerColor = Color.Transparent,
        unselectedContainerColor = Color.Transparent
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .width(280.dp),
                drawerShape = RectangleShape,
                drawerContainerColor = Color.Black.copy(alpha = 0.7f)
            ) {
                Column(
                    Modifier
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    NavigationDrawerItem(
                        selected = navController.currentDestination?.route == DrawerScreen.UserAccountDrawer.route,
                        colors = navItemColor,
                        shape = RectangleShape,
                        label = {
                            Text(
                                stringResource(id = DrawerScreen.UserAccountDrawer.title),
                                color = grayFFAAAAAA,
                                fontFamily = fontFCIconic,
                                fontWeight = FontWeight.Normal,
                                fontSize = 24.sp
                            )
                        },
                        icon = {
                            Icon(
                                modifier = Modifier
                                    .size(36.dp),
                                imageVector = DrawerScreen.UserAccountDrawer.icon,
                                contentDescription = null,
                                tint = grayFFAAAAAA
                            )
                        },
                        onClick = {
                            onClickAction(DrawerScreen.UserAccountDrawer.route)
                        },
                    )

                    TvLazyColumn(
                        Modifier
                            .selectableGroup(),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(
                            10.dp,
                            Alignment.CenterVertically
                        )
                    ) {

                        items(screenList, key = { it.route }) { screen ->
                            NavigationDrawerItem(
                                selected = navController.currentDestination?.route == screen.route,
                                colors = navItemColor,
                                shape = RectangleShape,
                                label = {
                                    Text(
                                        stringResource(id = screen.title),
                                        color = grayFFAAAAAA,
                                        fontFamily = fontFCIconic,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 24.sp
                                    )
                                },
                                icon = {
                                    Icon(
                                        modifier = Modifier
                                            .size(36.dp),
                                        imageVector = screen.icon,
                                        contentDescription = null,
                                        tint = grayFFAAAAAA
                                    )
                                },
                                onClick = {
                                    onClickAction(screen.route)
                                }
                            )
                        }
                    }

                    NavigationDrawerItem(
                        selected = navController.currentDestination?.route == DrawerScreen.LogOutDrawer.route,
                        colors = navItemColor,
                        shape = RectangleShape,
                        label = {
                            Text(
                                stringResource(id = DrawerScreen.LogOutDrawer.title),
                                color = grayFFAAAAAA,
                                fontFamily = fontFCIconic,
                                fontWeight = FontWeight.Normal,
                                fontSize = 24.sp
                            )
                        },
                        icon = {
                            Icon(
                                modifier = Modifier
                                    .size(36.dp),
                                imageVector = DrawerScreen.LogOutDrawer.icon,
                                contentDescription = null,
                                tint = grayFFAAAAAA
                            )
                        },
                        onClick = {
                            onClickAction(DrawerScreen.LogOutDrawer.route)
                        }
                    )
                }
            }
        },
        scrimColor = Color.LightGray.copy(alpha = 0.7f),
        modifier = modifier
    ) {
        builder()
    }
}