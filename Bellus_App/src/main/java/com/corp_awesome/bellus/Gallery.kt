package com.corp_awesome.bellus

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 *
 */
class Gallery : AppCompatActivity() {

    lateinit var frame : FrameLayout
    var init = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.gallery_activity)


    }

    override fun onStart() {
        super.onStart()

        if (! init) {
            frame = findViewById<FrameLayout>(R.id.parent_frame)
            init = true
        }

    }

    fun showPreview(v : View){
        val previewParent : CoordinatorLayout= LayoutInflater.from(this).inflate(R.layout.gallery_preview, null) as CoordinatorLayout
        val img  = previewParent.findViewById<ImageView>(R.id.preview_iv)

        Glide.with(this).load(R.drawable.message_icon).into(img)

        frame.addView(previewParent)
        frame.getChildAt(0).isEnabled = false
    }

    fun exitPreview(v : View){
        frame.removeViewAt(1)
        frame.getChildAt(0).isEnabled = true

    }

}