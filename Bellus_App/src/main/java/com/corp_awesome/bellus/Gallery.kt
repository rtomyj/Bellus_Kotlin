package com.corp_awesome.bellus

import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.bumptech.glide.Glide
import android.view.animation.AnimationUtils


/**
 *
 */
class Gallery : AppCompatActivity() {

    private lateinit var frame : FrameLayout
    private var init = false
    private var previewIsShown = false

    private lateinit var galleryRV : RecyclerView
    private lateinit var galleryAdapter: GalleryAdapter

    private val absoluteAssetPath = "file:///android_asset/"
    private var picList: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.gallery_activity)

        galleryRV = findViewById(R.id.gallery_RV)
        var galleryRVManager = GridLayoutManager(this, 2)
        galleryRV.layoutManager = galleryRVManager



        val list = assets.list("")

        list.filterTo(picList) { it.contains(".png") }

        galleryAdapter = GalleryAdapter(picList, this)
        galleryRV.adapter = galleryAdapter



    }

    override fun onStart() {
        super.onStart()

        if (! init) {
            frame = findViewById<FrameLayout>(R.id.parent_frame)
            init = true
        }

    }

    fun showPreview(position : Int){
        if (!previewIsShown) {
            previewIsShown = true

            val previewParent: CoordinatorLayout = LayoutInflater.from(this).inflate(R.layout.gallery_preview, null) as CoordinatorLayout
            val img = previewParent.findViewById<ImageView>(R.id.preview_iv)
            val fab = previewParent.findViewById<ImageView>(R.id.fab)

            Glide.with(this).load(Uri.parse(absoluteAssetPath + picList[position])).into(img)

            frame.addView(previewParent)
            galleryRV.isEnabled = false

            val animation1 = AnimationUtils.loadAnimation(applicationContext,
                    R.anim.pop_up)
            val animation2 = AnimationUtils.loadAnimation(applicationContext, R.anim.pop_up_delay)

            previewParent.startAnimation(animation1)
            img.startAnimation(animation1)
            fab.startAnimation(animation2)
        }

    }

    fun exitPreview(v : View){
        exitPreview()

    }

    private fun exitPreview(){
        var img = frame.getChildAt(1).findViewById<ImageView>(R.id.preview_iv)

        Glide.with(this).clear(img)

        frame.removeViewAt(1)

        frame.getChildAt(0).isEnabled = true

        previewIsShown = false

    }

    override fun onBackPressed() {
        if (! previewIsShown)
            super.onBackPressed()
        else
            exitPreview()
    }
}