package com.zicure.xcotv.presentation.streaming

import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.zicure.xcotv.domain.usecase.GetUserListUseCase
import com.zicure.xcotv.utils.BaseTheme
import com.zicure.xcotv.utils.DataStorage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@AndroidEntryPoint
class StreamingActivity : AppCompatActivity() {

    @Inject
    lateinit var getUserListUseCase: GetUserListUseCase

    @Inject
    lateinit var dataStorage: DataStorage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BaseTheme {
//                VideoPlayer(uri)
            }
        }
    }
}