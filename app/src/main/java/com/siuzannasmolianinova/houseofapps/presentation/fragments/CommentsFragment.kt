package com.siuzannasmolianinova.houseofapps.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.siuzannasmolianinova.houseofapps.databinding.FragmentCommentsBinding
import com.siuzannasmolianinova.houseofapps.presentation.items.CommentItem
import com.siuzannasmolianinova.houseofapps.presentation.view_models.CommentsViewModel
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CommentsFragment : Fragment() {

    private var _binding: FragmentCommentsBinding? = null
    private val binding: FragmentCommentsBinding get() = _binding!!
    private lateinit var mAdapter: GroupieAdapter
    private val viewModel: CommentsViewModel by viewModels()
    private val args: CommentsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCommentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("Comments")
        binding.appBar.toolbar.title = "Comments"
        initPost(args.title, args.body)
        initRV()
        bindViewModel()
    }

    private fun initPost(title: String, body: String) {
        binding.post.postTitle.text = title
        binding.post.postBody.text = body
    }

    private fun initRV() {
        mAdapter = GroupieAdapter()
        binding.commentsRecyclerView.run {
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
        viewModel.loadComments(args.id)
        viewModel.comments.observe(viewLifecycleOwner) { comments ->
            Timber.d("comments observe")
            comments.forEach {
                Timber.d("adapter add", it.toString())
                mAdapter.add(CommentItem(it))
            }
        }
    }
}
