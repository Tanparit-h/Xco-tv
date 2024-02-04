package com.zicure.xcotv.utils

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.zicure.xcotv.utils.DataStorage.Companion.KEY_DEFAULT_LANGUAGE
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
fun setLocaleLang(lang: String, context: Context) {
    val appLocale = LocaleListCompat.forLanguageTags(lang)
    AppCompatDelegate.setApplicationLocales(appLocale)

    val resource = context.resources
    val config = resource.configuration
    val locale = Locale(lang)
    Locale.setDefault(locale)
    config.setLocale(locale)
    resource.updateConfiguration(config, resource.displayMetrics)

    DataStorage(context).setSynchronousData(KEY_DEFAULT_LANGUAGE, lang)
}

fun isSetLanguage(context: Context): Boolean {
    val language = DataStorage(context).getSynchronousData(KEY_DEFAULT_LANGUAGE)
    return !language.isNullOrEmpty()
}

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
fun loadLocale(context: Context) {
    val language = DataStorage(context).getSynchronousData(KEY_DEFAULT_LANGUAGE)
    setLocaleLang(language ?: "th", context)
}