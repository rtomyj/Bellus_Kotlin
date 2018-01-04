package com.corp_awesome.bellus

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView

/**
 * Created by MacMini on 1/3/18.
 */


class PictureMethods{

    companion object {

        fun setScaledBitmap(imgView: ImageView, resources: Resources, refrence: Int, sampleSize: Int = 2){

            var options : BitmapFactory.Options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeResource(resources, refrence ,options)

            options.inSampleSize = sampleSize
            options.inJustDecodeBounds = false

            var img : Bitmap = BitmapFactory.decodeResource(resources, refrence, options)

            imgView.setImageBitmap(img)

        }
    }
}