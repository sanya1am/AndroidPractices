package com.sanya1am.profile.presentation.viewModel

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.modo.stack.StackNavContainer
import com.github.terrakok.modo.stack.back
import com.sanya1am.profile.R
import com.sanya1am.profile.domain.repository.IProfileRepository
import com.sanya1am.profile.presentation.receiver.NotificationReceiver
import com.sanya1am.profile.presentation.state.EditProfileState
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import java.io.File
import java.util.Date

class EditProfileViewModel(
    private val repository: IProfileRepository,
    private val navigation: StackNavContainer
) : ViewModel() {
    private val mutableState = MutableEditProfileState()
    val viewState = mutableState as EditProfileState
    private val context: Context by KoinJavaComponent.inject(Context::class.java)
    private val formatter = DateTimeFormatter.ofPattern("HH:mm")
    private val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

    init {
        viewModelScope.launch {
            repository.getProfile()?.let {
                mutableState.name = it.name
                mutableState.photoUri = Uri.parse(it.photoUri)
                mutableState.documentUrl = it.documentUrl
                tryParseTime(it.notifTime)?.let { time ->
                    mutableState.notifTime = time
                    updateTimeString()
                }
                tryParseDate(it.notifDate)?.let { date ->
                    mutableState.notifDate = date
                    updateDateString()
                }

            }
        }
        mutableState.isShowPermissionDialog = true
    }

    fun onNameChanged(name: String) {
        mutableState.name = name
    }

    fun onDoneClicked() {
        validateTime()
        if (mutableState.notifTimeError != null) return

        viewModelScope.launch {
            repository.setProfile(
                viewState.name,
                viewState.photoUri.toString(),
                viewState.documentUrl,
                viewState.notifTime,
                viewState.notifDate
            )
            saveNotification()
            back()
        }
    }

    fun onAvatarClicked() {
        mutableState.isShowSelectDialog = true
    }

    fun onSelectDismiss() {
        mutableState.isShowSelectDialog = false
    }

    fun onCameraLaunchHandled() {
        mutableState.shouldLaunchCamera = false
    }

    fun onCameraSelected() {
        val baseDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val pictureFile = File(baseDir, "picture_${Date().time}.jpg")
        val uri = FileProvider.getUriForFile(
            context,
            context.packageName + ".provider",
            pictureFile
        )
        mutableState.tempImageUri = uri
        mutableState.shouldLaunchCamera = true
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

    fun back() {
        navigation.back()
    }

    fun onTimeInputClicked() {
        mutableState.isShowTimePicker = true
    }

    fun onDateInputClicked() {
        mutableState.isShowDatePicker = true
    }

    fun onTimeChanged(time: String) {
        mutableState.notifTimeString = time
        validateTime()
    }

    fun onTimeConfirmed(h: Int, m: Int) {
        mutableState.notifTime = LocalTime.of(h, m)
        mutableState.notifTimeError = null
        updateTimeString()
        onTimeCanceled()
    }

    fun onDateConfirmed(year: Int, month: Int, day: Int) {
        mutableState.notifDate = LocalDate.of(year, month, day)
        updateDateString()
        onDateCanceled()
    }

    fun onTimeCanceled() {
        mutableState.isShowTimePicker = false
    }

    fun onDateCanceled() {
        mutableState.isShowDatePicker = false
    }

    private fun saveNotification() {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val dateTime = LocalDateTime.of(viewState.notifDate, viewState.notifTime)
        val timeInMillis = dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

        val notifyIntent = Intent(context, NotificationReceiver::class.java)

        notifyIntent.putExtras(
            Bundle().apply {
                putString("NOTIFICATION", "Пора сделать перерыв, ${viewState.name}!")
            }
        )

        val notifyPendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            notifyIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        try {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                timeInMillis,
                notifyPendingIntent
            )
        } catch (e: SecurityException) {
            Log.d("alarmManager", e.message.toString())
        }
    }

    private fun validateTime() {
        try {
            mutableState.notifTime = LocalTime.parse(mutableState.notifTimeString, formatter)
            mutableState.notifTimeError = null
        } catch (e: Exception) {
            mutableState.notifTimeError = context.getString(R.string.incorrect_time_format)
        }
    }

    fun onDateChanged(date: String) {
        mutableState.notifDateString = date
        updateDateString()
    }

    private fun updateDateString() {
        mutableState.notifDateString = viewState.notifDate.format(dateFormatter)
    }

    private fun updateTimeString() {
        mutableState.notifTimeString = formatter.format(viewState.notifTime)
    }

    private fun tryParseTime(date: String): LocalTime? {
        return try {
            LocalTime.parse(date)
        } catch (e: Exception) { null }
    }

    private fun tryParseDate(date: String): LocalDate? {
        return try {
            LocalDate.parse(date)
        } catch (e: Exception) { null }
    }

    private class MutableEditProfileState : EditProfileState {
        override var name by mutableStateOf("")
        override var photoUri: Uri by mutableStateOf(Uri.EMPTY)

        override var shouldLaunchCamera by mutableStateOf(false)
        override var tempImageUri: Uri? by mutableStateOf(null)

        override var documentUrl by mutableStateOf("")
        override var notifTime: LocalTime by mutableStateOf(LocalTime.now())
        override var notifTimeString by mutableStateOf("")
        override var notifTimeError: String? by mutableStateOf(null)
        override var isShowPermissionDialog by mutableStateOf(false)
        override var isShowSelectDialog by mutableStateOf(false)
        override var isShowTimePicker by mutableStateOf(false)

        override var notifDate: LocalDate by mutableStateOf(LocalDate.now()) // ?
        override var notifDateString by mutableStateOf("") // ?
        override var isShowDatePicker by mutableStateOf(false) // ?
    }
}
