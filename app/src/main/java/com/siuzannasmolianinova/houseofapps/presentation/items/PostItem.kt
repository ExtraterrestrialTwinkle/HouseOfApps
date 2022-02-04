package com.siuzannasmolianinova.houseofapps.presentation.items

import android.view.View
import com.siuzannasmolianinova.houseofapps.R
import com.siuzannasmolianinova.houseofapps.data.entity.Post
import com.siuzannasmolianinova.houseofapps.databinding.ItemPostBinding
import com.xwray.groupie.viewbinding.BindableItem

class PostItem(private val post: Post) : BindableItem<ItemPostBinding>() {

    override fun bind(binding: ItemPostBinding, position: Int) {
        binding.postTitle.text = post.title
        binding.postBody.text = post.body
    }

    override fun getLayout(): Int = R.layout.item_post

    override fun initializeViewBinding(view: View): ItemPostBinding {
        return ItemPostBinding.bind(view)
    }

    override fun getId(): Long = post.postId
}
