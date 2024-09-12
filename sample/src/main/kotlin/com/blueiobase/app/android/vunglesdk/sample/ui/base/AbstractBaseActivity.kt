package com.blueiobase.app.android.vunglesdk.sample.ui.base

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.window.OnBackInvokedCallback
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.IdRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityOptionsCompat
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.blueiobase.app.android.vunglesdk.sample.R
import com.blueiobase.app.android.vunglesdk.sample.util.Util

/**
 * Base class for all [Activity] implementations.
 * @author IO DevBlue
 * @since 1.0.0
 */
abstract class AbstractBaseActivity: AppCompatActivity() {



    ///////////////////////////////////////////////////////////////////////////
    // VARIABLES
    ///////////////////////////////////////////////////////////////////////////
//    /** Reference to the [MainApplication] accessible to every subclass of [AbstractBaseActivity]. */
//    val app by lazy { application as MainApplication }

    /** The insets to be applied to this activity. */
    private var windowInsets: Insets? = null

    /** Back-pressed handler for Android 33 and above. */
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private lateinit var onBackInvokedCallback: OnBackInvokedCallback

    /** Backward compatibility back-pressed handler for Android versions less than 33. */
    private val onBackPressedCallback by lazy {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() { onSupportNavigateUp() }
        }
    }




    ///////////////////////////////////////////////////////////////////////////
    // OVERRIDE FUNCTIONS
    ///////////////////////////////////////////////////////////////////////////
    @Suppress("Deprecation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyActivityInsets(findViewById(android.R.id.content))

        if (Util.isAndroid33()) {
            onBackInvokedCallback = OnBackInvokedCallback { onSupportNavigateUp() }
            onBackInvokedDispatcher.registerOnBackInvokedCallback(OnBackInvokedDispatcher.PRIORITY_DEFAULT, onBackInvokedCallback)
        } else {
            onBackPressedDispatcher.addCallback(onBackPressedCallback)
            overridePendingTransition(0, 0)
        }

        if(Util.isAndroid34()) {
            overrideActivityTransition(OVERRIDE_TRANSITION_OPEN, 0, 0)
        }
    }

    override fun onPause() {
        super.onPause()
       if (Util.isAndroid33()) {
            onBackInvokedDispatcher.unregisterOnBackInvokedCallback(onBackInvokedCallback)
        } else {
            onBackPressedCallback.apply {
                isEnabled = false
                remove()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (Util.isAndroid33()) {
            onBackInvokedDispatcher.registerOnBackInvokedCallback(OnBackInvokedDispatcher.PRIORITY_DEFAULT, onBackInvokedCallback)
        } else {
            onBackPressedDispatcher.addCallback(onBackPressedCallback.apply { isEnabled = true })
        }
    }

    override fun onNavigateUp(): Boolean {
        finish()
        return super.onNavigateUp()
    }




    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC FUNCTIONS
    ///////////////////////////////////////////////////////////////////////////
    /**
     * Configures a [Toolbar] and configures it as the support action bar for the [AbstractBaseActivity].
     * @param toolbarID The [Int] id for the [Toolbar]
     * @return The configured [Toolbar].
     */
    fun configureToolbar(@IdRes toolbarID: Int): Toolbar {
        val toolbar = this.findViewById<Toolbar>(toolbarID)
        this.let {
            toolbar.apply {
                setNavigationOnClickListener { _ ->
                    it.finish()
                }
            }
            it.setSupportActionBar(toolbar)
            it.supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowTitleEnabled(false)
            }
        }
        return toolbar
    }

    /**
     * This is used to navigate between [Activity] classes.
     * @param clazz The [Activity] class to Navigate to.
     * @param activityResultLauncher The launcher to start the destination activity awaiting a result.
     * @param activityOptionsCompat Additional options on how the activity should be started.
     *                              **NOTE:** This is only applied when the [activityResultLauncher] is provided.
     * @param intentAction The general action to be performed in the incoming [Activity].
     * @param intent The DSL method which accepts a [Bundle] for extra information to be retrieved in the destination [Activity].
     * @param options Additional options on how the activity should be started.
     * @param flags Special flags controlling how this intent is handled
     */
    fun navigateTo(
        clazz: Class<out Activity>,
        activityResultLauncher: ActivityResultLauncher<Intent>? = null,
        activityOptionsCompat: ActivityOptionsCompat? = null,
        intentAction: String? = null,
        intent: () -> Bundle = { Bundle.EMPTY },
        options: (() -> Bundle)? = null,
        flags: Int = 0
    ) {
        val src = Intent(this, clazz)
        src.putExtras(intent()).action = intentAction
        src.addFlags(flags)
        if(activityResultLauncher != null) {
            activityResultLauncher.launch(src, activityOptionsCompat)
        } else {
            this.startActivity(src, options?.let {it()})
        }
    }




    ///////////////////////////////////////////////////////////////////////////
    // PRIVATE FUNCTIONS
    ///////////////////////////////////////////////////////////////////////////
    /** Apply the system insets to the [Activity] on Android 35 and above for edge-to-edge support. */
    private fun applyActivityInsets(rootView: View) { //Working
        if(Util.isAndroid35()) {
            //Adding and removing insets for Vanilla Ice cream based on fullscreen preference.
            ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { _, wi ->
                val insets = wi.getInsets(WindowInsetsCompat.Type.systemBars())
                windowInsets = insets
                rootView.setBackgroundColor(getColor(R.color.vungle_green))
                rootView.setPadding(insets.left, insets.top, insets.right, insets.bottom)
                WindowInsetsCompat.CONSUMED
            }
        }

    }

}