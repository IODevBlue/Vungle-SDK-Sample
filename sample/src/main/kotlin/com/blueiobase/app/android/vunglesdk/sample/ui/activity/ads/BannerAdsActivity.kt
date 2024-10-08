package com.blueiobase.app.android.vunglesdk.sample.ui.activity.ads

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.blueiobase.app.android.vunglesdk.sample.R
import com.blueiobase.app.android.vunglesdk.sample.VungleAdConfig
import com.blueiobase.app.android.vunglesdk.sample.ui.base.AbstractBaseActivity
import com.vungle.ads.BannerAdListener
import com.vungle.ads.BaseAd
import com.vungle.ads.VungleAdSize
import com.vungle.ads.VungleBannerView
import com.vungle.ads.VungleError

/** Activity for displaying banner ads. */
class BannerAdsActivity: AbstractBaseActivity(), BannerAdListener {

    /** An banner ad. */
    private var bannerAd: VungleBannerView? = null
    /** Indicates whether an ad is currently loading. */
    private var isAdLoading = false

    /** Button to trigger the loading of a banner ad. */
    private val loadAdButton: Button by lazy { findViewById(R.id.banner_load_ad_btn) }
    /** Button to destroy the banner ad. */
    private val destroyAdButton: Button by lazy { findViewById(R.id.banner_destroy_ad_btn) }
    /**  Progress bar displayed while the banner ad is loading. */
    private val progressBar: ProgressBar by lazy { findViewById(R.id.banner_ads_pb) }
    /** Content root hosting all user interface content. */
    private val contentRoot: RelativeLayout by lazy { findViewById(R.id.banner_content_root) }
    /** TextView to display error messages. */
    private val errorTextView: TextView by lazy { findViewById(R.id.banner_error_text_view) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner_ads)
        configureToolbar(R.id.banner_ads_toolbar)
        savedInstanceState?.let {
            isAdLoading = it.getBoolean("isAdLoading")
            if(isAdLoading) {
                loadAdButton.visibility = View.GONE
                destroyAdButton.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            }
            errorTextView.text= savedInstanceState.getString("errorText", "")
            errorTextView.visibility = savedInstanceState.getInt("errorVisibility", View.GONE)
        }
        loadAdButton.setOnClickListener {
            isAdLoading = true
            loadAdButton.visibility = View.GONE
            destroyAdButton.visibility = View.GONE
            errorTextView.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            bannerAd = VungleBannerView(this, VungleAdConfig.BANNER_ID_2, VungleAdSize.BANNER).apply {
                adListener = this@BannerAdsActivity
                load()
            }
            val params = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                addRule(RelativeLayout.CENTER_IN_PARENT)
            }
            contentRoot.addView(bannerAd, params)
        }

        destroyAdButton.setOnClickListener {
            bannerAd?.finishAd()
            bannerAd?.adListener = null
        }
    }

    override fun onResume() {
        super.onResume()
        if(bannerAd != null)
            destroyAdButton.visibility = View.VISIBLE
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
        Log.d("BannerAdsActivity", "onAdClicked: ${baseAd.eventId}")
        isAdLoading = false
        loadAdButton.visibility = View.VISIBLE
        destroyAdButton.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        errorTextView.visibility = View.GONE
        Toast.makeText(this, "Ad clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onAdEnd(baseAd: BaseAd) {
        Log.d("BannerAdsActivity", "onAdEnd: ${baseAd.eventId}")
        isAdLoading = false
        loadAdButton.visibility = View.VISIBLE
        destroyAdButton.visibility = View.GONE
        progressBar.visibility = View.GONE
        errorTextView.visibility = View.GONE
        Toast.makeText(this, "Ad ended", Toast.LENGTH_SHORT).show()
    }

    override fun onAdFailedToLoad(baseAd: BaseAd, adError: VungleError) {
        val error = "Error: ${adError.localizedMessage}"
        Log.e("BannerAdsActivity", "onAdFailedToLoad: ${baseAd.eventId} || $error")
        isAdLoading = false
        loadAdButton.visibility = View.VISIBLE
        destroyAdButton.visibility = View.GONE
        progressBar.visibility = View.GONE
        with(errorTextView) {
            visibility = View.VISIBLE
            text = error
        }
        Toast.makeText(this, "Ad failed to load", Toast.LENGTH_SHORT).show()
    }

    override fun onAdFailedToPlay(baseAd: BaseAd, adError: VungleError) {
        val error = "Error: ${adError.localizedMessage}"
        Log.e("BannerAdsActivity", "onAdFailedToPlay: ${baseAd.eventId} || $error")
        isAdLoading = false
        loadAdButton.visibility = View.VISIBLE
        destroyAdButton.visibility = View.GONE
        progressBar.visibility = View.GONE
        with(errorTextView) {
            visibility = View.VISIBLE
            text = error
        }
        Toast.makeText(this, "Ad failed to play", Toast.LENGTH_SHORT).show()
    }

    override fun onAdImpression(baseAd: BaseAd) {
        Log.d("BannerAdsActivity", "onAdImpression: ${baseAd.eventId}")
        isAdLoading = false
        loadAdButton.visibility = View.VISIBLE
        destroyAdButton.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        errorTextView.visibility = View.GONE
        Toast.makeText(this, "Ad impression", Toast.LENGTH_SHORT).show()
    }

    override fun onAdLeftApplication(baseAd: BaseAd) {
        Log.d("BannerAdsActivity", "onAdLeftApplication: ${baseAd.eventId}")
        isAdLoading = false
        loadAdButton.visibility = View.VISIBLE
        destroyAdButton.visibility = View.GONE
        progressBar.visibility = View.GONE
        errorTextView.visibility = View.GONE
        Toast.makeText(this, "Ad left application", Toast.LENGTH_SHORT).show()
    }

    override fun onAdLoaded(baseAd: BaseAd) {
        Log.d("BannerAdsActivity", "onAdLoaded: ${baseAd.eventId}")
        isAdLoading = false
        loadAdButton.visibility = View.VISIBLE
        destroyAdButton.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        errorTextView.visibility = View.GONE
        Toast.makeText(this, "Ad loaded", Toast.LENGTH_SHORT).show()
    }

    override fun onAdStart(baseAd: BaseAd) {
        Log.d("BannerAdsActivity", "onAdStart: ${baseAd.eventId}")
        isAdLoading = false
        loadAdButton.visibility = View.VISIBLE
        destroyAdButton.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        errorTextView.visibility = View.GONE
        Toast.makeText(this, "Ad started", Toast.LENGTH_SHORT).show()
    }
}