// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.android.communication.ui.callingcompositedemoapp.launcher;

import com.azure.android.communication.ui.chat.ChatComposite;

public class ChatCompositeJavaLauncher implements ChatCompositeLauncher {

    public ChatCompositeJavaLauncher() {
    }

    @Override
    public void launch() {
        final ChatComposite chatComposite = new ChatComposite();
        chatComposite.launch();
    }
}
