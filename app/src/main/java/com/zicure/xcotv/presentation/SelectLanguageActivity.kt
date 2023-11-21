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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.zicure.xcotv.domain.usecase.SetDataStoreUseCase
import com.zicure.xcotv.presentation.profile.SelectProfileActivity
import com.zicure.xcotv.utils.BackPressHandler
import com.zicure.xcotv.utils.BaseTheme
import com.zicure.xcotv.utils.fontFCIconic
import com.zicure.xcotv.utils.gray1B1B1B
import com.zicure.xcotv.utils.loadLocale
import com.zicure.xcotv.utils.pxToDp
import com.zicure.xcotv.utils.setLocaleLang
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SelectLanguageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseTheme {
                SelectLanguageScreen()
            }
        }
    }

    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    private fun SelectLanguageScreen() {
        val mContext = LocalContext.current
        BackPressHandler { finishAndRemoveTask() }
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
                    modifier = Modifier.padding(bottom = 30.dp),
                    text = getString(R.string.tx_select_language),
                    color = Color.White,
                    fontFamily = fontFCIconic,
                    fontWeight = FontWeight.Normal,
                    fontSize = 72.sp
                )
            }
            item {
                LanguageButton(
                    R.drawable.ic_flag_english,
                    getString(R.string.tx_select_language_en),
                    "en"
                ) {
                    val action = Intent(mContext, SelectProfileActivity::class.java)
                    mContext.startActivity(action)
                    finish()
                }
            }
            item {
                LanguageButton(
                    R.drawable.ic_flag_thai,
                    getString(R.string.tx_select_language_th),
                    "th"
                ) {
                    val action = Intent(mContext, SelectProfileActivity::class.java)
                    mContext.startActivity(action)
                    finish()
                }
            }
        }
    }

    @SuppressLint("ResourceType")
    @Composable
    private fun LanguageButton(
        @DrawableRes flagId: Int,
        string: String,
        languageCode: String,
        action: () -> Unit
    ) {
        val mContext = LocalContext.current

        TextButton(
            modifier = Modifier
                .width(270.dp),
            onClick = {
                setLocaleLang(languageCode, mContext)
                action()
            }
        ) {
            val vectorResIcon = ImageBitmap.imageResource(
                mContext.resources,
                flagId
            )
            Image(
                bitmap = vectorResIcon,
                modifier = Modifier
                    .size(width = 70.pxToDp(), height = 44.pxToDp()),
                contentDescription = null
            )
            Spacer(modifier = Modifier.weight(0.5f))
            Text(
                modifier = Modifier
                    .padding(start = 12.dp),
                text = string,
                color = Color.White,
                fontFamily = fontFCIconic,
                fontWeight = FontWeight.Normal,
                fontSize = 48.sp
            )
            Spacer(modifier = Modifier.weight(0.5f))
        }
    }

    @Preview
    @Composable
    private fun PreviewSelectLanguageScreen() {
        SelectLanguageScreen()
    }
}