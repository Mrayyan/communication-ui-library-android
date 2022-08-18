// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.android.communication.ui.calling.models;

import androidx.annotation.Nullable;

/**
 * Provides navigation bar view data to Call Composite including title and subTitle
 *
 * Create an instance of CallCompositeNavigationBarViewData and pass it to
 * {@link CallCompositeLocalOptions} when launching a new call
 *
 */
public class CallCompositeNavigationBarViewData {
    @Nullable
    private final String callTitle;

    @Nullable
    private final String callSubTitle;

    /**
     * Constructs a {@link  CallCompositeNavigationBarViewData}
     * @param callTitle Default title "Setup" is used if not customized
     * @param callSubTitle Will appear as a 2nd line under callTitle. Will *not* appear unless
     *                     callTitle is also specified
     */
    public CallCompositeNavigationBarViewData(@Nullable final String callTitle, @Nullable final String callSubTitle) {
        this.callTitle = callTitle;
        this.callSubTitle = callSubTitle;
    }

    @Nullable
    public String getCallTitle() {
        return callTitle;
    }

    @Nullable
    public String getCallSubTitle() {
        return callSubTitle;
    }
}