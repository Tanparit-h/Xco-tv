package com.zicure.xcotv.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zicure.xcotv.domain.usecase.GetUserListUseCase
import com.zicure.xcotv.utils.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectProfileViewModel @Inject constructor(
    val getUserListUseCase: GetUserListUseCase
): ViewModel() {

    private val _userList = MutableLiveData<List<Profile>>()
    val userList: LiveData<List<Profile>> = _userList

    fun getUser() {
        viewModelScope.launch {
            _userList.postValue(getUserListUseCase())
        }
    }
}