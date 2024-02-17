package com.test.pexelsapp.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.test.domain.models.images.Photo
import com.test.pexelsapp.R
import com.test.pexelsapp.presentation.adapters.FeaturedCollectionsAdapter
import com.test.pexelsapp.presentation.adapters.ImageRVAdapter
import com.test.pexelsapp.presentation.viewmodelfactory.HomeViewModel
import com.test.pexelsapp.presentation.viewmodelfactory.HomeViewModelFactory

class HomeFragment : Fragment() {

    lateinit var viewModel: HomeViewModel
    lateinit var imageRVAdapter: ImageRVAdapter
    private val featuredCollectionsAdapter by lazy { FeaturedCollectionsAdapter(requireContext(), ArrayList()) }
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        viewModel = ViewModelProvider(this, HomeViewModelFactory()).get(HomeViewModel::class.java)

        progressBar = view.findViewById(R.id.progressBarHorizontal)

        viewModel.featuredCollectionNames.observe(viewLifecycleOwner) { collections ->
            if (collections != null) {
                featuredCollectionsAdapter.setData(collections)
            }
        }


        val featuredCollectionRV = view.findViewById<RecyclerView>(R.id.featured_collections_recycler_view)
        featuredCollectionRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        featuredCollectionRV.adapter = featuredCollectionsAdapter

        val imageRV = view.findViewById<RecyclerView>(R.id.idRVPhotos)
        val photoList = ArrayList<String>()



        imageRVAdapter = ImageRVAdapter()

        imageRV.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = imageRVAdapter
        }



        viewModel.featuredCollectionNames.observe(viewLifecycleOwner) { collections ->
            if (collections != null) {
                featuredCollectionsAdapter.setData(collections)
            }
        }

        featuredCollectionsAdapter.onCollectionSelected = { collection ->
//            viewModel.getImagesFromCollection(collection.id)
            if (collection.title.contains("Curated Picks"))
                viewModel.getCuratedPhotos()
            else
                viewModel.getImages(collection.title)
        }

        viewModel.imageList.observe(viewLifecycleOwner) {
            if (it.isSuccessful) {
                val response = it.body()
                if (response != null) {
                    imageRVAdapter.setImageData(response.photos as ArrayList<Photo>, context!!)
                    imageRV.scrollToPosition(0)
                }
            }
        }


        viewModel.cleanImageRV.observe(viewLifecycleOwner) {
            imageRVAdapter.setImageData(ArrayList<Photo>(), context!!)
        }




//        viewModel.imageListFeaturedCollection.observe(viewLifecycleOwner) {
//            if (it.isSuccessful) {
//                val response = it.body()
//                if (response != null) {
//                    imageRVAdapter.setImageData(response.media as ArrayList<Photo>, context!!)
//                }
//            }
//        }

        return view
    }
}