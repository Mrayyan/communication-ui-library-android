// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.android.communication.ui.presentation.fragment.calling.controlbar

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.azure.android.communication.ui.R
import com.azure.android.communication.ui.redux.state.AudioDeviceSelectionStatus
import com.azure.android.communication.ui.redux.state.AudioOperationalStatus
import com.azure.android.communication.ui.redux.state.CameraOperationalStatus
import com.azure.android.communication.ui.redux.state.PermissionStatus
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import androidx.core.content.ContextCompat.getSystemService

import android.content.DialogInterface

import android.widget.Toast

import android.app.AlertDialog
import android.provider.Settings.System.getString
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.inputmethod.InputMethodManager

import android.widget.EditText
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import com.azure.android.communication.ui.model.UserSpeechObject
import com.azure.android.communication.ui.presentation.fragment.calling.localuser.LocalParticipantViewModel
import com.google.firebase.FirebaseApp
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text


internal class ControlBarView : LinearLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    private lateinit var mUserDisplayName: String
    private lateinit var viewModel: ControlBarViewModel
    private lateinit var endCallButton: ImageButton
    private lateinit var cameraToggle: ImageButton
    private lateinit var micToggle: ImageButton
    private lateinit var speakText: ImageButton
    private lateinit var callAudioDeviceButton: ImageButton
    private lateinit var requestCallEndCallback: () -> Unit
    private lateinit var openAudioDeviceSelectionMenuCallback: () -> Unit

    override fun onFinishInflate() {
        super.onFinishInflate()
        endCallButton = findViewById(R.id.azure_communication_ui_call_end_call_button)
        cameraToggle = findViewById(R.id.azure_communication_ui_call_cameraToggle)
        speakText = findViewById(R.id.azure_communication_ui_call_speakText)
        micToggle = findViewById(R.id.azure_communication_ui_call_call_audio)
        callAudioDeviceButton = findViewById(R.id.azure_communication_ui_call_audio_device_button)
        subscribeClickListener()
    }

    fun start(
        viewLifecycleOwner: LifecycleOwner,
        viewModel: ControlBarViewModel,
        viewModelLocalParticipant: LocalParticipantViewModel,
        requestCallEnd: () -> Unit,
        openAudioDeviceSelectionMenu: () -> Unit,
    ) {
        this.viewModel = viewModel
        this.requestCallEndCallback = requestCallEnd
        this.openAudioDeviceSelectionMenuCallback = openAudioDeviceSelectionMenu



        setupAccessibility()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getAudioOperationalStatusStateFlow().collect {
                updateMic(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getCameraStateFlow().collect {
                updateCamera(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getAudioDeviceSelectionStatusStateFlow().collect {
                setAudioDeviceButtonState(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModelLocalParticipant.getDisplayNameStateFlow().collect {
                if (it != null) {
                    mUserDisplayName = it
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getShouldEnableMicButtonStateFlow().collect {
                micToggle.isEnabled = it
            }
        }
    }

    private fun setupAccessibility() {
        val localizationProvider = viewModel.getLocalizationProvider()
        endCallButton.contentDescription = localizationProvider
            .getLocalizedString(
                context,
                R.string.azure_communication_ui_calling_view_button_hang_up_accessibility_label
            )

        callAudioDeviceButton.contentDescription = localizationProvider
            .getLocalizedString(
                context,
                R.string.azure_communication_ui_calling_view_button_device_options_accessibility_label
            )

        cameraToggle.contentDescription = localizationProvider
            .getLocalizedString(
                context,
                R.string.azure_communication_ui_calling_view_button_toggle_video_accessibility_label
            )

        micToggle.contentDescription = localizationProvider
            .getLocalizedString(
                context,
                R.string.azure_communication_ui_calling_view_button_toggle_audio_accessibility_label
            )
    }

    private fun updateCamera(cameraState: ControlBarViewModel.CameraModel) {
        val permissionIsNotDenied = cameraState.cameraPermissionState != PermissionStatus.DENIED

        when (cameraState.cameraState.operation) {
            CameraOperationalStatus.ON -> {
                cameraToggle.isSelected = true
                cameraToggle.isEnabled = permissionIsNotDenied
            }
            CameraOperationalStatus.OFF -> {
                cameraToggle.isSelected = false
                cameraToggle.isEnabled = permissionIsNotDenied
            }
            else -> {
                // disable button
                cameraToggle.isEnabled = false
            }
        }
    }

    private fun updateMic(audioOperationalStatus: AudioOperationalStatus) {
        when (audioOperationalStatus) {
            AudioOperationalStatus.ON -> {
                // show un-mute icon
                micToggle.isSelected = true
            }
            AudioOperationalStatus.OFF -> {
                // show mute icon
                micToggle.isSelected = false
            }
        }
    }

    private fun setAudioDeviceButtonState(audioDeviceSelectionStatus: AudioDeviceSelectionStatus) {
        when (audioDeviceSelectionStatus) {
            AudioDeviceSelectionStatus.SPEAKER_SELECTED -> {
                callAudioDeviceButton.setImageResource(
                    R.drawable.azure_communication_ui_ic_fluent_speaker_2_24_filled_composite_button_enabled
                )
            }
            AudioDeviceSelectionStatus.RECEIVER_SELECTED -> {
                callAudioDeviceButton.setImageResource(
                    R.drawable.azure_communication_ui_ic_fluent_speaker_2_24_regular_composite_button_filled
                )
            }
            AudioDeviceSelectionStatus.BLUETOOTH_SCO_SELECTED -> {
                callAudioDeviceButton.setImageResource(
                    // Needs an icon
                    R.drawable.azure_communication_ui_ic_fluent_speaker_bluetooth_24_regular
                )
            }
        }
    }

    private fun subscribeClickListener() {
        endCallButton.setOnClickListener {
            requestCallEndCallback()
        }
        micToggle.setOnClickListener {
            if (micToggle.isSelected) {
                viewModel.turnMicOff()
            } else {
                viewModel.turnMicOn()
            }
        }
        speakText.setOnClickListener {
            val builder = AlertDialog.Builder(context, R.style.AzureCommunicationUICalling_AlertDialog)
            val input = EditText(context)
            input.setTextColor(getColor(context, R.color.azure_communication_ui_color_transparent_background))


            builder
                .setTitle(context.resources.getString(R.string.azure_communication_ui_text_to_speech))
                .setMessage(context.resources.getString(R.string.azure_communication_ui_text_to_speech_desc))
                .setView(input)
                .setPositiveButton(android.R.string.ok
                ) { _, _ ->
                    val value = input.text.toString()
                    if (value.trim { it <= ' ' }.isEmpty()) {
                        Toast.makeText(context, "Please write something!", Toast.LENGTH_SHORT)
                            .show()
                    } else {

                        val database = Firebase.database
                        val myRef = database.reference

                        val userSpeechObject = UserSpeechObject(mUserDisplayName,
                            value,
                            System.currentTimeMillis().toString())

                        myRef.push().setValue(userSpeechObject)
                    }

                    val imm: InputMethodManager? =
                        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
                    imm?.hideSoftInputFromWindow(input.windowToken, 0)
                }
                .setNegativeButton(android.R.string.cancel,
                    DialogInterface.OnClickListener { dialog, which ->
                        val imm: InputMethodManager? =
                            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
                        imm?.hideSoftInputFromWindow(input.windowToken, 0)
                    })

            builder.show()
            input.requestFocus()
            val imm: InputMethodManager? =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        }

        cameraToggle.setOnClickListener {
            if (cameraToggle.isSelected) {
                viewModel.turnCameraOff()
            } else {
                viewModel.turnCameraOn()
            }
        }
        callAudioDeviceButton.setOnClickListener {
            openAudioDeviceSelectionMenuCallback()
        }
    }
}
