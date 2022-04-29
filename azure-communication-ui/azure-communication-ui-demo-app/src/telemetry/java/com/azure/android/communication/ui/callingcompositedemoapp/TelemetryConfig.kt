// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.android.communication.ui.callingcompositedemoapp

import android.util.Log
import com.azure.android.communication.ui.telemetry.TelemetryCallback
import com.azure.android.communication.ui.telemetry.TelemetryManager

internal class TelemetryConfig {
    companion object {
        internal fun configure(){
            TelemetryManager.instance().setCallback(object : TelemetryCallback {
                override fun callback(name: String?, value: Double) {
                    // replace with AppInsights
                    Log.d("telemetry", "$name $value")
                }
            })
        }
    }
}