package com.example.galleryapplication.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import com.example.galleryapplication.Model.GalleryModel
import com.example.galleryapplication.Adapter.GridAdapter
import com.example.galleryapplication.R
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.nio.charset.Charset


class MainActivity : AppCompatActivity() {
    var gridImagesList: ArrayList<String> = ArrayList()
    var modelDataList: ArrayList<GalleryModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imageView = findViewById<GridView>(R.id.gridview)
        getImageDetails()
        val adapter =
            GridAdapter(this, gridImagesList)
        imageView.setAdapter(adapter)
        imageView.setOnItemClickListener { parent, v, position, id ->
            val imageData = Intent(applicationContext, DetailViewActivity::class.java)
            imageData.putExtra("currentposition", position)
            imageData.putExtra(IMAGES, modelDataList)
            startActivity(imageData)
        }
    }

    private fun getImageDetails() {
        try {
            val obj = JSONArray(loadJSONFromAsset())
            for (i in 0 until obj.length()) {
                val userDetail = obj.getJSONObject(i)
                gridImagesList.add(userDetail.getString("url"))
                modelDataList.add(
                    GalleryModel(
                        userDetail.getString("title"),
                        userDetail.getString("explanation"),
                        userDetail.getString("url")
                    )
                )
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun loadJSONFromAsset(): String {
        val json: String?
        try {
            val inputStream = assets.open("nasa_details.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }

    companion object {
        const val IMAGES = "images"
    }
}