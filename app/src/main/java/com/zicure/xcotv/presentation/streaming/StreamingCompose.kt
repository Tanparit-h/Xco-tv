package com.zicure.xcotv.presentation.streaming

import android.net.Uri
import android.util.Log
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.annotation.OptIn
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.zicure.xcotv.utils.grayFF1B1B1B
import androidx.constraintlayout.compose.ConstraintLayout

@OptIn(UnstableApi::class)
@Composable
private fun VideoPlayer(modifier: Modifier = Modifier, exoPlayer: ExoPlayer) {
    val context = LocalContext.current

    DisposableEffect(
        AndroidView(modifier = modifier, factory = {
            PlayerView(context).apply {
                hideController()
                useController = false
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM

                player = exoPlayer
                layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            }
        })
    ) {
        onDispose { exoPlayer.release() }
    }
}

@OptIn(UnstableApi::class)
@Composable
fun GetExoPlayer(
    stringUri: String?
): ExoPlayer {
    val context = LocalContext.current
    val uri = Uri.parse(stringUri)

    val exoPlayer = remember {
        ExoPlayer.Builder(context)
            .build()
            .apply {
                if (!uri.toString().endsWith(".m3u8")) {
                    val defaultDataSourceFactory = DefaultDataSource.Factory(context)
                    val dataSourceFactory: DataSource.Factory = DefaultDataSource.Factory(
                        context,
                        defaultDataSourceFactory
                    )
                    val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(MediaItem.fromUri(uri))
                    setMediaSource(source)
                } else {
                    val dataSourceFactory: DataSource.Factory = DefaultHttpDataSource.Factory()
                    val hlsMediaSource =
                        HlsMediaSource.Factory(dataSourceFactory)
                            .createMediaSource(MediaItem.fromUri(uri))
                    setMediaSource(hlsMediaSource)
                }
                prepare()
            }
    }

    exoPlayer.playWhenReady = true
    exoPlayer.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
    exoPlayer.repeatMode = Player.REPEAT_MODE_ONE
    return exoPlayer
}

@Composable
fun MainStreaming(exoPlayer: ExoPlayer) {
    val position = remember {
        mutableLongStateOf(exoPlayer.currentPosition)
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (mainSurface, videoView) = createRefs()
        Surface(
            color = grayFF1B1B1B,
            modifier = Modifier
                .fillMaxSize(1f)
                .constrainAs(mainSurface) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
        ) {
            VideoPlayer(
                modifier = Modifier
                    .constrainAs(videoView) {
                        start.linkTo(mainSurface.start)
                        top.linkTo(mainSurface.top)
                        bottom.linkTo(mainSurface.bottom)
                        end.linkTo(mainSurface.end)
                    }, exoPlayer
            )
            Log.d("TAG", "MainStreaming position: $position")

        }
    }
}

@Preview()
@Composable
private fun PreviewMainStreaming() {
//    MainStreaming("https://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4")
}