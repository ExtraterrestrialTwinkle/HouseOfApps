package com.siuzannasmolianinova.houseofapps.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.siuzannasmolianinova.houseofapps.data.entity.Photo
import com.siuzannasmolianinova.houseofapps.databinding.FragmentPhotosBinding
import com.siuzannasmolianinova.houseofapps.presentation.items.PhotoItem
import com.siuzannasmolianinova.houseofapps.presentation.view_models.PhotosViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.viewbinding.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class PhotosFragment : Fragment() {

    private var _binding: FragmentPhotosBinding? = null
    private val binding: FragmentPhotosBinding get() = _binding!!
    private lateinit var mAdapter: GroupieAdapter
    private val viewModel: PhotosViewModel by viewModels()
    private val args: PhotosFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("Photos")
        binding.appBar.toolbar.title = args.title
        initRV()
        bindViewModel()
    }

    private fun initRV() {
        mAdapter = GroupieAdapter().apply {
            spanCount = 3
        }
        binding.photosRecyclerView.run {
            adapter = mAdapter
            layoutManager = GridLayoutManager(requireContext(), mAdapter.spanCount).apply {
                spanSizeLookup = mAdapter.spanSizeLookup
            }
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.HORIZONTAL
                )
            )
        }
    }

    private fun bindViewModel() {
        viewModel.loadPhotos(args.id)
        viewModel.photos.observe(viewLifecycleOwner) { photos ->
            Timber.d("photos observe")
            photos.forEach { photo ->
                Timber.d("adapter add", photo.toString())
                val item = PhotoItem(photo) {
                    onItemClick(it)
                }
                mAdapter.add(item)
            }
        }
    }

    private fun onItemClick(photo: Photo) {
        findNavController().navigate(
            PhotosFragmentDirections.actionPhotosFragmentToBigPhotoFragment(
                photo.url
            )
        )
    }
}
