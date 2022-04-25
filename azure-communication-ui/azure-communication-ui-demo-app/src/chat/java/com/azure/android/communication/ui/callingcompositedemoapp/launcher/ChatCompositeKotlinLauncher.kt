// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.android.communication.ui.callingcompositedemoapp.launcher

import com.azure.android.communication.ui.chat.ChatComposite

class ChatCompositeKotlinLauncher :
        ChatCompositeLauncher {

    override fun launch() {
        val chatComposite = ChatComposite()
        chatComposite.launch()
    }
}
