// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.android.communication.ui.chat.presentation.ui.viewmodel

import android.content.Context
import com.azure.android.communication.ui.chat.R
import com.azure.android.communication.ui.chat.models.EMPTY_MESSAGE_INFO_MODEL
import com.azure.android.communication.ui.chat.models.MessageInfoModel
import com.azure.android.communication.ui.chat.models.MessageStatus
import com.azure.android.communication.ui.chat.utilities.findMessageIdxById
import com.azure.android.core.rest.annotation.Immutable
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

private val timeFormatShort = DateTimeFormatter.ofPattern("EEEE")
private val timeFormatLong = DateTimeFormatter.ofPattern("EEEE MMMM dd")

@Immutable
internal class MessageViewModel(
    val message: MessageInfoModel,
    val showUsername: Boolean,
    val showTime: Boolean,
    val dateHeaderText: String?,
    val isLocalUser: Boolean,
    val showMessageSendStatusIcon: Boolean,
    val messageStatus: MessageStatus?,
)

internal fun List<MessageInfoModel>.toViewModelList(
    context: Context,
    localUserIdentifier: String,
    latestReadMessageTimestamp: OffsetDateTime = OffsetDateTime.MIN,
) =
    InfoModelToViewModelAdapter(
        context,
        this,
        localUserIdentifier,
        latestReadMessageTimestamp
    ) as List<MessageViewModel>

private class InfoModelToViewModelAdapter(
    private val context: Context,
    private val messages: List<MessageInfoModel>,
    private val localUserIdentifier: String,
    private val latestReadMessageTimestamp: OffsetDateTime,
) :
    List<MessageViewModel> {

    var lastReadMessageFound = false

    override fun get(index: Int): MessageViewModel {
        // Generate Message View Model here

        val lastMessage = try {
            messages[index - 1]
        } catch (e: IndexOutOfBoundsException) {
            EMPTY_MESSAGE_INFO_MODEL
        }

        //Index is from bottom to top
        //This message is the latest message

        val thisMessage = messages[index]

        val isLocalUser =
            thisMessage.senderCommunicationIdentifier?.id == localUserIdentifier || thisMessage.isCurrentUser
        val currentMessageTime = thisMessage.editedOn ?: thisMessage.createdOn


        /*
        *
        * Here are the requirements:
        *
        * if (msgFromLocalUser && latestMessage){
        * switch
        * case 1 (latestMessage == read) -> show read Icon.
        * }
        * latest message has been read and is not
        *
        * How to check if the message is the latest message?
        *
        * Get last screen message - show read receipt
        * For the thisMessage, if it is not isread, show the sent/unsent icon
        * Failed icon for all failed messages.
        *
        * Should shoe icon -- true, false only when read receipt shown once
        *
        * We will display the read receipt icon on the last seen message,
        * and either unsent or sent icon for the last unsent/sent message,
        * and failed icon for all failed messages

        *
        *
        *
        * Scenario 1) latest message is read
        * Scenario 2) 3rd latest message is read, the rest are sent
        * -> If current msg time is outside the read receipt and this is from local user, false
        *
        * Screnario 3) no message has been read, only sent
        *
        * */

        val isRead = isLocalUser && (currentMessageTime != null && currentMessageTime <= latestReadMessageTimestamp)

        if (!lastReadMessageFound){
            lastReadMessageFound = isRead
        } else {
            //Don't isRead Icon...
        }

        return MessageViewModel(

            messages[index],
            showUsername = !isLocalUser &&
                (lastMessage.senderCommunicationIdentifier?.id ?: "")
                != (thisMessage.senderCommunicationIdentifier?.id ?: ""),

            showTime =
            (
                (lastMessage.senderCommunicationIdentifier?.id ?: "")
                    != (thisMessage.senderCommunicationIdentifier?.id ?: "") &&
                    !thisMessage.isCurrentUser
                ) ||
                (thisMessage.isCurrentUser && !lastMessage.isCurrentUser),

            dateHeaderText = buildDateHeader(
                lastMessage.createdOn!!,
                thisMessage.createdOn ?: OffsetDateTime.now()
            ),

            isLocalUser = isLocalUser,
            //Only show this icon on the latest message
            //TODO: Change this to show. If last read message and latest message are not the same, show the icon
            showMessageSendStatusIcon = shouldMessageStatusIconBeShown(thisMessage),
            messageStatus = thisMessage.sendStatus
        )


    }

    private fun buildDateHeader(
        lastMessageDate: OffsetDateTime,
        thisMessageDate: OffsetDateTime,
    ): String? {

        val thisMessageDateZoned = thisMessageDate.atZoneSameInstant(ZoneId.systemDefault())
        val today = OffsetDateTime.now().withHour(0).withMinute(0)
            .withSecond(0)
            .withNano(0).atZoneSameInstant(ZoneId.systemDefault())

        val yesterday = today.minusDays(1)
        val weekAgo = today.minusWeeks(1)

        if (lastMessageDate.dayOfYear != thisMessageDate.dayOfYear) {
            if (thisMessageDateZoned.isAfter(today)) {
                return context.getString(R.string.azure_communication_ui_chat_message_today)
            } else if (thisMessageDateZoned.isAfter(yesterday)) {
                return context.getString(R.string.azure_communication_ui_chat_message_yesterday)
            } else if (thisMessageDateZoned.isAfter(weekAgo)) {
                return thisMessageDate.format(timeFormatShort)
            }
            return thisMessageDate.format(timeFormatLong)
        } else {
            return null
        }
    }

    private fun shouldMessageStatusIconBeShown(currentMessage: MessageInfoModel, isRead: Boolean): Boolean {
        /*
        *
        * return true
        * but if read receipt has been shown, it shhould be false otheriwe s
        *
        *
        * */


        if (currentMessage.sendStatus == MessageStatus.FAILED) {
            return true
        } else if (isRead) {
            //check if
            return true
        } else if ()
    }

    // Rest of List Implementation
    override val size = messages.size
    override fun contains(element: MessageViewModel) = messages.contains(element.message)

    override fun containsAll(elements: Collection<MessageViewModel>) =
        messages.containsAll(elements.map { it.message })

    override fun indexOf(element: MessageViewModel) = messages.findMessageIdxById(element.message.id ?: "")

    override fun isEmpty() = messages.isEmpty()

    override fun iterator(): Iterator<MessageViewModel> {
        // Not Implemented
        TODO("Not Implemented, probably not needed")
    }

    override fun lastIndexOf(element: MessageViewModel) = messages.lastIndexOf(element.message)

    override fun listIterator(): ListIterator<MessageViewModel> {
        // Not Implemented
        TODO("Not Implemented, probably not needed")
    }

    override fun listIterator(index: Int): ListIterator<MessageViewModel> {
        // Not Implemented
        TODO("Not Implemented, probably not needed")
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<MessageViewModel> {
        // Not Implemented
        TODO("Not Implemented, probably not needed")
    }
}
