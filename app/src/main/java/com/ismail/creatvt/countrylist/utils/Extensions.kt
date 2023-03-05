package com.ismail.creatvt.countrylist.utils

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.core.view.children
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.bumptech.glide.Glide
import com.facebook.shimmer.ShimmerFrameLayout


val View.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(context)

@BindingAdapter(value = ["onRefresh", "progressTime"], requireAll = false)
fun SwipeRefreshLayout.setOnRefresh(
    refreshListener: OnRefreshListener,
    progressTime: Int? = null
) {
    setOnRefreshListener {
        refreshListener.onRefresh()
        if (progressTime != null) {
            Handler(Looper.getMainLooper()).postDelayed({
                isRefreshing = false
            }, progressTime * 1000L)
        }
    }
}

@BindingAdapter("shimmerAnimation")
fun ShimmerFrameLayout.setShimmerAnimation(shouldShimmer: Boolean) {
    if(shouldShimmer) {
        startShimmer()
        visibility = View.VISIBLE
    } else {
        stopShimmer()
        visibility = View.GONE
    }
}

@BindingAdapter(value = ["loadFlag", "flagSize"], requireAll = false)
fun ImageView.loadFlag(countryCode: String, flagSize: Int = 128) {
    Glide.with(this)
        .load("$BASE_URL/countries/flag/$countryCode/$flagSize")
        .into(this)
}