package com.smile.park.ui.setting

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.smile.park.R
import com.smile.park.databinding.FragmentSettingBinding
import com.smile.park.utils.helpers.EventObserver
import com.smile.park.utils.ui.adapters.AdapterSettingAttractions
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SettingFragment : Fragment(R.layout.fragment_setting) {

    private val binding by viewBinding(FragmentSettingBinding::bind)
    private val viewModel: SettingViewModel by viewModels()

    private val adaptersMenu by lazy(LazyThreadSafetyMode.NONE) { AdapterSettingAttractions() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.top, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.menu_about -> {
                        viewModel.onMenuOptionItemClicked()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        binding.settingNewAttraction.setOnClickListener {
            viewModel.onAddClicked()
        }

        adaptersMenu.attach(binding.settingAttractions)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.attractionsFlow.collectLatest {
                    adaptersMenu.submitList(it)
                }
            }
        }

        viewModel.navigationLiveData.observe(viewLifecycleOwner, EventObserver { event ->
            when (event) {
                SettingViewModel.Navigation.AboutScreen -> {

                }
                is SettingViewModel.Navigation.Details -> {
                    findNavController().navigate(SettingFragmentDirections.toDetailsAttractionFragment(event.attraction))
                }
            }
        })

    }
}