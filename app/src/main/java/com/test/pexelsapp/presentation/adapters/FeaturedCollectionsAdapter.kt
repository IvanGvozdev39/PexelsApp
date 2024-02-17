package com.test.pexelsapp.presentation.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.pexelsapp.R

class FeaturedCollectionsAdapter(private val context: Context?,
                                 private var collectionList: List<String>) : RecyclerView.Adapter<FeaturedCollectionsAdapter.ViewHolder>() {

    fun setData(collections: List<String>) {
        collectionList = collections
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val titleTV: TextView = view.findViewById(R.id.text_view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.featured_collections_item, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return collectionList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = collectionList[position]
        holder.titleTV.text = data

        if (data.contains("Curated Picks")) {
            context?.let { holder.titleTV.setTextColor(it.getColor(R.color.white)) }
            context?.let { holder.titleTV.backgroundTintList = ColorStateList.valueOf(it.getColor(R.color.red)) }
        }
    }
}