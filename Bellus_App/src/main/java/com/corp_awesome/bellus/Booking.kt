package com.corp_awesome.bellus

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by MacMini on 1/6/18.
 */
class Booking : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.booking_activity)

        supportActionBar?.title = resources.getString(R.string.book_appt)
        supportActionBar?.elevation = 0F


    }
}