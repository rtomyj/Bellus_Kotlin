package com.corp_awesome.bellus

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView

/**
 *  Activity class that displays info about_img the services/service provider
 *
 */

class about : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_activity)

        supportActionBar?.title = resources.getString(R.string.about)

        val phoneTV  : TextView= findViewById(R.id.phone_TV)
        val emailTV : TextView = findViewById(R.id.email_TV)

        phoneTV.isLongClickable = true
        emailTV.isLongClickable = true

    }

    fun smsClick(v : View){
        val number = resources.getString(R.string.primary_phone_number)
        val sms = Intent(Intent.ACTION_SENDTO, Uri.fromParts("sms", number , null))

        startActivity(Intent.createChooser(sms, "Inquire email..."))


    }

    fun emailClick(v : View){
        val address = resources.getString(R.string.primary_email_address)
        val email = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", address , null))

        startActivity(Intent.createChooser(email, "Inquire email..."))

    }

}
