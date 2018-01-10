package com.corp_awesome.bellus

import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder


/**
 *
 */
class GalleryAdapter(private var picList: ArrayList<String>, private var context : Context) : Adapter<GalleryAdapter.GalleryViewHolder>() {
    private val absoluteAssetPath = "file:///android_asset/"


    override fun getItemCount() : Int{
        return picList.size
    }

    override fun onBindViewHolder(holder: GalleryViewHolder?, position: Int) {

        Glide.with(context).load(Uri.parse(absoluteAssetPath + picList[position])).into(holder!!.img)

        holder!!.img.setOnClickListener {
            (context as Gallery).showPreview(position)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.gallery_list_item, parent , false)
        return  GalleryViewHolder(view)
    }

    inner class GalleryViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        var img : ImageView = view.findViewById(R.id.gallery_RV_image)


    }




}