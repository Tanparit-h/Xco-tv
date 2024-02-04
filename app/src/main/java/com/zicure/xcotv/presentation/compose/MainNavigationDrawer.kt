package com.zicure.xcotv.presentation.compose

import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.tv.material3.DrawerState
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.zicure.xcotv.presentation.compose.layout.AlertDialogLayout
import com.zicure.xcotv.presentation.compose.screen.FreeTVScreen
import com.zicure.xcotv.presentation.compose.screen.SelectLanguageScreen
import com.zicure.xcotv.presentation.main.MainViewModel
import com.zicure.xcotv.utils.DataStorage
import com.zicure.xcotv.utils.DrawerScreen
import com.zicure.xcotv.utils.loadLocale
import kotlinx.coroutines.delay
import kotlin.system.exitProcess

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MainNavigationDrawer(
    viewModel: MainViewModel,
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState,
    navMedia: (intent: Intent) -> Unit,
    closeAction: () -> Unit
) {
    val mContext = LocalContext.current
    val openAlertDialog = remember { mutableStateOf(false) }
    MainDrawer(
        drawerState = drawerState,
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
        dialog(route = DrawerScreen.LogOutDrawer.route) {
            AlertDialogLayout(
                onDismissRequest = { openAlertDialog.value = false },
                onConfirmation = {
                    DataStorage(mContext).setSynchronousData(DataStorage.KEY_DEFAULT_LANGUAGE, "")
                    val language = DataStorage(mContext).getSynchronousData(DataStorage.KEY_DEFAULT_LANGUAGE)
                    language.isNullOrEmpty()
                    closeAction()
                },
                dialogTitle = "Confirm Log Out",
                icon = Icons.Outlined.Logout
            )
        }
    }
}