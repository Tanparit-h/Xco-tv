package com.zicure.xcotv.presentation.profile

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zicure.xcotv.R
import com.zicure.xcotv.utils.BaseTheme
import com.zicure.xcotv.utils.fontFCIconic
import com.zicure.xcotv.utils.gray1B1B1B
import com.zicure.xcotv.utils.loadLocale
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class SelectProfileActivity : AppCompatActivity() {

    private val viewModel by viewModels<SelectProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseTheme {
                SelectProfileScreen()
            }
        }
    }


    @Composable
    private fun SelectProfileScreen() {
        viewModel.getUser()
        loadLocale(LocalContext.current)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(gray1B1B1B),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    modifier = Modifier
                        .padding(bottom = 30.dp),
                    text = getString(R.string.tx_select_user),
                    color = Color.White,
                    fontFamily = fontFCIconic,
                    fontWeight = FontWeight.Bold,
                    fontSize = 48.sp
                )
            }
            item {

            }
        }
    }



    @Preview(device = Devices.AUTOMOTIVE_1024p)
    @Composable
    private fun PreviewSplashScreen() {
        SelectProfileScreen()
    }
}