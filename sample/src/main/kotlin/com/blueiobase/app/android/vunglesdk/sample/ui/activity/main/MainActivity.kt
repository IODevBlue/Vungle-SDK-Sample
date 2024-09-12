package com.blueiobase.app.android.vunglesdk.sample.ui.activity.main

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blueiobase.app.android.vunglesdk.sample.R
import com.blueiobase.app.android.vunglesdk.sample.ui.base.AbstractBaseActivity

/** Entry activity for the sample application. */
class MainActivity: AbstractBaseActivity() {

    /** RecyclerView for displaying the list of supported ads. */
    private val adsRecyclerView by lazy { findViewById<RecyclerView>(R.id.ad_list_rv) }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen() //Install splash screen that supports Android 12 and above.
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        with(adsRecyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = SupportedAdsAdapter(this@MainActivity)
        }

    }

}