package com.test.pexelsapp.presentation.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.test.domain.models.images.Photo
import com.test.pexelsapp.R


class ImageRVAdapter : RecyclerView.Adapter<ImageRVAdapter.PhotoViewHolder>() {

    var list = ArrayList<Photo>()
    lateinit var context: Context

    private var totalPhotosToLoad: Int = 0
    private var photosLoaded: Int = 0

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

        val placeholderDrawable = ColorDrawable(Color.parseColor(photo.avg_color)).apply {
            setBounds(0, 0, photo.width, photo.height)
        }

        Glide.with(holder.itemView)
            .load(photo.src.original)
            .placeholder(placeholderDrawable)
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.photoIV)


        holder.itemView.setOnClickListener {
            // Handle item click here
        }
    }


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
        val photoIV: ImageView = itemView.findViewById(R.id.idIVImage)
    }

}