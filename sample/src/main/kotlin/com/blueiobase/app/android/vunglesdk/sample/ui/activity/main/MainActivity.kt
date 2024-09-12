package com.blueiobase.app.android.vunglesdk.sample.ui.activity.main

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.blueiobase.app.android.vunglesdk.sample.ui.base.AbstractBaseActivity

/**
 * Entry activity for the application.
 * @author IO DevBlue
 * @since 1.0.0
 */
class MainActivity: AbstractBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen() //Install splash screen that supports Android 12 and above.
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
    }

}