package com.zicure.xcotv.presentation.compose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.zicure.xcotv.presentation.main.MainViewModel
import com.zicure.xcotv.utils.DrawerScreen

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MainNavigationDrawer(viewModel: MainViewModel) {

    MainDrawer {
        composable(route = DrawerScreen.SearchScreenDrawer.route) {
            FreeTVScreen(viewModel)
        }
        composable(route = DrawerScreen.FreeTVScreenDrawer.route) {
            FreeTVScreen(viewModel)
        }
    }
}