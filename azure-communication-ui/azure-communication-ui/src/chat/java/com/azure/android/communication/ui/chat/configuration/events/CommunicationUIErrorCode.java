// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.android.communication.ui.chat.configuration.events;

import com.azure.android.core.util.ExpandableStringEnum;

import java.util.Collection;

/**
 * Defines values for CommunicationUIErrorCode.
 */
public final class CommunicationUIErrorCode extends ExpandableStringEnum<CommunicationUIErrorCode> {

    public static final CommunicationUIErrorCode CHAT_THREAD_ADD_USER = fromString("CHAT_THREAD_ADD_USER");


    /**
     * Creates or finds a CallCompositeErrorCode from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding CallCompositeErrorCode.
     */
    private static CommunicationUIErrorCode fromString(final String name) {
        return fromString(name, CommunicationUIErrorCode.class);
    }

    /**
     * @return known CallCompositeErrorCode values.
     */
    public static Collection<CommunicationUIErrorCode> values() {
        return values(CommunicationUIErrorCode.class);
    }
}

