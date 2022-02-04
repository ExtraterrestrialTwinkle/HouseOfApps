package com.siuzannasmolianinova.houseofapps.presentation.items

import android.view.View
import com.siuzannasmolianinova.houseofapps.R
import com.siuzannasmolianinova.houseofapps.data.entity.Album
import com.siuzannasmolianinova.houseofapps.databinding.ItemAlbumBinding
import com.xwray.groupie.viewbinding.BindableItem

class AlbumItem(private val album: Album) : BindableItem<ItemAlbumBinding>() {

    override fun bind(binding: ItemAlbumBinding, position: Int) {
        binding.albumTitle.text = album.title
    }

    override fun getLayout(): Int = R.layout.item_album

    override fun initializeViewBinding(view: View): ItemAlbumBinding {
        return ItemAlbumBinding.bind(view)
    }
}
