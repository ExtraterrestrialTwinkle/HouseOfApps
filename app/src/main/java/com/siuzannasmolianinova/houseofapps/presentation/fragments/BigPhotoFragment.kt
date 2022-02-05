package com.siuzannasmolianinova.houseofapps.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.siuzannasmolianinova.houseofapps.R
import com.siuzannasmolianinova.houseofapps.databinding.FragmentBigPhotoBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class BigPhotoFragment : Fragment() {
    private var _binding: FragmentBigPhotoBinding? = null
    private val binding: FragmentBigPhotoBinding get() = _binding!!
    private val args: BigPhotoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBigPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("Big Photo")
        Glide.with(requireContext())
            .load(args.url)
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_error)
            .into(binding.bigPhoto)
    }
}
