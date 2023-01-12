package com.example.galleryapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.galleryapplication.R


class GridAdapter(private val context: Context, private val images: ArrayList<String>) :
    BaseAdapter() {

    override fun getCount(): Int {
        return images.size
    }

    override fun getItem(position: Int): Any {
        return images[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            val layoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.grid_gallery, parent, false)
        }
        val card = view!!.findViewById<ImageView>(R.id.image)
        Glide.with(context)
            .load(images[position])
            .into(card)
        return view
    }
}