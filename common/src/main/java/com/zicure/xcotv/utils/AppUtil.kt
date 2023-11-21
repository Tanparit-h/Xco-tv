package com.zicure.xcotv.utils

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppUtil @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun getPackage(): PackageInfo {
        val manager = context.packageManager
        return manager.getPackageInfo(context.packageName, PackageManager.GET_ACTIVITIES)
    }
}