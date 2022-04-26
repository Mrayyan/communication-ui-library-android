// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.android.communication.ui.callingcompositedemoapp

import androidx.lifecycle.ViewModel
import com.azure.android.communication.ui.callingcompositedemoapp.launcher.ChatCompositeJavaLauncher
import com.azure.android.communication.ui.callingcompositedemoapp.launcher.ChatCompositeLauncher

class ChatLauncherViewModel : ViewModel() {
    fun doLaunch(activity: ChatLauncherActivity) {
        val launcher: ChatCompositeLauncher = ChatCompositeJavaLauncher() { "token" }
        launcher.launch(activity)
    }
}
