package com.zicure.xcotv.presentation.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.zicure.xcotv.presentation.compose.MainNavigationDrawer
import com.zicure.xcotv.utils.BaseTheme
import com.zicure.xcotv.utils.DrawerScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseTheme {
                navController = rememberNavController()
                MainNavigationDrawer(
                    viewModel,
                    navController,
                    navMedia = {
                        mediaActivityResult.launch(it)
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
