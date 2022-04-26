// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.android.communication.ui.chat;


import com.azure.android.communication.ui.chat.configuration.LocalizationConfiguration;
import com.azure.android.communication.ui.chat.configuration.ThemeConfiguration;

/**
 * Builder for creating {@link ChatComposite}.
 */
public final class ChatCompositeBuilder {

    private ThemeConfiguration themeConfig = null;
    private LocalizationConfiguration localizationConfig = null;

    /**
     * Sets an optional theme for call-composite to use by {@link ChatComposite}.
     *
     * @param theme {@link ThemeConfiguration}.
     * @return {@link ChatCompositeBuilder}
     */
    public ChatCompositeBuilder theme(final ThemeConfiguration theme) {
        this.themeConfig = theme;
        return this;
    }

    /**
     * Sets an optional localization for call-composite to use by {@link ChatComposite}.
     *
     * @param localization {@link LocalizationConfiguration}.
     * @return {@link ChatCompositeBuilder}
     */
    public ChatCompositeBuilder localization(final LocalizationConfiguration localization) {
        this.localizationConfig = localization;
        return this;
    }

    /**
     * Creates {@link ChatComposite}.
     *
     * @return {@link ChatComposite}
     */
    public ChatComposite build() {
        return new ChatComposite();
    }
}
