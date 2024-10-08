package com.blueiobase.app.android.vunglesdk.sample.ui.activity.ads

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.blueiobase.app.android.vunglesdk.sample.R
import com.blueiobase.app.android.vunglesdk.sample.VungleAdConfig
import com.blueiobase.app.android.vunglesdk.sample.ui.base.AbstractBaseActivity
import com.vungle.ads.AdConfig
import com.vungle.ads.BaseAd
import com.vungle.ads.BaseAdListener
import com.vungle.ads.InterstitialAd
import com.vungle.ads.VungleError

/** Activity for displaying interstitial ads. */
class InterstitialAdsActivity: AbstractBaseActivity(), BaseAdListener {

    /** An interstitial ad. */
    private var interstitialAd: InterstitialAd? = null
    /** Indicates whether an ad is currently loading. */
    private var isAdLoading = false

    /** Button to trigger the loading of an interstitial ad. */
    private val loadAdButton: Button by lazy { findViewById(R.id.interstitial_load_ad_btn) }
    /**  Progress bar displayed while the interstitial ad is loading. */
    private val progressBar: ProgressBar by lazy { findViewById(R.id.interstitial_ads_pb) }
    /** TextView to display error messages. */
    private val errorTextView: TextView by lazy { findViewById(R.id.interstitial_error_text_view) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interstitial_ads)
        configureToolbar(R.id.interstitial_ads_toolbar)
        savedInstanceState?.let {
            isAdLoading = it.getBoolean("isAdLoading")
            if(isAdLoading) {
                loadAdButton.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            }
            errorTextView.text= savedInstanceState.getString("errorText", "")
            errorTextView.visibility = savedInstanceState.getInt("errorVisibility", View.GONE)
        }
        loadAdButton.setOnClickListener {
            isAdLoading = true
            loadAdButton.visibility = View.GONE
            errorTextView.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            interstitialAd = InterstitialAd(this, VungleAdConfig.INTERSTITIAL_ID_2, AdConfig().apply {
                adOrientation = AdConfig.AUTO_ROTATE
            }).apply {
                adListener = this@InterstitialAdsActivity
                load()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        with(outState) {
            putBoolean("isAdLoading", isAdLoading)
            putString("errorText", errorTextView.text.toString())
            putInt("errorVisibility", errorTextView.visibility)
        }
    }

    override fun onAdClicked(baseAd: BaseAd) {
        Log.d("InterstitialAdsActivity", "onAdClicked: ${baseAd.eventId}")
        isAdLoading = false
        loadAdButton.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        errorTextView.visibility = View.GONE
        Toast.makeText(this, "Ad clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onAdEnd(baseAd: BaseAd) {
        Log.d("InterstitialAdsActivity", "onAdEnd: ${baseAd.eventId}")
        isAdLoading = false
        loadAdButton.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        errorTextView.visibility = View.GONE
        Toast.makeText(this, "Ad ended", Toast.LENGTH_SHORT).show()
    }

    override fun onAdFailedToLoad(baseAd: BaseAd, adError: VungleError) {
        val error = "Error: ${adError.localizedMessage}"
        Log.e("InterstitialAdsActivity", "onAdFailedToLoad: ${baseAd.eventId} || $error")
        isAdLoading = false
        loadAdButton.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        with(errorTextView) {
            visibility = View.VISIBLE
            text = error
        }
        Toast.makeText(this, "Ad failed to load", Toast.LENGTH_SHORT).show()
    }

    override fun onAdFailedToPlay(baseAd: BaseAd, adError: VungleError) {
        val error = "Error: ${adError.localizedMessage}"
        Log.e("InterstitialAdsActivity", "onAdFailedToPlay: ${baseAd.eventId} || $error")
        isAdLoading = false
        loadAdButton.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        with(errorTextView) {
            visibility = View.VISIBLE
            text = error
        }
        Toast.makeText(this, "Ad failed to play", Toast.LENGTH_SHORT).show()
    }

    override fun onAdImpression(baseAd: BaseAd) {
        Log.d("InterstitialAdsActivity", "onAdImpression: ${baseAd.eventId}")
        isAdLoading = false
        loadAdButton.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        errorTextView.visibility = View.GONE
        Toast.makeText(this, "Ad impression", Toast.LENGTH_SHORT).show()
    }

    override fun onAdLeftApplication(baseAd: BaseAd) {
        Log.d("InterstitialAdsActivity", "onAdLeftApplication: ${baseAd.eventId}")
        isAdLoading = false
        loadAdButton.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        errorTextView.visibility = View.GONE
        Toast.makeText(this, "Ad left application", Toast.LENGTH_SHORT).show()
    }

    override fun onAdLoaded(baseAd: BaseAd) {
        Log.d("InterstitialAdsActivity", "onAdLoaded: ${baseAd.eventId}")
        isAdLoading = false
        loadAdButton.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        errorTextView.visibility = View.GONE
        Toast.makeText(this, "Ad loaded", Toast.LENGTH_SHORT).show()
        interstitialAd?.let {
            if(it.canPlayAd()) {
                it.play(this)
            }
        }
    }

    override fun onAdStart(baseAd: BaseAd) {
        Log.d("InterstitialAdsActivity", "onAdStart: ${baseAd.eventId}")
        isAdLoading = false
        loadAdButton.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        errorTextView.visibility = View.GONE
        Toast.makeText(this, "Ad started", Toast.LENGTH_SHORT).show()
    }
}