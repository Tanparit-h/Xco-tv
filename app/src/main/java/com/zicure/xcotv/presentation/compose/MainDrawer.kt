package com.zicure.xcotv.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.items
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.ModalNavigationDrawer
import androidx.tv.material3.NavigationDrawerItem
import androidx.tv.material3.NavigationDrawerItemDefaults
import androidx.tv.material3.NavigationDrawerItemScale
import androidx.tv.material3.Text
import androidx.tv.material3.rememberDrawerState
import com.zicure.xcotv.utils.DrawerScreen
import com.zicure.xcotv.utils.fontFCIconic
import com.zicure.xcotv.utils.grayFFAAAAAA
import com.zicure.xcotv.utils.screenList

//

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MainDrawer(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    builder: NavGraphBuilder.() -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    val collapsedDrawerItemWidth = 42.dp
    val paddingValue = 12.dp
    val scaleValue = 1.2f

    val navItemColor = NavigationDrawerItemDefaults.colors(
//        focusedContainerColor = Color.Transparent
//        selectedContainerColor = Color.Transparent,
//        selectedContentColor = Color.Transparent
    )
    val navShape = NavigationDrawerItemDefaults.shape(
        shape = RectangleShape
    )
    val navScale = NavigationDrawerItemScale(
        scale = scaleValue,
        focusedScale = scaleValue,
        pressedScale = scaleValue,
        selectedScale = scaleValue,
        disabledScale = scaleValue,
        focusedSelectedScale = scaleValue,
        focusedDisabledScale = scaleValue,
        pressedSelectedScale = scaleValue
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Column(
                Modifier
                    .fillMaxHeight()
                    .padding(4.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                NavigationDrawerItem(
                    selected = navController.currentDestination?.route == DrawerScreen.UserAccountDrawer.route,
                    colors = navItemColor,
                    shape = navShape,
                    scale = navScale,
                    onClick = {
                        navController.navigate(DrawerScreen.UserAccountDrawer.route)
                    },
                    leadingContent = {
                        Icon(
                            modifier = Modifier
                                .size(72.dp),
                            imageVector = DrawerScreen.UserAccountDrawer.icon,
                            contentDescription = null,
                            tint = grayFFAAAAAA
                        )
                    }
                ) {
                    Text(
                        stringResource(id = DrawerScreen.UserAccountDrawer.title),
                        color = grayFFAAAAAA,
                        fontFamily = fontFCIconic,
                        fontWeight = FontWeight.Normal,
                        fontSize = 24.sp
                    )
                }

                TvLazyColumn(
                    Modifier
                        .selectableGroup(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
                ) {

                    items(screenList, key = { it.route }) { screen ->
                        NavigationDrawerItem(
                            selected = navController.currentDestination?.route == screen.route,
                            shape = navShape,
                            scale = navScale,
                            colors = navItemColor,
                            onClick = {
//                                drawerState.setValue(DrawerValue.Closed)
                                navController.navigate(screen.route) {
                                    /* remove the previous Composable from the back stack  */
                                    popUpTo(navController.currentDestination?.route ?: "") {
                                        inclusive = true
                                    }
                                }
                            },
                            leadingContent = {
                                Icon(
                                    modifier = Modifier
                                        .size(72.dp),
                                    imageVector = screen.icon,
                                    contentDescription = null,
                                    tint = grayFFAAAAAA
                                )
                            }
                        ) {
                            Text(
                                stringResource(id = screen.title),
                                color = grayFFAAAAAA,
                                fontFamily = fontFCIconic,
                                fontWeight = FontWeight.Normal,
                                fontSize = 24.sp
                            )
                        }
                    }
                }

                NavigationDrawerItem(
                    selected = navController.currentDestination?.route == DrawerScreen.LogOutDrawer.route,
                    shape = navShape,
                    scale = navScale,
                    colors = navItemColor,
                    onClick = {
                        navController.navigate(DrawerScreen.LogOutDrawer.route)
                    },
                    leadingContent = {
                        Icon(
                            modifier = Modifier
                                .size(72.dp),
                            imageVector = DrawerScreen.LogOutDrawer.icon,
                            contentDescription = null,
                            tint = grayFFAAAAAA
                        )
                    }
                ) {
                    Text(
                        stringResource(id = DrawerScreen.LogOutDrawer.title),
                        color = grayFFAAAAAA,
                        fontFamily = fontFCIconic,
                        fontWeight = FontWeight.Normal,
                        fontSize = 24.sp
                    )
                }
            }

        },
        scrimBrush = Brush.linearGradient(
            colors = listOf(
                Color.Black, Color.Transparent
            ), tileMode = TileMode.Clamp
        ),
        modifier = modifier
    ) {
        NavHost(
            navController = navController,
            startDestination = DrawerScreen.FreeTVScreenDrawer.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = collapsedDrawerItemWidth + (paddingValue * 2))
        ) {
            builder()
        }
    }
}