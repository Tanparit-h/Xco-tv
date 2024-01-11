package com.zicure.xcotv.presentation.compose.layout

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.zicure.xcotv.domain.model.FreeTv
import com.zicure.xcotv.presentation.streaming.StreamingActivity
import com.zicure.xcotv.utils.fontFCIconic
import com.zicure.xcotv.utils.grayFF616161


@Composable
@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterialApi::class)
fun ListProgramLayout(media: FreeTv, navMedia: (intent: Intent) -> Unit) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(unbounded = true)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 10.dp),
                text = media.nameList,
                color = Color.White,
                fontFamily = fontFCIconic,
                fontWeight = FontWeight.SemiBold,
                fontSize = 32.sp,
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp, bottom = 10.dp)
                    .wrapContentHeight(unbounded = true),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 24.dp)
            ) {
                media.mediaList.forEach { banner ->
                    item {
                        var borderColor by remember { mutableStateOf(Color.White) }
                        fun applyColors(focusState: FocusState) {
                            borderColor = if (focusState.isFocused) {
                                grayFF616161
                            } else {
                                Color.Transparent
                            }
                        }
                        Card(
                            modifier = Modifier
                                .width(200.dp)
                                .height(200.dp)
                                .onFocusEvent(::applyColors)
                                .focusable(),
                            onClick = {
                                Log.d("TAG", "FreeTVScreen Banner: ${banner.name}")
                                navMedia(StreamingActivity().getIntent(context = context, banner.mediaUrl))
                            },
                            backgroundColor = Color.White,
                            border = BorderStroke(4.dp, borderColor),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            GlideImage(
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(200.dp),
                                model = banner.banner,
                                contentDescription = "",
                                alignment = Alignment.Center,
                                contentScale = ContentScale.Inside
                            )
                        }
                    }
                }
            }
        }
    }
}
