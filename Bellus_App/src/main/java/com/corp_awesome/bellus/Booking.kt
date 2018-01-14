package com.corp_awesome.bellus

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


/**
 *
 */

/*
    TODO
    Type of appointments - group appointments, one on one, wedding

 */
class Booking : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.booking_activity)

        supportActionBar?.elevation = 0F


    }

}