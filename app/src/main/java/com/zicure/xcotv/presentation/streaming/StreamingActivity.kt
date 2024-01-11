package com.zicure.xcotv.presentation.streaming

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.zicure.xcotv.domain.usecase.GetUserListUseCase
import com.zicure.xcotv.presentation.compose.VideoDrawer
import com.zicure.xcotv.utils.BaseTheme
import com.zicure.xcotv.utils.DataStorage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class StreamingActivity : AppCompatActivity() {
    private val MEDIA_URL = "MEDIA_URL"
    private val MEDIA_RESUME = "MEDIA_RESUME"
    private val MEDIA_RESULT = "MEDIA_RESULT"

    @Inject
    lateinit var getUserListUseCase: GetUserListUseCase

    @Inject
    lateinit var dataStorage: DataStorage
    private lateinit var exoPlayer: ExoPlayer

    private lateinit var drawerState: DrawerState
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mediaUrl = intent.getStringExtra(MEDIA_URL)
        val isResume = intent.getBooleanExtra(MEDIA_RESUME, false)

        setContent {
            BaseTheme {
                val scope = rememberCoroutineScope()
                drawerState = rememberDrawerState(DrawerValue.Closed)
                exoPlayer = GetExoPlayer(stringUri = mediaUrl)
                navController = rememberNavController()

                BackHandler {
                    if (drawerState.currentValue == DrawerValue.Open) {
                        scope.launch {
                            drawerState.close()
                        }
                    } else {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                }
                LaunchedEffect(Unit) {
                    launch {
                        snapshotFlow { drawerState.currentValue }
                            .collect {
                                if (it == DrawerValue.Open) {
                                    exoPlayer.playWhenReady = false
                                    exoPlayer.pause()
                                } else {
                                    exoPlayer.playWhenReady = true
                                }
                            }
                    }
                }
                VideoDrawer(
                    modifier = Modifier,
                    drawerState = drawerState,
                    navController = navController,
                    onClickAction = { route ->
                        val returnIntent = intent
                        returnIntent.putExtra(MEDIA_RESULT, route)
                        setResult(RESULT_OK, returnIntent)
                        finish()
                    }
                ) {
                    MainStreaming(exoPlayer, isResume)
                }
            }
        }
    }

    override fun onStop() {
        exoPlayer.release()
        super.onStop()
    }

    fun getIntent(context: Context, mediaUrl: String, isResume: Boolean = false): Intent{
        val intent = Intent(context, StreamingActivity::class.java);
        intent.putExtra(MEDIA_URL, mediaUrl)
        intent.putExtra(MEDIA_RESUME, isResume)
        return intent
    }
}