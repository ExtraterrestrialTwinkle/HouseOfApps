package com.siuzannasmolianinova.houseofapps.presentation.items

import android.view.View
import com.siuzannasmolianinova.houseofapps.R
import com.siuzannasmolianinova.houseofapps.data.entity.Comment
import com.siuzannasmolianinova.houseofapps.databinding.ItemCommentBinding
import com.xwray.groupie.viewbinding.BindableItem

class CommentItem(private val comment: Comment) : BindableItem<ItemCommentBinding>() {

    override fun bind(binding: ItemCommentBinding, position: Int) {
        binding.name.text = comment.name
        binding.email.text = comment.email
        binding.commentBody.text = comment.body
    }

    override fun getLayout(): Int = R.layout.item_comment

    override fun initializeViewBinding(view: View): ItemCommentBinding {
        return ItemCommentBinding.bind(view)
    }
}