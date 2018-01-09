package com.corp_awesome.bellus

import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.io.File
import android.view.animation.AnimationUtils
import android.view.animation.Animation




/**
 *
 */
class Gallery : AppCompatActivity() {

    lateinit var frame : FrameLayout
    var init = false
    var previewIsShown = false

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

        var file = File(filesDir, "")
        var list = file.listFiles()

        for (item : File in list)
            System.out.println(item.name)
           // System.out.println(file.absolutePath + " " + file.exists())

    }

    fun showPreview(v : View){
        val previewParent : CoordinatorLayout= LayoutInflater.from(this).inflate(R.layout.gallery_preview, null) as CoordinatorLayout
        val img  = previewParent.findViewById<ImageView>(R.id.preview_iv)
        val fab  = previewParent.findViewById<ImageView>(R.id.fab)

        previewParent.visibility =View.INVISIBLE

        Glide.with(this).load(R.drawable.gallery_p_4).into(img)

        frame.addView(previewParent)
        frame.getChildAt(0).isEnabled = false


        previewParent.visibility =View.VISIBLE

        val animation1 = AnimationUtils.loadAnimation(applicationContext,
                R.anim.pop_up)
        img.startAnimation(animation1)
        fab.startAnimation(animation1)

        previewIsShown = true

    }

    fun exitPreview(v : View){
        exitPreview()

    }

    fun exitPreview(){
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