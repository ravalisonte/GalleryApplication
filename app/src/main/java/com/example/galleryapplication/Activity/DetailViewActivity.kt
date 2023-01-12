package com.example.galleryapplication.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.galleryapplication.Adapter.DetailViewPagerAdapter
import com.example.galleryapplication.Model.GalleryModel
import com.example.galleryapplication.R

class DetailViewActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager
    private lateinit var viewPagerAdapter: DetailViewPagerAdapter
    private var modelList: ArrayList<GalleryModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_view)
        var currentPosition = 0
        val imagesData = intent.extras
        imagesData?.let {
            currentPosition = it.get("currentposition") as Int
            modelList = it.getSerializable("images") as ArrayList<GalleryModel>
        }
        viewPager = findViewById(R.id.idViewPager)
        viewPagerAdapter = DetailViewPagerAdapter(this, modelList)
        viewPager.adapter = viewPagerAdapter
        viewPager.currentItem = currentPosition
    }
}
