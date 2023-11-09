package com.zicure.xcotv.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.zicure.xcotv.R
import com.zicure.xcotv.utils.redC30000

@SuppressLint("CustomSplashScreen")
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent { }
    }

    @Composable
    private fun SplashScreen() {
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(redC30000),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            val vectorResIcon = ImageBitmap.imageResource(
//                LocalContext.current.resources, R.drawable.ic_xcotv
//            )
//            Image(vectorResIcon, modifier = Modifier.size(60.dp), contentDescription = null)
//
//        }
    }
}
