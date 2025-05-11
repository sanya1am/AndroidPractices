package com.sanya1am.consecutivepractices.profile.presentation.state

import android.net.Uri
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime

interface EditProfileState {
    val name: String
    val photoUri: Uri
    val documentUrl: String
    val notifTime: LocalTime
    val notifTimeString: String
    val notifTimeError: String?
    val isShowPermissionDialog: Boolean
    val isShowSelectDialog: Boolean
    val isShowTimePicker: Boolean
    val notifDate: LocalDate // ?
    val notifDateString: String // ?
    val isShowDatePicker: Boolean
}