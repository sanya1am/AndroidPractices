package com.sanya1am.consecutivepractices.profile.presentation.screens

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import coil3.compose.AsyncImage
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.LocalStackNavigation
import com.sanya1am.consecutivepractices.R
import com.sanya1am.consecutivepractices.profile.presentation.viewModel.EditProfileViewModel
import com.sanya1am.consecutivepractices.ui.theme.Spacing
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId

@Parcelize
class EditProfileScreen (
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content(modifier: Modifier) {
        val navigation = LocalStackNavigation.current

        val viewModel = koinViewModel<EditProfileViewModel> { parametersOf(navigation) }
        val state = viewModel.viewState

        val context = LocalContext.current

        val pickMedia: ActivityResultLauncher<PickVisualMediaRequest> =
            rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                viewModel.onImageSelected(uri)
            }

        var imageUri by remember { mutableStateOf<Uri?>(null) }

        val mGetContent = rememberLauncherForActivityResult(
            ActivityResultContracts.TakePicture()
        ) { success: Boolean ->
            if (success) {
                viewModel.onImageSelected(imageUri)
            }
        }

        LaunchedEffect(state.shouldLaunchCamera) {
            if (state.shouldLaunchCamera && state.tempImageUri != null) {
                imageUri = state.tempImageUri
                imageUri?.let { mGetContent.launch(it) }
                viewModel.onCameraLaunchHandled()
            }
        }

        val requestPermissionLauncher =
            rememberLauncherForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ) { map: Map<String, Boolean> ->
                if (map.values.contains(false)) {
                    val dialog = AlertDialog.Builder(context)
                        .setMessage(context.getString(R.string.needs_permission))
                        .setCancelable(false)
                        .setPositiveButton(context.getString(R.string.ok)) { _, _ ->
                            viewModel.back()
                        }

                    dialog.show()
                }
                viewModel.onPermissionClosed()
            }

        Scaffold(
            contentWindowInsets = WindowInsets(0.dp),
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = stringResource(R.string.profile))
                    },
                    actions = {
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = null,
                            Modifier
                                .padding(end = 8.dp)
                                .clickable { viewModel.onDoneClicked() }
                        )
                    },
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            Modifier
                                .padding(end = 8.dp)
                                .clickable { viewModel.back() }
                        )
                    }
                )
            }
        ) {
            Column(
                Modifier
                    .padding(it)
                    .padding(Spacing.medium)
            ) {
                AsyncImage(
                    model = state.photoUri,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(vertical = Spacing.small)
                        .size(128.dp)
                        .clip(CircleShape)
                        .clickable { viewModel.onAvatarClicked() },
                    error = painterResource(R.drawable.avatar)
                )

                TextField(
                    value = state.name,
                    onValueChange = { viewModel.onNameChanged(it) },
                    label = { Text(stringResource(R.string.name)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = Spacing.small)
                )

                TextField(
                    value = state.documentUrl,
                    onValueChange = { viewModel.onDocumentChanged(it) },
                    label = { Text(stringResource(R.string.url_pdf)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = Spacing.small)
                )

                TextField(
                    value = state.notifTimeString,
                    onValueChange = { viewModel.onTimeChanged(it) },
                    label = { Text(stringResource(R.string.time_text)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = Spacing.small),
                    isError = state.notifTimeError != null,
                    trailingIcon = {
                        Icon(
                            painter =  painterResource(id = R.drawable.time),
                            contentDescription = null,
                            modifier = Modifier
                                .clickable { viewModel.onTimeInputClicked() }
                        )
                    }
                )

                state.notifTimeError?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                TextField(
                    value = state.notifDateString,
                    onValueChange = { viewModel.onDateChanged(it) },
                    label = { Text(stringResource(R.string.date_text)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = Spacing.small),
                    readOnly = true,
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.calendar),
                            contentDescription = null,
                            modifier = Modifier.clickable { viewModel.onDateInputClicked() }
                        )
                    }
                )
            }
        }

        if (state.isShowTimePicker) {
            val timePickerState = rememberTimePickerState(
                initialHour = state.notifTime.hour,
                initialMinute = state.notifTime.minute,
                is24Hour = true
            )

            AlertDialog(
                onDismissRequest = { viewModel.onTimeCanceled() },
                dismissButton = {
                    TextButton(onClick = { viewModel.onTimeCanceled() } ) {
                        Text(stringResource(R.string.cancel))
                    }
                },
                confirmButton = {
                    TextButton(onClick = { viewModel.onTimeConfirmed(timePickerState.hour, timePickerState.minute) }) {
                        Text(stringResource(R.string.ok))
                    }
                },
                text = { TimePicker(state = timePickerState) }
            )
        }

        if (state.isShowDatePicker) { // ?
            val datePickerState = rememberDatePickerState(
                initialSelectedDateMillis = state.notifDate
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli()
            )

            DatePickerDialog(
                onDismissRequest = { viewModel.onDateCanceled() },
                confirmButton = {
                    TextButton(
                        onClick = {
                            datePickerState.selectedDateMillis?.let { millis ->
                                val date = Instant.ofEpochMilli(millis)
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate()

                                viewModel.onDateConfirmed(
                                    date.year,
                                    date.monthValue, // monthValue = 1..12
                                    date.dayOfMonth
                                )
                            }
                        }
                    ) {
                        Text(stringResource(R.string.ok))
                    }
                },
                dismissButton = {
                    TextButton(onClick = { viewModel.onDateCanceled() }) {
                        Text(stringResource(R.string.cancel))
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }

        if (state.isShowSelectDialog) {
            Dialog(onDismissRequest = { viewModel.onSelectDismiss() }) {
                Surface (
                    modifier = Modifier.fillMaxWidth(0.8f),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(
                            text = stringResource(R.string.camera),
                            modifier = Modifier
                                .clickable {
                                    viewModel.onCameraSelected()
                                    viewModel.onSelectDismiss()
                                }
                        )
                        Text(
                            text = stringResource(R.string.gallery),
                            modifier = Modifier
                                .clickable {
                                    pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                                    viewModel.onSelectDismiss()
                                }
                        )
                    }
                }
            }
        }

        if (state.isShowPermissionDialog) {
            LaunchedEffect(Unit) {
                val permissions = mutableListOf<String>()
                if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.Q &&
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU &&
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    permissions.add(Manifest.permission.POST_NOTIFICATIONS)
                }

                requestPermissionLauncher.launch(permissions.toTypedArray())
            }
        }
    }
}
