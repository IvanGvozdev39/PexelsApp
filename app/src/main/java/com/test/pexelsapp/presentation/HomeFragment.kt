package com.test.pexelsapp.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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
    private val featuredCollectionsAdapter by lazy {
        FeaturedCollectionsAdapter(
            requireContext(),
            ArrayList()
        )
    }
    private lateinit var progressBar: ProgressBar
    private lateinit var searchBarET: EditText
    private lateinit var searchBarCloseIcon: ImageView
    private lateinit var parentConstraintLayout: ConstraintLayout
    private lateinit var noResultsLayout: LinearLayout
    private lateinit var exploreBtn: Button



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        viewModel = ViewModelProvider(this, HomeViewModelFactory())[HomeViewModel::class.java]
        progressBar = view.findViewById(R.id.progressBarHorizontal)
        searchBarET = view.findViewById(R.id.search_bar_edit_text)
        searchBarCloseIcon = view.findViewById(R.id.search_bar_close_icon)
        parentConstraintLayout = view.findViewById(R.id.parent_constraint_layout)
        noResultsLayout = view.findViewById(R.id.no_results_layout)
        exploreBtn = view.findViewById(R.id.explore_button)

        viewModel.featuredCollectionNames.observe(viewLifecycleOwner) { collections ->
            if (collections != null) {
                featuredCollectionsAdapter.setData(collections)
            }
        }


        val featuredCollectionRV =
            view.findViewById<RecyclerView>(R.id.featured_collections_recycler_view)
        featuredCollectionRV.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        featuredCollectionRV.adapter = featuredCollectionsAdapter

        val imageRV = view.findViewById<RecyclerView>(R.id.idRVPhotos)
        val photoList = ArrayList<String>()



        imageRVAdapter = ImageRVAdapter(findNavController())

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
            searchBarET.clearFocus()
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

                    if (response.photos.size == 0) {
                        noResultsLayout.visibility = View.VISIBLE
                        imageRV.visibility = View.GONE
                    } else {
                        noResultsLayout.visibility = View.GONE
                        imageRV.visibility = View.VISIBLE
                    }
                }
            }
        }

        exploreBtn.setOnClickListener {
            Log.d("afajkff", "afawl,")
            noResultsLayout.visibility = View.GONE
            searchBarET.text.clear()
            searchBarET.clearFocus()
            viewModel.getPopularImages()
        }


        viewModel.cleanImageRV.observe(viewLifecycleOwner) {
            imageRVAdapter.setImageData(ArrayList<Photo>(), context!!)
        }


        val searchViewTextWatcher: TextWatcher = object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString().trim { it <= ' ' }.length == 0) {
                    searchBarCloseIcon.visibility = View.GONE
                } else {
                    searchBarCloseIcon.visibility = View.VISIBLE
                    featuredCollectionsAdapter.resetSelection()
                    viewModel.getImages(searchBarET.text.toString())
                    imageRV.scrollToPosition(0)
                }
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
                // TODO Auto-generated method stub
            }

            override fun afterTextChanged(s: Editable) {
                // TODO Auto-generated method stub
            }
        }

        searchBarCloseIcon.setOnClickListener {
            searchBarET.text.clear()
            searchBarET.clearFocus()
        }

        parentConstraintLayout.setOnClickListener {
            searchBarET.clearFocus()
        }

        searchBarET.addTextChangedListener(searchViewTextWatcher)


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