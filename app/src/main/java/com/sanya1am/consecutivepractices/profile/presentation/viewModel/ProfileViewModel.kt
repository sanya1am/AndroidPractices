package com.sanya1am.consecutivepractices.profile.presentation.viewModel

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Environment
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanya1am.consecutivepractices.profile.domain.repository.IProfileRepository
import com.sanya1am.consecutivepractices.profile.presentation.state.ProfileState
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent
import java.io.File
import java.util.Date

class ProfileViewModel (
    private val repository: IProfileRepository
) : ViewModel() {
    private val mutableState = MutableProfileState()
    val viewState = mutableState as ProfileState
    val context: Context by KoinJavaComponent.inject(Context::class.java)

    init {
        viewModelScope.launch {
            repository.observeProfile().collect {
                mutableState.name = it.name
                mutableState.photoUri = Uri.parse(it.photoUri)
                mutableState.documentUrl = it.documentUrl
            }
        }
    }

    fun onDocumentClicked() {
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val request = DownloadManager.Request(viewState.documentUrl.toUri())
        val filename = "resume_${Date().time}.pdf"

        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setTitle("Загрузка")
        request.setDescription("Загрузка файла резюме")

        val downloadId = downloadManager.enqueue(request)
        val onComplete: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                if (id == downloadId) {
                    onDownloadComplete(context, filename)
                }
            }
        }

        ContextCompat.registerReceiver(
            context,
            onComplete,
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE),
            ContextCompat.RECEIVER_EXPORTED
        )
    }

    private fun onDownloadComplete(context: Context, filename: String) {
        val file = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            filename
        )
        val uri = FileProvider.getUriForFile(
            context, context.applicationContext?.packageName + ".provider", file
        )
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(uri, "application/pdf")
        intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION + Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    private class MutableProfileState: ProfileState {
        override var name by mutableStateOf("")
        override var photoUri by mutableStateOf(Uri.EMPTY)
        override var documentUrl by mutableStateOf("")
    }
}