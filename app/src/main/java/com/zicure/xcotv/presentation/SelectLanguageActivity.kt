package com.zicure.xcotv.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zicure.xcotv.R
import com.zicure.xcotv.presentation.compose.screen.SelectLanguageScreen
import com.zicure.xcotv.presentation.profile.SelectProfileActivity
import com.zicure.xcotv.utils.BackPressHandler
import com.zicure.xcotv.utils.BaseTheme
import com.zicure.xcotv.utils.fontFCIconic
import com.zicure.xcotv.utils.grayFF1B1B1B
import com.zicure.xcotv.utils.loadLocale
import com.zicure.xcotv.utils.pxToDp
import com.zicure.xcotv.utils.setLocaleLang
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectLanguageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseTheme {
                BackPressHandler { finishAndRemoveTask() }
                val mContext = LocalContext.current
                SelectLanguageScreen {
                    val action = Intent(mContext, SelectProfileActivity::class.java)
                    mContext.startActivity(action)
                    finish()
                }
            }
        }
    }
}