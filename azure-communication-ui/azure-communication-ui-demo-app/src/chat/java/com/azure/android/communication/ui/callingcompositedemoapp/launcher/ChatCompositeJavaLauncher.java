// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.android.communication.ui.callingcompositedemoapp.launcher;

import com.azure.android.communication.common.CommunicationTokenCredential;
import com.azure.android.communication.common.CommunicationTokenRefreshOptions;
import com.azure.android.communication.ui.callingcompositedemoapp.ChatLauncherActivity;
import com.azure.android.communication.ui.chat.ChatComposite;
import com.azure.android.communication.ui.chat.ChatOptions;

import java.util.concurrent.Callable;

public class ChatCompositeJavaLauncher implements ChatCompositeLauncher {

    private final Callable<String> tokenRefresher;

    public ChatCompositeJavaLauncher(final Callable<String> tokenRefresher) {
        this.tokenRefresher = tokenRefresher;
    }

    @Override
    public void launch(ChatLauncherActivity activity) {
        final CommunicationTokenRefreshOptions communicationTokenRefreshOptions =
                new CommunicationTokenRefreshOptions(tokenRefresher, true);
        final CommunicationTokenCredential communicationTokenCredential =
                new CommunicationTokenCredential(communicationTokenRefreshOptions);

        String endpoint = "https://<RESOURCE_NAME>.communcationservices.azure.com";

        final ChatOptions options = new ChatOptions(communicationTokenCredential, endpoint);

        final ChatComposite chatComposite = new ChatComposite();
        chatComposite.startChatThread(activity, options);
    }
}
