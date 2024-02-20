package com.test.pexelsapp.presentation

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.test.pexelsapp.R


class DetailsFragment : Fragment() {

    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView = view.findViewById<ImageView>(R.id.image)
        val url = arguments?.getString("url")
        val avgColor = arguments?.getInt("avgColor")
        val width = arguments?.getInt("width")
        val height = arguments?.getInt("height")

        val placeholderDrawable = avgColor?.let { ColorDrawable(it) }
        if (height != null && width != null) {
            placeholderDrawable?.setBounds(0, 0, width, height)
        }

        Log.d("wtfdawa", "width $width height $height avgColor $avgColor url $url")
        Log.d("wtfdawa", "activity $activity")
        activity?.let {
            Glide.with(it)
                .load(url)
                .placeholder(placeholderDrawable)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
        }
    }


}