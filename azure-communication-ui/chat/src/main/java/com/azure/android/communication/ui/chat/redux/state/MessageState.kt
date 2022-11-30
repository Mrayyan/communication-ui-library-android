package com.azure.android.communication.ui.chat.redux.state

    internal enum class MessageSendStatus  {
        UNSENT,
        SENT,
        SEEN,
        FAILED,
    }

    internal data class MessageState(val state: MessageSendStatus)
