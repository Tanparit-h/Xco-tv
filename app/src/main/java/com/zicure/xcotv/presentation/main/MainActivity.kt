package com.zicure.xcotv.presentation.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.tv.material3.DrawerState
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.rememberDrawerState
import com.zicure.xcotv.presentation.compose.MainNavigationDrawer
import com.zicure.xcotv.utils.BaseTheme
import com.zicure.xcotv.utils.DataStorage
import com.zicure.xcotv.utils.DrawerScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    @OptIn(ExperimentalTvMaterial3Api::class)
    private lateinit var drawerState: DrawerState
    private lateinit var navController: NavHostController

    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseTheme {
                val scope = rememberCoroutineScope()
                navController = rememberNavController()
                drawerState = rememberDrawerState(DrawerValue.Closed)

                BackHandler {
                    if (drawerState.currentValue == DrawerValue.Open) {
                        scope.launch {
                            finishAffinity()
                        }
                    } else {
                        scope.launch {
                            drawerState.setValue(DrawerValue.Open)
                        }
                    }
                }
                MainNavigationDrawer(
                    viewModel,
                    navController,
                    drawerState,
                    navMedia = {
                        mediaActivityResult.launch(it)
                    },
                    closeAction = {
                        finishAffinity()
                    }
                )
            }
        }
    }

    private val mediaActivityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val route = data?.getStringExtra("MEDIA_RESULT")
            if (route != null) {
                navController.navigate(route)
            } else {
                navController.navigate(DrawerScreen.FreeTVScreenDrawer.route)
            }
        }
    }

}
