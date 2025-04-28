package com.sanya1am.consecutivepractices.profile.presentation.viewModel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.sanya1am.consecutivepractices.profile.domain.repository.IProfileRepository
import com.sanya1am.consecutivepractices.profile.presentation.state.EditProfileState
import kotlinx.coroutines.launch

class EditProfileViewModel(
    private val repository: IProfileRepository
) : ViewModel() {
    private val mutableState = MutableEditProfileState()
    val viewState = mutableState as EditProfileState

    init {
        viewModelScope.launch {
            repository.getProfile()?.let {
                mutableState.name = it.name
                mutableState.photoUri = Uri.parse(it.photoUri)
                mutableState.documentUrl = it.documentUrl
            }
        }
        mutableState.isShowPermissionDialog = true
    }

    fun onNameChanged(name: String) {
        mutableState.name = name
    }

    fun onDoneClicked() {
        viewModelScope.launch {
            repository.setProfile(
                viewState.name,
                viewState.photoUri.toString(),
                viewState.documentUrl
            )
        }
    }

    fun onAvatarClicked() {
        mutableState.isShowSelectDialog = true
    }

    fun onSelectDismiss() {
        mutableState.isShowSelectDialog = false
    }

    fun onImageSelected(uri: Uri?) {
        uri?.let { mutableState.photoUri = it }
    }

    fun onPermissionClosed() {
        mutableState.isShowPermissionDialog = false
    }

    fun onDocumentChanged(url: String) {
        mutableState.documentUrl = url
    }

    private class MutableEditProfileState : EditProfileState {
        override var name by mutableStateOf("")
        override var photoUri: Uri by mutableStateOf(Uri.EMPTY)
        override var documentUrl by mutableStateOf("")
        override var isShowPermissionDialog by mutableStateOf(false)
        override var isShowSelectDialog by mutableStateOf(false)
    }
}
