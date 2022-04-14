// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.android.communication.ui.callingcompositedemoapp

import androidx.lifecycle.ViewModel
import com.azure.android.communication.ui.callingcompositedemoapp.launcher.ChatCompositeKotlinLauncher
import com.azure.android.communication.ui.callingcompositedemoapp.launcher.ChatCompositeLauncher

class ChatLauncherViewModel : ViewModel() {
    fun doLaunch() {
        val launcher: ChatCompositeLauncher = ChatCompositeKotlinLauncher()
        launcher.launch()
    }
}
