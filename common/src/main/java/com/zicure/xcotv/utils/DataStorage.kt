package com.zicure.xcotv.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "zicure")

class DataStorage @Inject constructor(
    @ApplicationContext val context: Context
) {

    suspend fun <T> setData(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { zicure ->
            zicure[key] = value
        }
    }

    fun <T> getData(key: Preferences.Key<T>): Flow<T?> =
        context.dataStore.data.map { preference ->
            preference[key]
        }

    inline fun <reified T> getSynchronousData(key: Preferences.Key<T>): T? {
        val pref = context.getSharedPreferences("zicure-sync", Context.MODE_PRIVATE)
        return when (T::class) {
            String::class -> {
                pref.getString(key.name, "") as T
            }
            Int::class -> {
                pref.getInt(key.name, -1) as T
            }
            Boolean::class -> {
                pref.getBoolean(key.name, false) as T
            }
            Long::class -> {
                pref.getLong(key.name, -1L) as T
            }
            Float::class -> {
                pref.getFloat(key.name, -1F) as T
            }
            else -> {
                null
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("CommitPrefEdits")
    inline fun <reified T> setSynchronousData(key: Preferences.Key<T>, value: T) {
        val pref = context.getSharedPreferences("zicure-sync", Context.MODE_PRIVATE).edit()
        Timber.d("Set `${key.name}` ---> $value")
        when (T::class) {
            String::class -> {
                pref.putString(key.name, value as String)
            }
            Int::class -> {
                pref.putInt(key.name, value as Int)
            }
            Boolean::class -> {
                pref.putBoolean(key.name, value as Boolean)
            }
            Long::class -> {
                pref.putLong(key.name, value as Long)
            }
            Float::class -> {
                pref.putFloat(key.name, value as Float)
            }
        }
        pref.apply()
    }

    fun getDataStore(): DataStore<Preferences> {
        return context.dataStore
    }

    companion object {
        val KEY_DEFAULT_LANGUAGE = stringPreferencesKey("KEY_DEFAULT_LANGUAGE")
        val KEY_LANGUAGE = stringPreferencesKey("KEY_LANGUAGE")
        val KEY_USER_ID = stringPreferencesKey("KEY_USER_ID")
    }
}