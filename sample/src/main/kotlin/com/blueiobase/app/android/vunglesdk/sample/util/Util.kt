package com.blueiobase.app.android.vunglesdk.sample.util

import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

/**
 * Utility class to handle several computational needs in the app layer.
 * @author IO DevBlue.
 * @since 1.0.0
 */
internal object Util {



    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC FUNCTIONS
    ///////////////////////////////////////////////////////////////////////////
//    /**
//     * Validates if the device is an Android 29 device or later.
//     * @return `true` if it the device runs on Android 28 or later, `false` if otherwise.
//     */
//    fun isAndroid28() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
//
//    /**
//     * Validates if the device is an Android 29 device or later.
//     * @return `true` if it the device runs on Android 29 or later, `false` if otherwise.
//     */
//    fun isAndroid29() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
//
//    /**
//     * Validates if the device is an Android 30 device or later.
//     * @return `true` if it the device runs on Android 30 or later, `false` if otherwise.
//     */
//    fun isAndroid30() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU

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

//    /**
//     * Retrieves a [Parcelable] instance from the provided bundle.
//     * @param key The String used to retrieve the parcel.
//     * @param savedInstanceState The [Bundle] where the parcel would be.
//     * @param clazz The type of the parcel. Needed for Android 34+
//     */
//    @Suppress("deprecation")
//    fun <T> retrieveParcelable(key: String, savedInstanceState: Bundle, clazz: Class<T>): T? { //Working
//        with(savedInstanceState) {
//            return (if(isAndroid34())
//                getParcelable(key, clazz)
//            else
//                getParcelable(key))
//        }
//    }
//
//    /**
//     * Retrieves a [Serializable] instance from the provided bundle.
//     * @param key The String used to retrieve the serialized value.
//     * @param savedInstanceState The [Bundle] where the serialized value would be.
//     * @param clazz The type of the serialized value. Needed for Android 34+
//     */ @Suppress("deprecation")
//    fun <T: Serializable> retrieveSerializable(key: String, savedInstanceState: Bundle, clazz: Class<T>): Serializable? { //Working
//        with(savedInstanceState) {
//            return if(isAndroid34())
//                getSerializable(key, clazz)
//            else
//                getSerializable(key)
//        }
//    }
//
//    /**
//     * Retrieves an array of Parcelable instances from the provided bundle.
//     * @param key The String used to retrieve the parcel.
//     * @param savedInstanceState The [Bundle] where the parcel would be.
//     * @param clazz The type of the parcel. Needed for Android 34+
//     */
//    @Suppress("deprecation", "UNCHECKED_CAST")
//    fun <T: Parcelable> retrieveParcelableArray(key: String, savedInstanceState: Bundle, clazz: Class<T>): Array<T>? { //Working
//        with(savedInstanceState) {
//            return (if(isAndroid34())
//                getParcelableArray(key, clazz)
//            else
//                getParcelableArray(key) as Array<T>?)
//        }
//    }
//
//    /**
//     * Converts Density-Independent Pixels to Pixels
//     * @param dp Density-Independent Pixels in [Integer]
//     * @return Pixels in [Integer]
//     */
//    fun dpToPx(dp: Int) = (dp * Resources.getSystem().displayMetrics.density).toInt() //Working
}