package com.zicure.xcotv.utils

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Sensors
import androidx.compose.ui.graphics.vector.ImageVector
import com.zicure.xcotv.R

sealed class DrawerScreen(
    val route: String,
    val icon: ImageVector,
    @StringRes val  title: Int,
    @StringRes val descriptionId: Int,
) {

    data object UserAccountDrawer: DrawerScreen(
        "UserAccount",
        Icons.Outlined.AccountCircle,
        R.string.user_account,
        R.string.user_account,
    )
    data object SearchScreenDrawer: DrawerScreen(
        "SearchScreen",
        Icons.Outlined.Search,
        R.string.search,
        R.string.search,
    )
    data object FreeTVScreenDrawer : DrawerScreen(
        "FreeTVScreen",
        Icons.Outlined.Sensors,
        R.string.free_tv,
        R.string.free_tv
    )

    data object LogOutDrawer : DrawerScreen(
        "LogOut",
        Icons.Outlined.ExitToApp,
        R.string.log_out,
        R.string.log_out
    )
}

val screenList = listOf(
    DrawerScreen.SearchScreenDrawer,
    DrawerScreen.FreeTVScreenDrawer
)