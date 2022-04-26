// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.android.communication.ui.chat.configuration;

import com.azure.android.communication.ui.chat.ChatComposite;

/**
 * Theme configuration to provide for CallComposite.
 *
 * <pre>
 *
 * &#47;&#47; Initialize the call composite builder
 * final CallCompositeBuilder builder = new CallCompositeBuilder&#40;&#41;
 *     .theme&#40;new ThemeConfiguration&#40;themeId&#41;&#41;;
 *
 * &#47;&#47; Build the call composite
 * CallComposite callComposite = builder.build&#40;&#41;;
 *
 * </pre>
 *
 * @see ChatComposite
 */
public final class ThemeConfiguration {
    private final int theme;

    /**
     * Create Theme configuration.
     *
     * @param theme Style resource id that is derived from "AzureCommunicationUI.Theme.Chat" theme.
     */
    public ThemeConfiguration(final int theme) {
        this.theme = theme;
    }

    /**
     * Get theme id. Style resource id that is derived from "AzureCommunicationUI.Theme.Chat" theme.
     *
     * @return theme id
     */
    public int getTheme() {
        return theme;
    }
}
