// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.android.communication.ui.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.azure.android.communication.ui.di.DependencyInjectionContainer
import com.azure.android.communication.ui.di.DependencyInjectionContainerImpl
import com.azure.android.communication.ui.presentation.fragment.calling.CallingViewModel
import com.azure.android.communication.ui.presentation.fragment.factories.CallingViewModelFactory
import com.azure.android.communication.ui.presentation.fragment.factories.ParticipantGridCellViewModelFactory
import com.azure.android.communication.ui.presentation.fragment.factories.SetupViewModelFactory
import com.azure.android.communication.ui.presentation.fragment.setup.SetupViewModel
import java.lang.RuntimeException

/**
 * ViewModel for the CallCompositeActivity
 *
 * The instanceID should match the one passed to the Activity via Intent.
 * it needs to be set in activity onCreate() before accessing container or it
 * will throw an exception.
 *
 * Afterwards you can reference container, which holds the services.
 */
internal class DependencyInjectionContainerHolder(application: Application) :
    AndroidViewModel(application) {
    // Instance ID to locate Configuration. -1 is invalid.
    var instanceId: Int = -1

    val container: DependencyInjectionContainer by lazy {
        if (instanceId == -1) {
            throw RuntimeException(
                "Will not be able to locate a Configuration for instanceId: -1. " +
                    "Please ensure that you have set instanceId before retrieving the container."
            )
        }

        // Generate a new instance
        DependencyInjectionContainerImpl(application, instanceId)
    }

    val setupViewModel by lazy {
        SetupViewModel(
            container.appStore,
            SetupViewModelFactory(container.appStore, container.localizationProvider)
        )
    }

    val callingViewModel by lazy {
        CallingViewModel(
            container.appStore,
            CallingViewModelFactory(container.appStore, container.localizationProvider, ParticipantGridCellViewModelFactory())
        )
    }
}
