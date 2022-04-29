// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.android.communication.ui.telemetry

internal interface TelemetryCallback {
    fun callback(name: String?, value: Double)
}