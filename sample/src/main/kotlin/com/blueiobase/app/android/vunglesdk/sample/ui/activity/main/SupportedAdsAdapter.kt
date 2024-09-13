package com.blueiobase.app.android.vunglesdk.sample.ui.activity.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blueiobase.app.android.vunglesdk.sample.R
import com.blueiobase.app.android.vunglesdk.sample.ui.activity.ads.BannerAdsActivity
import com.blueiobase.app.android.vunglesdk.sample.ui.activity.ads.InterstitialAdsActivity
import com.blueiobase.app.android.vunglesdk.sample.ui.activity.ads.NativeAdsActivity
import com.blueiobase.app.android.vunglesdk.sample.ui.activity.ads.RewardedAdsActivity
import com.blueiobase.app.android.vunglesdk.sample.ui.base.AbstractBaseActivity

/**
 * Adapter for displaying the list of supported ad types.
 */
class SupportedAdsAdapter(private val activity: AbstractBaseActivity) :
    RecyclerView.Adapter<AdsViewHolder>() {

    // Retrieve the string array from resources
    private val adList: Array<String> by lazy {
        arrayOf(
            activity.getString(R.string.interstitial_ads),
            activity.getString(R.string.rewarded_ads),
            activity.getString(R.string.banner_ads),
            activity.getString(R.string.native_ads)
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_ad_list, parent, false)
        return AdsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return adList.size
    }

    override fun onBindViewHolder(holder: AdsViewHolder, position: Int) {
        val adName = adList[position]
        with(holder) {
            adNameTextView.text = adName
            adNameTextView.setBackgroundResource(R.drawable.bg_ripple_default)
            if(adList.lastIndex == position)
                divider.visibility = View.GONE

            itemView.setOnClickListener {
                when(adName) {
                    activity.getString(R.string.interstitial_ads) -> activity.navigateTo(InterstitialAdsActivity::class.java)
                    activity.getString(R.string.rewarded_ads) -> activity.navigateTo(RewardedAdsActivity::class.java)
                    activity.getString(R.string.banner_ads) -> activity.navigateTo(BannerAdsActivity::class.java)
                    activity.getString(R.string.native_ads) -> activity.navigateTo(NativeAdsActivity::class.java)
                }
            }

        }

    }
}