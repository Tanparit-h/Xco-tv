package com.zicure.xcotv.presentation.compose.layout

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.PlayCircleFilled
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.zicure.xcotv.R
import com.zicure.xcotv.domain.model.FreeTv
import com.zicure.xcotv.presentation.streaming.StreamingActivity
import com.zicure.xcotv.utils.MultiColorText
import com.zicure.xcotv.utils.XcotvIconTextButton
import com.zicure.xcotv.utils.XcotvTextButton
import com.zicure.xcotv.utils.fontFCIconic
import com.zicure.xcotv.utils.grayFF616161
import com.zicure.xcotv.utils.grayFFAAAAAA

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
fun SuggestProgramLayout(
    media: FreeTv,
    navMedia: (intent: Intent) -> Unit
) {
    val context = LocalContext.current
    var focusState by remember { mutableStateOf<FocusState?>(null) }
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(key1 = focusState) {
        if (focusState?.hasFocus == false) {
            focusRequester.requestFocus()
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {
        GlideImage(
            modifier = Modifier
                .matchParentSize()
                .drawWithCache {
                    val gradient = Brush.horizontalGradient(
                        colors = listOf(Color.Black, Color.Transparent),
                        startX = size.width / 4,
                        endX = size.width
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(
                            gradient,
                            blendMode = BlendMode.Darken
                        )
                    }
                },
            model = media.mediaList[0].background,
            contentDescription = "",
            alignment = Alignment.CenterEnd,
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(600.dp)
                .zIndex(3f)
                .padding(top = 100.dp, start = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)

            ) {
                GlideImage(
                    modifier = Modifier
                        .width(52.dp)
                        .height(52.dp)
                        .clip(shape = RoundedCornerShape(8.dp)),
                    model = media.mediaList[0].icon,
                    contentDescription = ""
                )

                MultiColorText(
                    modifier = Modifier.padding(start = 15.dp),
                    Triple(
                        media.mediaList[0].name,
                        Color.White,
                        FontWeight.Bold
                    ),
                    Triple(
                        "\n" + media.mediaList[0].subName,
                        grayFFAAAAAA,
                        FontWeight.Normal
                    )
                )

                media.mediaList[0].tagList.forEach { tag ->
                    XcotvTextButton(
                        modifier = Modifier
                            .padding(start = 20.dp),
                        text = tag.name,
                        colorButton = grayFF616161,
                        colorText = Color.White,
                        fontWeight = FontWeight.Medium
                    ) {
                        //TODO: Padding for search filter by tag
                        Log.d("TAG", "search filter by tag ")
                    }
                }
            }

            Text(
                modifier = Modifier
                    .width(500.dp)
                    .padding(top = 40.dp),
                text = media.mediaList[0].description,
                color = Color.White,
                fontFamily = fontFCIconic,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .padding(top = 60.dp),
            ) {
                XcotvIconTextButton(
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .onFocusChanged {
                            focusState = it
                        },
                    icon = Icons.Outlined.PlayCircleFilled,
                    text = ContextCompat.getString(LocalContext.current, R.string.play_now),
                    colorIcon = Color.Red,
                    colorButton = Color.Transparent,
                    colorText = Color.Red,
                    fontWeight = FontWeight.Normal,
                    sizeIcon = 36
                ) {
                    navMedia(StreamingActivity().getIntent(context = context, media.mediaList[0]))
                }

                XcotvIconTextButton(
                    modifier = Modifier,
                    icon = Icons.Outlined.Info,
                    text = ContextCompat.getString(LocalContext.current, R.string.info),
                    colorIcon = Color.Red,
                    colorButton = Color.Transparent,
                    colorText = Color.Red,
                    fontWeight = FontWeight.Normal,
                    sizeIcon = 28
                ) {}

                val listIcon =
                    if (media.mediaList[0].isList) Icons.Filled.CheckCircle else Icons.Filled.AddCircle
                XcotvIconTextButton(
                    modifier = Modifier,
                    icon = listIcon,
                    text = ContextCompat.getString(LocalContext.current, R.string.my_list),
                    colorIcon = Color.White,
                    colorButton = Color.Transparent,
                    colorText = Color.White,
                    fontWeight = FontWeight.Normal,
                    sizeIcon = 28
                ) {}
            }
        }
    }
}