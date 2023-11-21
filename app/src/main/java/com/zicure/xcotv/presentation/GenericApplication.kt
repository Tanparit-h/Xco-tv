package com.zicure.xcotv.presentation

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.zicure.xcotv.domain.UatTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class GenericApplication: Application() {
    @Inject
    lateinit var tree: Timber.Tree

    override fun onCreate() {
        super.onCreate()
        initLogger()
    }

    private fun initLogger() {
        tree = UatTree()
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)
            .methodOffset(3)
            .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
        Timber.plant(tree)
    }
}