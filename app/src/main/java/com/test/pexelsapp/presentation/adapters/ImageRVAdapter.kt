package com.test.pexelsapp.presentation.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.test.domain.models.Photo
import com.test.pexelsapp.R


class ImageRVAdapter : RecyclerView.Adapter<ImageRVAdapter.PhotoViewHolder>() {

    var list = ArrayList<Photo>()
    lateinit var context: Context

    fun setImageData(list: ArrayList<Photo>, context: Context) {
        this.list = list

        this.context = context

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoViewHolder {
        // this method is use to inflate the layout file
        // which we have created for our recycler view.
        // on below line we are inflating our layout file.
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.photo_rv_item,
            parent, false
        )
        // at last we are returning our view holder
        // class with our item View File.
        return PhotoViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = list[position]
        Glide.with(context)
            .load(photo.src.original)
            .transform(CenterCrop(), FitCenter()) // Apply transformations for efficient loading
            .placeholder(createColorDrawable(photo.avg_color)) // Set placeholder color
            .into(holder.photoIV)    }

    private fun createColorDrawable(colorName: String): ColorDrawable {
        val colorInt = Color.parseColor(colorName)
        // Create a solid color drawable with the average color
        return ColorDrawable(colorInt)
    }


    override fun getItemCount(): Int {
        // on below line we are returning
        // the size of our list
        return list.size
    }

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // on below line we are initializing our image view.
        val photoIV: ImageView = itemView.findViewById(R.id.idIVImage)
    }

}