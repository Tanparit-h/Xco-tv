package com.zicure.xcotv.presentation.compose

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zicure.xcotv.presentation.compose.screen.FreeTVScreen
import com.zicure.xcotv.presentation.compose.screen.SelectLanguageScreen
import com.zicure.xcotv.presentation.main.MainViewModel
import com.zicure.xcotv.utils.DrawerScreen
import com.zicure.xcotv.utils.loadLocale

@Composable
fun MainNavigationDrawer(
    viewModel: MainViewModel,
    navController: NavHostController = rememberNavController(),
    navMedia: (intent: Intent) -> Unit
) {
    val mContext = LocalContext.current
    MainDrawer(
        navController = navController
    ) {
        composable(route = DrawerScreen.SearchScreenDrawer.route) {
            FreeTVScreen(viewModel, navMedia)
        }
        composable(route = DrawerScreen.FreeTVScreenDrawer.route) {
            FreeTVScreen(viewModel, navMedia)
        }
        composable(route = DrawerScreen.LanguageScreenDrawer.route) {
            SelectLanguageScreen {
                loadLocale(mContext)
            }
        }
    }
}