package com.blueiobase.app.android.vunglesdk.sample.util

import android.os.Build

/** Utility class to handle several computational needs. */
internal object Util {



    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC FUNCTIONS
    ///////////////////////////////////////////////////////////////////////////
    /**
     * Validates if the device is an Android 33 device or later.
     * @return `true` if it the device runs on Android 33 or later, `false` if otherwise.
     */
    fun isAndroid33() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU

    /**
     * Validates if the device is an Android 34 device or later.
     * @return `true` if it the device runs on Android 34 or later, `false` if otherwise.
     */
    fun isAndroid34() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE

    /**
     * Validates if the device is an Android 35 device or later.
     * @return `true` if it the device runs on Android 35 or later, `false` if otherwise.
     */
    fun isAndroid35() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM

}