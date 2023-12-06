package com.zicure.xcotv.presentation.profile

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.zicure.xcotv.R
import com.zicure.xcotv.domain.usecase.GetUserListUseCase
import com.zicure.xcotv.presentation.streaming.StreamingActivity
import com.zicure.xcotv.utils.BaseTheme
import com.zicure.xcotv.utils.DataStorage
import com.zicure.xcotv.utils.DataStorage.Companion.KEY_USER_ID
import com.zicure.xcotv.utils.Profile
import com.zicure.xcotv.utils.fontFCIconic
import com.zicure.xcotv.utils.gray1B1B1B
import com.zicure.xcotv.utils.loadLocale
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class SelectProfileActivity : AppCompatActivity() {

    private val viewModel by viewModels<SelectProfileViewModel>()

    @Inject
    lateinit var getUserListUseCase: GetUserListUseCase

    @Inject
    lateinit var dataStorage: DataStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseTheme {
                SelectProfileScreen()
            }
        }
    }


    @Composable
    private fun SelectProfileScreen() {
        val userProfile = remember {
            getUserListUseCase.invoke()
        }
        loadLocale(LocalContext.current)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(gray1B1B1B),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(bottom = 30.dp),
                text = getString(R.string.tx_select_user),
                color = Color.White,
                fontFamily = fontFCIconic,
                fontWeight = FontWeight.Bold,
                fontSize = 48.sp
            )
            Row(
                modifier = Modifier
                    .background(gray1B1B1B)
                    .wrapContentSize(unbounded = true),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                userProfile.forEach { p ->
                    ProfileButton(profile = p)
                }
            }
            Button(
                modifier = Modifier
                    .wrapContentSize(unbounded = true)
                    .padding(all = 16.dp),
                shape = RoundedCornerShape(16),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.White
                ),
                onClick = {
                    //TODO: Padding for manage profile page
                    Timber.d("Padding for manage profile page")
                }
            ) {

                Spacer(modifier = Modifier.weight(0.5f))
                Text(
                    modifier = Modifier
                        .padding(start = 12.dp),
                    text = getString(R.string.tx_manage_profile),
                    color = gray1B1B1B,
                    fontFamily = fontFCIconic,
                    fontWeight = FontWeight.Normal,
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.weight(0.5f))
            }
        }
    }

    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    private fun ProfileButton(
        profile: Profile
    ) {
        val context = LocalContext.current
        OutlinedButton(
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Transparent),
            border = BorderStroke(0.dp, Color.Transparent),
            modifier = Modifier
                .padding(all = 4.dp)
                .background(gray1B1B1B),
            onClick = {
                dataStorage.setSynchronousData(KEY_USER_ID, profile.id)
                val action = Intent(context, StreamingActivity::class.java)
                startActivity(action)
            }
        ) {
            Column(
                modifier = Modifier.wrapContentSize(Alignment.Center),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GlideImage(
                    modifier = Modifier
                        .size(128.dp)
                        .clip(CircleShape),
                    model = profile.image,
                    contentDescription = ""
                )
                Text(
                    modifier = Modifier
                        .padding(top = 12.dp),
                    text = profile.name,
                    color = Color.White,
                    fontFamily = fontFCIconic,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }
        }
    }


    @Preview(device = Devices.AUTOMOTIVE_1024p)
    @Composable
    private fun PreviewSplashScreen() {
        SelectProfileScreen()
    }
}