package com.corp_awesome.bellus

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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

    fun bookAppointment(v : View){

    }

    fun about(v : View){

        var intent = Intent(this, about::class.java)
        startActivity(intent);

    }

}
