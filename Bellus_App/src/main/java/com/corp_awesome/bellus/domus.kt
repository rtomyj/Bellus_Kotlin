package com.corp_awesome.bellus

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.Glide


class Domus : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.domus_activity)

        supportActionBar?.elevation = 0f


    }

    override fun onStart() {
        super.onStart()

        setImages()

    }

    override fun onDestroy() {
        super.onDestroy()

     //  recycleImages()
    }

    private fun setImages(){
        val bookingIV : ImageView= findViewById(R.id.booking_IV)
        val galleryIV : ImageView= findViewById(R.id.gallery_IV)
        val aboutIV: ImageView = findViewById(R.id.about_IV)

        if (bookingIV.drawable == null) {

            val requestOptions = RequestOptions()
            requestOptions.fitCenter()

            /*
            val metrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(metrics)
            var size = metrics.widthPixels

            PictureMethods.setScaledBitmap(bookingIV, resources, R.drawable.booking_icon,2 , size)
            PictureMethods.setScaledBitmap(aboutIV, resources, R.drawable.about_icon,2 , size)
*/
            Glide.with(this).applyDefaultRequestOptions(requestOptions).load(R.drawable.booking_icon).into(bookingIV)
            Glide.with(this).applyDefaultRequestOptions(requestOptions).load(R.drawable.gallery_icon).into(galleryIV)
            Glide.with(this).applyDefaultRequestOptions(requestOptions).load(R.drawable.about_icon).into(aboutIV)

        }

    }

    private fun recycleImages(){
        val bookingIV : ImageView= findViewById(R.id.booking_IV)
        val aboutIV: ImageView = findViewById(R.id.about_IV)

        var drawable : BitmapDrawable= bookingIV.drawable as BitmapDrawable
        drawable.bitmap.recycle()
        drawable =  aboutIV.drawable as BitmapDrawable
        drawable.bitmap.recycle()

    }
    fun bookAppointment(v : View){

        val bookingIntent = Intent(this, Booking::class.java)
        startActivity(bookingIntent)

    }



    fun about(v : View){

        val aboutIntent = Intent(this, About::class.java)
        startActivity(aboutIntent);

    }

}
