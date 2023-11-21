package com.zicure.xcotv.domain.usecase

import androidx.datastore.preferences.core.Preferences
import com.zicure.xcotv.utils.DataStorage
import javax.inject.Inject

class GetDataStoreUseCase @Inject constructor(
    private val dataStore: DataStorage
) {
    operator fun <T> invoke(key: Preferences.Key<T>) = dataStore.getData(key)
}