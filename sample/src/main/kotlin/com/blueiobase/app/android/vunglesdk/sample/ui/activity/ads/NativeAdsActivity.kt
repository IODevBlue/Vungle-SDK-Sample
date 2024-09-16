package com.blueiobase.app.android.vunglesdk.sample.ui.activity.ads

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.blueiobase.app.android.vunglesdk.sample.R
import com.blueiobase.app.android.vunglesdk.sample.VungleAdConfig
import com.blueiobase.app.android.vunglesdk.sample.ui.base.AbstractBaseActivity
import com.vungle.ads.BaseAd
import com.vungle.ads.NativeAd
import com.vungle.ads.NativeAdListener
import com.vungle.ads.VungleError
import com.vungle.ads.internal.ui.view.MediaView

/** Activity for displaying native ads. */
class NativeAdsActivity: AbstractBaseActivity(), NativeAdListener {

    /** A native ad. */
    private var nativeAd: NativeAd? = null
    /** Indicates whether an ad is currently loading. */
    private var isAdLoading = false

    /** Button to trigger the loading of a native ad. */
    private val loadAdButton: Button by lazy { findViewById(R.id.native_load_ad_btn) }
    /** Button to destroy the native ad. */
    private val destroyAdButton: Button by lazy { findViewById(R.id.native_destroy_ad_btn) }
    /**  Progress bar displayed while the native ad is loading. */
    private val progressBar: ProgressBar by lazy { findViewById(R.id.native_ads_pb) }
    /** Content root hosting all user interface content. */
    private val contentRoot: RelativeLayout by lazy { findViewById(R.id.native_content_root) }
    /** Container for the native ad view.  */
    private val nativeAdContainer: FrameLayout by lazy { findViewById(R.id.native_ad_container) }
    /** TextView to display error messages. */
    private val errorTextView: TextView by lazy { findViewById(R.id.native_error_text_view) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native_ads)
        configureToolbar(R.id.native_ads_toolbar)
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
            progressBar.visibility = View.VISIBLE
            errorTextView.visibility = View.GONE
            nativeAd = NativeAd(this, VungleAdConfig.NATIVE_ID_2).apply {
                adOptionsPosition = NativeAd.TOP_LEFT
                adListener = this@NativeAdsActivity
                load()
            }
        }

        destroyAdButton.setOnClickListener {
            nativeAdContainer.removeAllViews()
            nativeAd?.unregisterView()
            nativeAd?.adListener = null
            nativeAd = null
        }
    }

    override fun onResume() {
        super.onResume()
        if(nativeAd != null)
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
        Toast.makeText(this, "Ad loaded", Toast.LENGTH_SHORT).show()
        errorTextView.visibility = View.GONE
        nativeAd?.let {
            if(!it.canPlayAd()) {
                Toast.makeText(this, "Ad cannot play", Toast.LENGTH_SHORT).show()
            } else {
                with(layoutInflater.inflate(R.layout.item_native_ad, contentRoot, false) as FrameLayout) {
                    nativeAdContainer.removeAllViews()
                    val iconImageView: ImageView = findViewById(R.id.native_ad_icon_image_view)
                    val titleTextView: TextView = findViewById(R.id.native_ad_title_text_view)
                    val ratingTextView: TextView = findViewById(R.id.native_ad_rating_text_view)
                    val bodyTextView: TextView = findViewById(R.id.native_ad_body_text_view)
                    val mediaView: MediaView = findViewById(R.id.native_ad_media_view)
                    val callToActionButton: Button = findViewById(R.id.native_ad_call_to_action_button)
                    val sponsorTextView: TextView = findViewById(R.id.native_ad_sponsor_text_view)

                    val clickableViews = mutableListOf(iconImageView, mediaView, callToActionButton)

                    it.registerViewForInteraction(this, mediaView, iconImageView, clickableViews)
                    titleTextView.text = it.getAdTitle()
                    bodyTextView.text = it.getAdBodyText()
                    ratingTextView.text = String.format("Rating: %s", it.getAdStarRating())
                    sponsorTextView.text = it.getAdSponsoredText()
                    callToActionButton.text = it.getAdCallToActionText()
                    callToActionButton.isVisible = it.hasCallToAction()

                    nativeAdContainer.addView(this)

                }
            }
        }
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