package com.zicure.xcotv.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.zicure.xcotv.R
import com.zicure.xcotv.utils.BaseTheme
import com.zicure.xcotv.utils.CustomProgressIndicator
import com.zicure.xcotv.utils.pxToDp
import com.zicure.xcotv.utils.redC30000
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
class SplashScreenActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseTheme {
                SplashScreen()
            }
        }
    }

    @Composable
    private fun SplashScreen() {
        val process: MutableFloatState = remember { mutableFloatStateOf(0f) }
        val mContext = LocalContext.current
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(redC30000),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                val vectorResIcon = ImageBitmap.imageResource(
                    LocalContext.current.resources,
                    R.drawable.ic_xcotv
                )
                Image(
                    bitmap = vectorResIcon,
                    modifier = Modifier.size(width = 222.pxToDp(), height = 150.pxToDp()),
                    contentDescription = null
                )
            }

            item {
                CustomProgressIndicator(
                    modifier = Modifier
                        .width(300.pxToDp())
                        .padding(top = 80.pxToDp()),
                    progress = process.floatValue
                ) {
                    val action = Intent(mContext, LanguageSelectActivity::class.java)
                    mContext.startActivity(action)
                    finish()
                }
                IncreaseProgressValue(process, 1f)
            }
        }
    }

    @Composable
    private fun IncreaseProgressValue(current: MutableFloatState, target: Float) {
        if (current.floatValue > target) return
        LaunchedEffect(target) {
            while (current.floatValue < target) {
                delay(20)
                current.floatValue += 0.05f
            }
        }
    }

    @Preview(device = Devices.AUTOMOTIVE_1024p)
    @Composable
    private fun PreviewSplashScreen() {
        SplashScreen()
    }
}
