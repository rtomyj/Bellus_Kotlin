package com.corp_awesome.bellus

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class Domus : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.domus_activity)

        supportActionBar?.hide()

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        if ( ! preferences.getBoolean(Preferences.SHOWED_POLICIES_ON_FIRST_LAUNCH.toString(), false)){
            val policyDialog = Popups.policyAlertDialog(this, true)

            policyDialog.create().show()
            preferences.edit().putBoolean(Preferences.SHOWED_POLICIES_ON_FIRST_LAUNCH.toString(), true).apply()

        }

    }

    override fun onStart() {
        super.onStart()

        setImages()

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

    fun bookAppointment(v : View){

        val bookingIntent = Intent(this, Booking::class.java)
        startActivity(bookingIntent)

    }



    fun about(v : View){

        val aboutIntent = Intent(this, About::class.java)
        startActivity(aboutIntent);

    }

    fun gallery (v: View){
        val galleryIntent = Intent(this, Gallery::class.java)
        startActivity(galleryIntent)

    }


}
