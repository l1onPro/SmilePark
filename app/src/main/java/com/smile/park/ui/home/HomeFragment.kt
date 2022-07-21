package com.smile.park.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.smile.park.R
import com.smile.park.databinding.FragmentHomeBinding
import com.smile.park.utils.helpers.EventObserver
import com.smile.park.utils.ui.adapters.AdapterSettingAttractions
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()

    private val adaptersMenu by lazy(LazyThreadSafetyMode.NONE) { AdapterSettingAttractions() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adaptersMenu.attach(binding.homeAttractions)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.attractionsFlow.collectLatest {
                    adaptersMenu.submitList(it)
                }
            }
        }

        viewModel.navigationLiveData.observe(viewLifecycleOwner, EventObserver {
            when (it) {
                is HomeViewModel.Navigation.RatingScreen -> {
                    findNavController().navigate(HomeFragmentDirections.toRatingFragment(it.attraction))
                }
            }
        })

    }
}