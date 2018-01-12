package com.corp_awesome.bellus

import android.app.Activity
import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

/**
 * Created by MacMini on 1/11/18.
 */
class Popups {

    companion object {
        fun policyAlertDialog(context : Context, welcome: Boolean = false)  : AlertDialog.Builder{
            val policyDialog = AlertDialog.Builder(context)
            val layout : LinearLayout = (context as Activity).layoutInflater.inflate(R.layout.policies_info_layout, null) as LinearLayout

            if (! welcome){
                val welcomeTV = layout.findViewById<TextView>(R.id.welcomeTV)
                layout.removeView(welcomeTV)

            }

            policyDialog.setView(layout)
            policyDialog.setPositiveButton("Understood", null)
            policyDialog.setCancelable(false)


            return policyDialog

        }

        fun roseAlertDialog(context : Context)  : AlertDialog.Builder{
            val policyDialog = AlertDialog.Builder(context)
            val layout = (context as Activity).layoutInflater.inflate(R.layout.rose_info_layout, null)

            //val inflater : LayoutInflater = getSystemService(this.layoutin)

            policyDialog.setView(layout)
            policyDialog.setPositiveButton("Dismiss", null)
            policyDialog.setCancelable(false)

            return policyDialog

        }
    }
}