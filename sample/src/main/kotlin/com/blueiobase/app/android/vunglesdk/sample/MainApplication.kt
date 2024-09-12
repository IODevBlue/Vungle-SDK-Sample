package com.blueiobase.app.android.vunglesdk.sample

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import com.vungle.ads.InitializationListener
import com.vungle.ads.VungleAds
import com.vungle.ads.VungleError

/** The [Application] class for the Vungle SDK Sample. */
class MainApplication: Application() {

    companion object {
        /** The tag used for logging. */
        const val TAG = "Vungle-SDK-Sample"
    }


    ///////////////////////////////////////////////////////////////////////////
    // VARIABLES
    ///////////////////////////////////////////////////////////////////////////
//    /** The global [SharedPreferences] instance. */
//    private val sharedPreferences: SharedPreferences by lazy { getSharedPreferences("Vungle-SDK-Sample", MODE_PRIVATE) }

    ///////////////////////////////////////////////////////////////////////////
    // OVERRIDE FUNCTIONS
    ///////////////////////////////////////////////////////////////////////////
    override fun onCreate() {
        super.onCreate()
        VungleAds.init(this, VungleAdConfig.APP_ID, object : InitializationListener {
            override fun onSuccess() {
                Log.d(TAG, "Vungle SDK init onSuccess()")
            }
            override fun onError(vungleError: VungleError) {
                Log.d(TAG, "onError(): ${vungleError.localizedMessage}")
            }
        })
    }

}