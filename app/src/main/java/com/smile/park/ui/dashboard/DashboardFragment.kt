package com.smile.park.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.smile.park.R
import com.smile.park.databinding.FragmentDashboardBinding
import com.smile.park.utils.helpers.BaseFragment
import com.smile.park.utils.helpers.EventObserver
import com.smile.park.utils.ui.adapters.AdapterStatistic
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DashboardFragment : BaseFragment(R.layout.fragment_dashboard) {

    private val binding by viewBinding(FragmentDashboardBinding::bind)
    private val viewModel: DashboardViewModel by viewModels()

    private val adaptersMenu by lazy(LazyThreadSafetyMode.NONE) { AdapterStatistic() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adaptersMenu.attach(binding.statsMain)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.attractionsFlow.collectLatest {
                    adaptersMenu.submitList(it)
                }
            }
        }

        viewModel.navigationLiveData.observe(viewLifecycleOwner, EventObserver {
            when (it) {
                is DashboardViewModel.Navigation.DetailsStatScreen -> {
                    findNavController().navigate(DashboardFragmentDirections.toRatingDetailsFragment(it.attraction))
                }
            }
        })

    }
}