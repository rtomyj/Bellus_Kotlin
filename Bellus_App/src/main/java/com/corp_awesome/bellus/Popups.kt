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
            val layout : LinearLayout = (context as Activity).layoutInflater.inflate(R.layout.policies_info_layout, null) as LinearLayout
            if (! welcome){
                val welcomeTV = layout.findViewById<TextView>(R.id.welcomeTV)
                layout.removeView(welcomeTV)

            }

            val policyDialog = customViewAlertDialog(context, layout)

            policyDialog.setPositiveButton(context.resources.getString(R.string.understood), null)

            return policyDialog

        }

        fun roseAlertDialog(context : Context)  : AlertDialog.Builder{
            val layout = (context as Activity).layoutInflater.inflate(R.layout.rose_info_layout, null)
            val roseDialog = customViewAlertDialog(context, layout)

            roseDialog.setPositiveButton(context.resources.getString(R.string.dismiss), null)

            return roseDialog

        }

        fun customViewAlertDialog(context : Context, view : View) : AlertDialog.Builder{
            val dialog = AlertDialog.Builder(context)

            dialog.setView(view)
            dialog.setCancelable(false)
            return dialog
        }
    }
}