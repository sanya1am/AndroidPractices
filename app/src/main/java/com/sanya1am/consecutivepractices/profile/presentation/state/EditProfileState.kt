package com.sanya1am.consecutivepractices.profile.presentation.state

import android.net.Uri

interface EditProfileState {
    val name: String
    val photoUri: Uri
    val documentUrl: String
    val isShowPermissionDialog: Boolean
    val isShowSelectDialog: Boolean
}