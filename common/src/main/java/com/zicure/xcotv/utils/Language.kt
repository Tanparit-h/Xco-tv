package com.zicure.xcotv.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
fun setLocaleLang(lang: String, context: Context) {
    val appLocale = LocaleListCompat.forLanguageTags(lang) //here ta is hardcoded for testing purpose,you can add users selected language code.
    AppCompatDelegate.setApplicationLocales(appLocale)

    val editor = context.getSharedPreferences(KEY_DEFAULT_LANGUAGE, Context.MODE_PRIVATE).edit()
    editor.putString(KEY_LANGUAGE, lang)
    editor.apply()
}

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
fun loadLocale(context: Context) {
    val sharedPreferences = context.getSharedPreferences(KEY_DEFAULT_LANGUAGE, Activity.MODE_PRIVATE)
    val language = sharedPreferences.getString(KEY_LANGUAGE, "th")
    setLocaleLang(language!!, context)
}