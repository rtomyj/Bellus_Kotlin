package com.corp_awesome.bellus

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 *  Activity class that displays info about the services/service provider
 *
 */

class about : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_activity)

        supportActionBar?.title = resources.getString(R.string.about)


    }
}
