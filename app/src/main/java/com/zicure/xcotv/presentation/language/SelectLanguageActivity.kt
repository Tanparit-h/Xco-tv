package com.zicure.xcotv.presentation.language

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.LocalContext
import com.zicure.xcotv.presentation.compose.screen.SelectLanguageScreen
import com.zicure.xcotv.presentation.profile.SelectProfileActivity
import com.zicure.xcotv.utils.BackPressHandler
import com.zicure.xcotv.utils.BaseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectLanguageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseTheme(isLoadLanguage = false) {
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