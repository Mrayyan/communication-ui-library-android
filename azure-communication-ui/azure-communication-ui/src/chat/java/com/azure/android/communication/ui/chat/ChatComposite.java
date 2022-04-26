// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.android.communication.ui.chat;

import android.content.Context;
import android.util.Log;

import com.azure.android.communication.chat.ChatThreadClientBuilder;

public final class ChatComposite {

    /**
     * Starts a new chat thread
     * @param context
     * @param chatOptions
     * @return chat thread ID
     */
    public String startChatThread(final Context context, final ChatOptions chatOptions) {
        final ChatThreadClientBuilder chatThreadAsyncClient = new ChatThreadClientBuilder();
        Log.d("ChatComposite", "launch-------------------------------");
        return "chat_thread_id";
    }

    public void joinChatThread(final Context context, final ChatOptions chatOptions, String chatThreadId) {

    }

    /**
     * @return chat thread ID
     */
    public String getChatThreadId() {
        return "chat_thread_id";
    }
}



