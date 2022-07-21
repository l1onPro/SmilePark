package com.smile.park.ui.ratingdetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.smile.park.R
import com.smile.park.databinding.FragmentRatingDetailsBinding
import com.smile.park.utils.helpers.BaseFragment
import com.smile.park.utils.ui.adapters.AdapterStatistic
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RatingDetailsFragment : BaseFragment(R.layout.fragment_rating_details) {

    private val binding by viewBinding(FragmentRatingDetailsBinding::bind)
    private val args by navArgs<RatingDetailsFragmentArgs>()
    private val viewModel: RatingDetailsViewModel by viewModels {
        RatingDetailsViewModel.RatingDetailsViewModelFactory(args.attraction)
    }

    private val adaptersMenu by lazy(LazyThreadSafetyMode.NONE) { AdapterStatistic() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle(args.attraction.title)

        adaptersMenu.attach(binding.ratingDetails)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.attractionsFlow.collectLatest {
                    adaptersMenu.submitList(it)
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()
        hideBottomMenu()
    }

}