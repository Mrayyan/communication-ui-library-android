// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.android.communication.ui.calling.presentation.fragment

import com.azure.android.communication.ui.calling.presentation.fragment.calling.CallingViewModel
import com.azure.android.communication.ui.calling.presentation.fragment.setup.SetupViewModel

internal data class ViewModelFactory(
        val callViewModel: CallingViewModel,
        val setupViewModel: SetupViewModel,
)
