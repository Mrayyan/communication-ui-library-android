// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.android.communication.ui.chat.redux.reducer

import com.azure.android.communication.ui.chat.redux.action.Action
import com.azure.android.communication.ui.chat.redux.state.ParticipantsState

internal interface ParticipantsReducer : Reducer<ParticipantsState>

internal class ParticipantsReducerImpl : ParticipantsReducer {
    override fun reduce(state: ParticipantsState, action: Action): ParticipantsState {
        return state
    }
}