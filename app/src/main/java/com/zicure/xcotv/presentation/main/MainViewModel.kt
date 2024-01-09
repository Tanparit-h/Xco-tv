package com.zicure.xcotv.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zicure.xcotv.domain.model.FreeTVListModel
import com.zicure.xcotv.domain.usecase.GetFreeTVMediaListUseCase
import com.zicure.xcotv.utils.Event
import com.zicure.xcotv.utils.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getFreeTVMediaListUseCase: GetFreeTVMediaListUseCase
): ViewModel() {

    private val _freeTVMediaList = MutableLiveData<FreeTVListModel?>()
    val freeTVMediaList: LiveData<FreeTVListModel?> = _freeTVMediaList

    fun getFreeTVMediaList() {
        viewModelScope.launch(Dispatchers.IO) {
            val mediaList = getFreeTVMediaListUseCase.invoke()
            _freeTVMediaList.postValue(mediaList)
        }
    }
}