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
import androidx.compose.ui.platform.LocalContext
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.zicure.xcotv.domain.model.Media
import com.zicure.xcotv.domain.usecase.GetUserListUseCase
import com.zicure.xcotv.presentation.compose.VideoDrawer
import com.zicure.xcotv.utils.BaseTheme
import com.zicure.xcotv.utils.DataStorage
import com.zicure.xcotv.utils.DataStorage.Companion.toPreferencesKey
import com.zicure.xcotv.utils.DrawerScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class StreamingActivity : AppCompatActivity() {
    private val MEDIA = "MEDIA"
    private val MEDIA_RESULT = "MEDIA_RESULT"

    @Inject
    lateinit var getUserListUseCase: GetUserListUseCase

    @Inject
    lateinit var dataStorage: DataStorage

    private lateinit var exoPlayer: ExoPlayer

    private lateinit var drawerState: DrawerState
    private lateinit var navController: NavController
    private lateinit var media: Media

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        media = intent.getParcelableExtra<Media>(MEDIA) ?: Media()

        setContent {
            BaseTheme {
                val scope = rememberCoroutineScope()
                drawerState = rememberDrawerState(DrawerValue.Closed)
                exoPlayer = GetExoPlayer(stringUri = media.mediaUrl)
                navController = rememberNavController()

                val resumePosition = dataStorage.getSynchronousData((media.name + media.subName).toPreferencesKey()) ?: 0
                if (resumePosition > 0) {
                    exoPlayer.seekTo(resumePosition)
                }


                BackHandler {
                    if (drawerState.currentValue == DrawerValue.Open) {
                        scope.launch {
                            dataStorage.setSynchronousData((media.name + media.subName).toPreferencesKey(), exoPlayer.currentPosition)
                            val returnIntent = intent
                            returnIntent.putExtra(MEDIA_RESULT, DrawerScreen.FreeTVScreenDrawer.route)
                            setResult(RESULT_OK, returnIntent)
                            finish()
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
                    MainStreaming(exoPlayer)
                }
            }
        }
    }

    override fun onStop() {
        dataStorage.setSynchronousData((media.name + media.subName).toPreferencesKey(), exoPlayer.currentPosition)
        exoPlayer.release()
        super.onStop()
    }

    fun getIntent(context: Context, media: Media): Intent{
        val intent = Intent(context, StreamingActivity::class.java);
        intent.putExtra(MEDIA, media)
        return intent
    }
}