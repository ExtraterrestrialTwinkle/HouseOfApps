package com.siuzannasmolianinova.houseofapps.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.siuzannasmolianinova.houseofapps.databinding.FragmentPostsBinding
import com.siuzannasmolianinova.houseofapps.presentation.items.PostItem
import com.siuzannasmolianinova.houseofapps.presentation.view_models.PostsViewModel
import com.xwray.groupie.GroupieAdapter
import timber.log.Timber

class PostsFragment : Fragment() {

    private var _binding: FragmentPostsBinding? = null
    private val binding: FragmentPostsBinding get() = _binding!!
    private lateinit var rvAdapter: GroupieAdapter
    private val viewModel: PostsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("Posts")
        binding.appBar.toolbar.title = "Posts"
        bindViewModel()
        initRV()
    }

    private fun bindViewModel() {
        viewModel.posts.observe(viewLifecycleOwner) { posts ->
            posts.forEach { rvAdapter.add(PostItem(it)) }
        }
    }

    private fun initRV() {
        val rvAdapter = GroupieAdapter().apply {
            setOnItemClickListener { item, _ ->
                if (item is PostItem) {
                    findNavController()
                        .navigate(PostsFragmentDirections.actionPostsFragmentToCommentsFragment(item.id))
                }
            }
        }
        viewModel.loadPosts()
        binding.newsFeed.adapter = rvAdapter
    }
}
