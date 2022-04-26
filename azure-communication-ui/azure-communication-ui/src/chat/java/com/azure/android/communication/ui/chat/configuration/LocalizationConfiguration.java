// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.android.communication.ui.chat.configuration;


import com.azure.android.communication.ui.chat.ChatComposite;

import java.util.Locale;

/**
 * Localization configuration to provide for ChatComposite.
 *
 * <pre>
 *
 * &#47;&#47; Initialize the chat composite builder with different parameters
 * final ChatCompositeBuilder builder = new ChatCompositeBuilder&#40;&#41;
 *     .localization&#40;new LocalizationConfiguration&#40;LanguageCode.FRENCH&#41;&#41;;
 *
 * final ChatCompositeBuilder builder = new ChatCompositeBuilder&#40;&#41;
 *     .localization&#40;new LocalizationConfiguration&#40;LanguageCode.FRENCH, layoutDirection&#41;&#41;;
 *
 * &#47;&#47; Build the chat composite
 * ChatComposite chatComposite = builder.build&#40;&#41;;
 *
 * </pre>
 *
 * @see ChatComposite
 */
public final class LocalizationConfiguration {
    private final Locale locale;
    private int layoutDirection;

    /**
     * Create Localization configuration.
     *
     * Set {@link Locale};.
     *
     * @param locale    The {@link Locale}; eg,. LanguageCode.FRENCH
     * @param layoutDirection layout direction eg,. LayoutDirection.RTL
     */
    public LocalizationConfiguration(final Locale locale, final int layoutDirection) {
        this.locale = locale;
        this.layoutDirection = layoutDirection;
    }

    /**
     * Create Localization configuration.
     *
     * @param locale The {@link Locale}; eg,. LanguageCode.FRENCH
     */
    public LocalizationConfiguration(final Locale locale) {
        this.locale = locale;
    }

    /**
     * Get current LanguageCode enum
     *
     * @return The {@link Locale};
     */
    public Locale getLanguageCode() {
        return locale;
    }

    /**
     * Get layoutDirection int.
     *
     * @return layoutDirection int
     */
    public int getLayoutDirection() {
        return layoutDirection;
    }
}
