package com.zicure.xcotv.presentation.compose.screen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.zicure.xcotv.domain.model.FreeTVListModel
import com.zicure.xcotv.domain.model.MediaListType
import com.zicure.xcotv.presentation.compose.layout.ListProgramLayout
import com.zicure.xcotv.presentation.compose.layout.SuggestProgramLayout
import com.zicure.xcotv.presentation.main.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterialApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun FreeTVScreen(viewModel: MainViewModel, navMedia: (intent: Intent) -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val focusRequester = remember { FocusRequester() }
    val freeTVMediaList = remember {
        mutableStateOf(FreeTVListModel(null))
    }
    getFreeTVListModel(viewModel, lifecycleOwner, freeTVMediaList)

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (surface) = createRefs()
        if (freeTVMediaList.value.freeTV?.size == 0) {
            Column(modifier = Modifier
                .focusRequester(focusRequester)
                .fillMaxSize()
                .background(color = Color.Black)
                .clickable { focusRequester.requestFocus() }
                .focusable()
                .constrainAs(surface) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                Text(text = "FreeTVScreen: ${freeTVMediaList.value.freeTV?.size}")
            }
        } else {
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
                .constrainAs(surface) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                freeTVMediaList.value.freeTV?.forEachIndexed { index, media ->
                    when (media.type) {
                        MediaListType.SUGGEST_PROGRAM -> {
                            item {
                                SuggestProgramLayout(media, navMedia)
                            }
                        }
                        MediaListType.LIST_PROGRAM -> {
                            item {
                                ListProgramLayout(media, navMedia)
                            }
                        }
                        MediaListType.LIST_BANNER -> {
                            item {
//                                ListProgramLayout(media)
                            }
                        }
                        else -> {
                            item {
                                ListProgramLayout(media, navMedia)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun getFreeTVListModel(
    viewModel: MainViewModel,
    lifecycleOwner: LifecycleOwner,
    freeTVMediaList: MutableState<FreeTVListModel>
) {
    LaunchedEffect(Unit) {
        withContext(Dispatchers.Main) {
            viewModel.getFreeTVMediaList()
        }
    }
    viewModel.freeTVMediaList.observe(lifecycleOwner) {
        if (it != null) {
            freeTVMediaList.value = it
        }
    }
}