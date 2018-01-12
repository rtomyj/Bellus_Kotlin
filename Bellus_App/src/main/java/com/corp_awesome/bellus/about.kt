package com.corp_awesome.bellus

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 *  Activity class that displays info about_icon the services/service provider
 *
 */

class About : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_activity)

        supportActionBar?.title = resources.getString(R.string.about)
        supportActionBar?.elevation = 0f

        addPictures()

    }


    private fun addPictures(){
        val roseIV : ImageView = findViewById(R.id.roseIV)
        val phoneIV  : ImageView= findViewById(R.id.phoneIV)
        val emailIV : ImageView = findViewById(R.id.emailIV)
        val instaIV : ImageView = findViewById(R.id.instaIV)
        val fb : ImageView = findViewById(R.id.fbIV)

        if (phoneIV.drawable == null && emailIV.drawable == null) {

            val requestOptions = RequestOptions()
            requestOptions.centerCrop()

            phoneIV.isLongClickable = true
            emailIV.isLongClickable = true

            Glide.with(this).applyDefaultRequestOptions(requestOptions).load(R.drawable.about_rose_img).into(roseIV)
            Glide.with(this).load(R.drawable.message_icon).into(phoneIV)
            Glide.with(this).load(R.drawable.email_icon).into(emailIV)
            Glide.with(this).applyDefaultRequestOptions(requestOptions).load(R.drawable.ig_icon).into(instaIV)
            Glide.with(this).load(R.drawable.fb_icon).into(fb)

        }

    }


    fun smsClick(v : View){
        val number = resources.getString(R.string.primary_phone_number)
        val sms = Intent(Intent.ACTION_SENDTO, Uri.fromParts("sms", number , null))

        startActivity(Intent.createChooser(sms, resources.getString(R.string.inquire_sms)))


    }

    fun emailClick(v : View){
        val address = resources.getString(R.string.primary_email_address)
        val email = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", address , null))

        startActivity(Intent.createChooser(email, resources.getString(R.string.inquire_email)))

    }

    fun igClick(v: View){

    }

    fun fbClick(v : View){

    }

    fun showBookingPolicy(v :View){
        Popups.policyAlertDialog(this, false).create().show()


    }

    fun showAboutRose(v :View){
        Popups.roseAlertDialog(this).create().show()
    }

}
