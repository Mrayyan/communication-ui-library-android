// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.android.communication.ui.calling.presentation.fragment.calling.hangup

import com.azure.android.communication.ui.calling.redux.action.Action
import com.azure.android.communication.ui.calling.redux.action.CallingAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class ConfirmLeaveOverlayViewModel(private val dispatch: (Action) -> Unit) {
    private val shouldDisplayConfirmLeaveOverlayStateFlow = MutableStateFlow(false)

    fun getShouldDisplayConfirmLeaveOverlayFlow(): StateFlow<Boolean> {
        return shouldDisplayConfirmLeaveOverlayStateFlow
    }

    fun confirm() {
        dispatchAction(action = CallingAction.CallEndRequested())
    }

    fun cancel() {
        shouldDisplayConfirmLeaveOverlayStateFlow.value = false
    }

    fun requestExitConfirmation() {
        shouldDisplayConfirmLeaveOverlayStateFlow.value = true
    }

    private fun dispatchAction(action: Action) {
        dispatch(action)
    }
}
