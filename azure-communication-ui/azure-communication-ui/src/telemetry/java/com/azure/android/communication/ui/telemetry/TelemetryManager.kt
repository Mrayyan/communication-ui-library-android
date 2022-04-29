// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.android.communication.ui.telemetry


public class TelemetryManager private constructor() {
    private var telemetryCallback: TelemetryCallback? = null
    fun track(name: String, value: Double) {
        telemetryCallback?.callback(name, value)
    }
    fun setCallback(telemetryCallback: TelemetryCallback) {
        this.telemetryCallback = telemetryCallback
    }

    companion object {
        private var instance: TelemetryManager? = null
        @JvmStatic
        fun instance(): TelemetryManager {
            if (instance == null) {
                instance = TelemetryManager()
            }
            return instance!!
        }
    }
}