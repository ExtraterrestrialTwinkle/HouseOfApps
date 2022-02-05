package com.siuzannasmolianinova.houseofapps.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.*
import com.siuzannasmolianinova.houseofapps.data.entity.Post
import com.siuzannasmolianinova.houseofapps.databinding.FragmentPostsBinding
import com.siuzannasmolianinova.houseofapps.databinding.ItemPostBinding
import com.siuzannasmolianinova.houseofapps.presentation.view_models.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class PostsFragment : Fragment() {

    private var _binding: FragmentPostsBinding? = null
    private val binding: FragmentPostsBinding get() = _binding!!
    private lateinit var mAdapter: Adapter
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
        initRV()
        bindViewModel()
    }

    private fun bindViewModel() {
        viewModel.fetchPostsLiveData().observe(viewLifecycleOwner) { pagingData ->
            Timber.d("posts observe")
            lifecycleScope.launch {
                mAdapter.submitData(pagingData)
            }
        }
    }

    private fun initRV() {
        binding.newsFeed.run {
            mAdapter = Adapter { post ->
                if (post != null) {
                    findNavController()
                        .navigate(
                            PostsFragmentDirections.actionPostsFragmentToCommentsFragment(
                                post.postId,
                                post.title,
                                post.body
                            )
                        )
                }
            }
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
}

class Adapter(private val onItemClick: (Post?) -> Unit) :
    PagingDataAdapter<Post, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NewsViewHolder).bind(getItem(position))
    }

    inner class NewsViewHolder(
        private val binding: ItemPostBinding,
        onItemClick: (Post?) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick(getItem(bindingAdapterPosition))
            }
        }

        fun bind(article: Post?) {
            binding.run {
                postTitle.text = article?.title
                postBody.text = article?.body
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return newItem.postId == oldItem.postId
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return newItem == oldItem
        }
    }
}
