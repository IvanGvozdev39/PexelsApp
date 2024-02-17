package com.test.pexelsapp.presentation.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.test.pexelsapp.R

class FeaturedCollectionsAdapter(
    private val context: Context?,
    private var collectionList: List<com.test.domain.models.images.Collection>
) : RecyclerView.Adapter<FeaturedCollectionsAdapter.ViewHolder>() {

    private var selectedPosition: Int = RecyclerView.NO_POSITION


    fun setData(collections: List<com.test.domain.models.images.Collection>) {
        collectionList = collections
        notifyDataSetChanged()
    }


    fun setSelectedCollection(position: Int) {
        selectedPosition = position
        notifyDataSetChanged()
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTV: TextView = view.findViewById(R.id.text_view)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.featured_collections_item, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return collectionList.count()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val collection = collectionList[position]
        holder.titleTV.text = collection.title

        // Highlight selected collection
        val backgroundColor = if (position == selectedPosition) {
            context?.let { ContextCompat.getColor(it, R.color.red) } // Your highlight color
        } else {
            context?.let { ContextCompat.getColor(it, R.color.white) } // Your default color
        }
        backgroundColor?.let { holder.titleTV.setBackgroundColor(it) }

        holder.titleTV.setOnClickListener {
            setSelectedCollection(position)
            // Notify the fragment about the selected collection
            onCollectionSelected(collection)
        }
    }

    var onCollectionSelected: (com.test.domain.models.images.Collection) -> Unit = {}

}