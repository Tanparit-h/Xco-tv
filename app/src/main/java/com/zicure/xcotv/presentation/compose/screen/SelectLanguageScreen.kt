package com.zicure.xcotv.presentation.compose.screen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.annotation.DrawableRes
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zicure.xcotv.R
import com.zicure.xcotv.presentation.profile.SelectProfileActivity
import com.zicure.xcotv.utils.BackPressHandler
import com.zicure.xcotv.utils.fontFCIconic
import com.zicure.xcotv.utils.grayFF1B1B1B
import com.zicure.xcotv.utils.loadLocale
import com.zicure.xcotv.utils.pxToDp
import com.zicure.xcotv.utils.setLocaleLang

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SelectLanguageScreen(
    action: () -> Unit
) {
    val mContext = LocalContext.current
    var focusState by remember { mutableStateOf<FocusState?>(null) }
    val focusRequester = remember { FocusRequester() }
    loadLocale(LocalContext.current)
    LaunchedEffect(key1 = mContext) {
        if (focusState?.hasFocus == false) {
            focusRequester.requestFocus()
        }
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(grayFF1B1B1B)
            .focusRequester(focusRequester)
            .onFocusChanged {
                focusState = it
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                modifier = Modifier.padding(bottom = 30.dp),
                text = mContext.getString(R.string.tx_select_language),
                color = Color.White,
                fontFamily = fontFCIconic,
                fontWeight = FontWeight.Normal,
                fontSize = 72.sp
            )
        }
        item {
            LanguageButton(
                R.drawable.ic_flag_english,
                mContext.getString(R.string.tx_select_language_en),
                "en"
            ) {
               action()
            }
        }
        item {
            LanguageButton(
                R.drawable.ic_flag_thai,
                mContext.getString(R.string.tx_select_language_th),
                "th"
            ) {
                action()
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