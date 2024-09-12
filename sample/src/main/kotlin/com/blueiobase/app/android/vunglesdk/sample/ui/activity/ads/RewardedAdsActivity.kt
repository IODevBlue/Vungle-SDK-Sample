package com.blueiobase.app.android.vunglesdk.sample.ui.activity.ads

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import com.blueiobase.app.android.vunglesdk.sample.R
import com.blueiobase.app.android.vunglesdk.sample.VungleAdConfig
import com.blueiobase.app.android.vunglesdk.sample.ui.base.AbstractBaseActivity
import com.vungle.ads.AdConfig
import com.vungle.ads.BaseAd
import com.vungle.ads.BaseAdListener
import com.vungle.ads.RewardedAd
import com.vungle.ads.VungleError

/** Activity for displaying rewarded ads. */
class RewardedAdsActivity: AbstractBaseActivity(), BaseAdListener {

    /** A rewarded ad. */
    private var rewardedAd: RewardedAd? = null

    /** Button to trigger the loading of a rewarded ad. */
    private val loadAdButton: Button by lazy { findViewById(R.id.rewarded_load_ad_btn) }
    /**  Progress bar displayed while the rewarded ad is loading. */
    private val progressBar: ProgressBar by lazy { findViewById(R.id.rewarded_ads_pb) }
    /** Indicates whether an ad is currently loading. */
    private var isAdLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rewarded_ads)
        configureToolbar(R.id.rewarded_ads_toolbar)
        savedInstanceState?.let {
            isAdLoading = it.getBoolean("isAdLoading")
            if(isAdLoading) {
                loadAdButton.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            }
        }
        loadAdButton.setOnClickListener {
            isAdLoading = true
            loadAdButton.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            rewardedAd = RewardedAd(this, VungleAdConfig.REWARD_PLACEMENT, AdConfig().apply {
                adOrientation = AdConfig.AUTO_ROTATE
            }).apply {
                adListener = this@RewardedAdsActivity
                setUserId("VungleTestUser")
                load()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        with(outState) {
            putBoolean("isAdLoading", isAdLoading)
        }
    }

    override fun onAdClicked(baseAd: BaseAd) {
        Log.d("RewardedAdsActivity", "onAdClicked: ${baseAd.eventId}")
        isAdLoading = false
        loadAdButton.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        Toast.makeText(this, "Ad clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onAdEnd(baseAd: BaseAd) {
        Log.d("RewardedAdsActivity", "onAdEnd: ${baseAd.eventId}")
        isAdLoading = false
        loadAdButton.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        Toast.makeText(this, "Ad ended", Toast.LENGTH_SHORT).show()
    }

    override fun onAdFailedToLoad(baseAd: BaseAd, adError: VungleError) {
        Log.e("RewardedAdsActivity", "onAdFailedToLoad: ${baseAd.eventId} || Error: ${adError.localizedMessage}")
        isAdLoading = false
        loadAdButton.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        Toast.makeText(this, "Ad failed to load", Toast.LENGTH_SHORT).show()
    }

    override fun onAdFailedToPlay(baseAd: BaseAd, adError: VungleError) {
        Log.e("RewardedAdsActivity", "onAdFailedToPlay: ${baseAd.eventId} || Error: ${adError.localizedMessage}")
        isAdLoading = false
        loadAdButton.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        Toast.makeText(this, "Ad failed to play", Toast.LENGTH_SHORT).show()
    }

    override fun onAdImpression(baseAd: BaseAd) {
        Log.d("RewardedAdsActivity", "onAdImpression: ${baseAd.eventId}")
        isAdLoading = false
        loadAdButton.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        Toast.makeText(this, "Ad impression", Toast.LENGTH_SHORT).show()
    }

    override fun onAdLeftApplication(baseAd: BaseAd) {
        Log.d("RewardedAdsActivity", "onAdLeftApplication: ${baseAd.eventId}")
        isAdLoading = false
        loadAdButton.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        Toast.makeText(this, "Ad left application", Toast.LENGTH_SHORT).show()
    }

    override fun onAdLoaded(baseAd: BaseAd) {
        Log.d("RewardedAdsActivity", "onAdLoaded: ${baseAd.eventId}")
        isAdLoading = false
        loadAdButton.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        Toast.makeText(this, "Ad loaded", Toast.LENGTH_SHORT).show()
        rewardedAd?.let {
            if(it.canPlayAd()) {
                it.play(this)
            }
        }
    }

    override fun onAdStart(baseAd: BaseAd) {
        Log.d("RewardedAdsActivity", "onAdStart: ${baseAd.eventId}")
        isAdLoading = false
        loadAdButton.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        Toast.makeText(this, "Ad started", Toast.LENGTH_SHORT).show()
    }
}