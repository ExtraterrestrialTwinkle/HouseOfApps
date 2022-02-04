package com.siuzannasmolianinova.houseofapps.presentation.items

import android.view.View
import com.bumptech.glide.Glide
import com.siuzannasmolianinova.houseofapps.R
import com.siuzannasmolianinova.houseofapps.data.entity.Photo
import com.siuzannasmolianinova.houseofapps.databinding.ItemPhotoBinding
import com.xwray.groupie.viewbinding.BindableItem

class PhotoItem(private val photo: Photo) : BindableItem<ItemPhotoBinding>() {

    override fun bind(binding: ItemPhotoBinding, position: Int) {
        binding.titleTextView.text = photo.title
        Glide.with(binding.imageView)
            .load(photo.thumbnailUrl)
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_error)
            .into(binding.imageView)
    }

    override fun getLayout(): Int = R.layout.item_photo

    override fun initializeViewBinding(view: View): ItemPhotoBinding {
        return ItemPhotoBinding.bind(view)
    }
}
