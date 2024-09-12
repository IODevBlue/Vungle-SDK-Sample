package com.blueiobase.app.android.vunglesdk.sample.ui.activity.main

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blueiobase.app.android.vunglesdk.sample.R
import com.google.android.material.divider.MaterialDivider

/**  ViewHolder for the ad list items. */
class AdsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    /** TextView displaying the name of the ad type.*/
    val adNameTextView: TextView = itemView.findViewById(R.id.item_ad_list_name_tv)
    /**  Divider below the ad name. */
    val divider: MaterialDivider = itemView.findViewById(R.id.item_ad_list_divider)
}