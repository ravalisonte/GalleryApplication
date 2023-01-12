package com.example.galleryapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.galleryapplication.Model.GalleryModel
import com.example.galleryapplication.R
import java.util.*
import kotlin.collections.ArrayList

class DetailViewPagerAdapter(
    private val context: Context,
    private val galleryModelList: ArrayList<GalleryModel>
) : PagerAdapter() {
    override fun getCount(): Int {
        return galleryModelList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.image_slider, container, false)
        val imageView = view.findViewById<ImageView>(R.id.fullscreen_image)
        val imageTitle = view.findViewById<TextView>(R.id.tvTitle)
        val imageDescription = view.findViewById<TextView>(R.id.tvDescription)

        val modelData = galleryModelList[position]
        modelData.also {
            Glide.with(context)
                .load(it.image)
                .into(imageView)
            imageTitle.text = it.title
            imageDescription.text = it.description
        }
        Objects.requireNonNull(container).addView(view);
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}