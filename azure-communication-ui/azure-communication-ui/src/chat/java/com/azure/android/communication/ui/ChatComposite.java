// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.android.communication.ui;

import android.util.Log;

import com.azure.android.communication.chat.ChatThreadClientBuilder;

public class ChatComposite {
    public void launch() {
        final ChatThreadClientBuilder chatThreadAsyncClient = new ChatThreadClientBuilder();
        Log.d("ChatComposite", "launch");
    }
}



