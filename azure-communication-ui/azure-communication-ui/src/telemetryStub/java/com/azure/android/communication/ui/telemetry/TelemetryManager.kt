// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.android.communication.ui.telemetry


internal class TelemetryManager private constructor() {
    private val telemetryCallback: TelemetryCallback? = null
    fun track(name: String, value: Double) {}
    fun setCallback(telemetryCallback: TelemetryCallback) {}

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