package com.smile.park.ui.rating

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.smile.park.R
import com.smile.park.databinding.FragmentRatingBinding
import com.smile.park.utils.helpers.BaseFragment

class RatingFragment : BaseFragment(R.layout.fragment_rating) {

    private val binding by viewBinding(FragmentRatingBinding::bind)
    private val args by navArgs<RatingFragmentArgs>()
    private val viewModel: RatingViewModel by viewModels {
        RatingViewModel.RatingViewModelFactory(args.attraction)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(args.attraction.title)
        hideBottomMenu()

        val widthScreen = resources.displayMetrics.widthPixels
        binding.ratingLike.setOnClickListener {
            checkDoubleClick {
                viewModel.addLike()
                showMessage()
            }
        }
        with(binding.ratingLike.layoutParams) {
            width = widthScreen * 3 / 5
            height = widthScreen * 3 / 5
        }
        binding.ratingDislike.setOnClickListener {
            checkDoubleClick {
                viewModel.addDislike()
                showMessage()
            }
        }
        with(binding.ratingDislike.layoutParams) {
            width = widthScreen * 3 / 5
            height = widthScreen * 3 / 5
        }
    }

    private fun showMessage() {
        AlertDialog.Builder(context)
            .setTitle(R.string.rating_message_title)
            .setMessage(R.string.rating_message_text)
            .setPositiveButton(R.string.ok) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

}