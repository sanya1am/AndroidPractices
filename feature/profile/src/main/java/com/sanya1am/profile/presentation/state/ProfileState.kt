package com.sanya1am.profile.presentation.state

import android.net.Uri

interface ProfileState {
    val name: String
    val photoUri: Uri
    val documentUrl: String
}