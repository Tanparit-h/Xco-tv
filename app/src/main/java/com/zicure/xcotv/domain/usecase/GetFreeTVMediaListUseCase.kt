package com.zicure.xcotv.domain.usecase

import com.google.gson.Gson
import com.zicure.xcotv.BuildConfig
import com.zicure.xcotv.domain.model.FreeTVListModel
import java.net.URL
import javax.inject.Inject

class GetFreeTVMediaListUseCase @Inject constructor() {
    fun invoke(): FreeTVListModel? {
        return try {
            val jsonString = URL(BuildConfig.URL_FREE_TV_MEDIA_LIST).readText()
            Gson().fromJson(
                jsonString,
                FreeTVListModel::class.java
            )
        } catch (e: Exception) {
            println("load json error" + e.message)
            null
        }
    }
}