package com.corp_awesome.bellus

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class domus : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.domus_activity)
        val bookingIV : ImageView= findViewById(R.id.booking_IV)
        val aboutIV: ImageView = findViewById(R.id.about_IV)
        val resources = resources

        PictureMethods.setScaledBitmap(bookingIV, resources, R.drawable.booking)
        PictureMethods.setScaledBitmap(aboutIV, resources, R.drawable.about)

    }

}
