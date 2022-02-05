package com.siuzannasmolianinova.houseofapps.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.siuzannasmolianinova.houseofapps.data.entity.Album
import com.siuzannasmolianinova.houseofapps.databinding.FragmentAlbumsBinding
import com.siuzannasmolianinova.houseofapps.presentation.items.AlbumItem
import com.siuzannasmolianinova.houseofapps.presentation.view_models.AlbumsViewModel
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class AlbumsFragment : Fragment() {

    private var _binding: FragmentAlbumsBinding? = null
    private val binding: FragmentAlbumsBinding get() = _binding!!
    private lateinit var mAdapter: GroupieAdapter
    private val viewModel: AlbumsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlbumsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("Albums")
        binding.appBar.toolbar.title = "Albums"
        initRV()
        bindViewModel()
    }

    private fun initRV() {
        mAdapter = GroupieAdapter()
        binding.albumsFeed.run {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun bindViewModel() {
        viewModel.loadAlbums()
        viewModel.albums.observe(viewLifecycleOwner) { albums ->
            Timber.d("albums observe")
            albums.forEach { album ->
                Timber.d("adapter add", album.toString())
                val item = AlbumItem(album) {
                    onItemClick(it)
                }
                mAdapter.add(item)
            }
        }
    }

    private fun onItemClick(album: Album) {
        findNavController().navigate(
            AlbumsFragmentDirections.actionAlbumsFragmentToPhotosFragment(
                album.id,
                album.title
            )
        )
    }
}
