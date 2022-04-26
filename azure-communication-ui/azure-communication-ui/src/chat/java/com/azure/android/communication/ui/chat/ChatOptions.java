// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.android.communication.ui.chat;

import com.azure.android.communication.common.CommunicationTokenCredential;

/***
 * Options to start chat experience using {@link ChatComposite}.
 */
public final class ChatOptions {
    private final CommunicationTokenCredential credential;
    private final String displayName;
    private final String endpoint;

    /**
     * Create {@link ChatOptions}.
     *
     * @param credential {@link CommunicationTokenCredential}
     * @param endpoint   ACS resource endpoint. (eg. "https://&lt;RESOURCE_NAME&gt;.communcationservices.azure.com")
     */
    public ChatOptions(
            final CommunicationTokenCredential credential,
            final String endpoint) {
        this(credential, endpoint, "");
    }

    /**
     * Create {@link ChatOptions}.
     *
     * @param credential {@link CommunicationTokenCredential}
     * @param endpoint   ACS resource endpoint. (eg. "https://&lt;RESOURCE_NAME&gt;.communcationservices.azure.com")
     * @param displayName user display name
     */
    public ChatOptions(final CommunicationTokenCredential credential,
                            final String endpoint,
                            final String displayName) {

        this.credential = credential;
        this.displayName = displayName;
        this.endpoint = endpoint;
    }

    /**
     * Get {@link CommunicationTokenCredential}.
     *
     * @return {@link String}
     */
    public CommunicationTokenCredential getCredential() {
        return credential;
    }

    /**
     * Get user display name.
     *
     * @return {@link String}
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * ACS resource endpoint. (eg. "https://&lt;RESOURCE_NAME&gt;.communcationservices.azure.com")
     *
     * @return {@link String}
     */
    public String getEndpoint() {
        return endpoint;
    }
}
