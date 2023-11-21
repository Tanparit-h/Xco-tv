package com.zicure.xcotv.domain.usecase

import androidx.datastore.preferences.core.Preferences
import com.zicure.xcotv.utils.DataStorage
import javax.inject.Inject

class SetDataStoreUseCase @Inject constructor(
    private val dataStorage: DataStorage
) {
    suspend operator fun <T> invoke(key: Preferences.Key<T>, value: T) = dataStorage.setData(key, value)
}