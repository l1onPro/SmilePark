package com.smile.park.ui.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.smile.park.R
import com.smile.park.databinding.FragmentDetailsBinding
import com.smile.park.utils.helpers.BaseFragment

class DetailsAttractionFragment : BaseFragment(R.layout.fragment_details) {

    private val binding by viewBinding(FragmentDetailsBinding::bind)
    private val args by navArgs<DetailsAttractionFragmentArgs>()
    private val viewModel: DetailsAttractionViewModel by viewModels {
        DetailsAttractionViewModel.DetailsAttractionViewModelFactory(args.attraction)
    }

    private val titleUi
        get() = binding.detailsEditTextTitle.text.toString()

    private val descriptionUi
        get() = binding.detailsEditTextDescription.text.toString()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle(
            if (args.attraction != null) {
                args.attraction?.title
            } else {
                ""
            }.orEmpty()
        )
        hideBottomMenu()

        binding.detailsEditTextTitle.setText(args.attraction?.title)
        binding.detailsEditTextDescription.setText(args.attraction?.description)
        binding.detailsBtn.setText(
            if (args.attraction != null) {
                R.string.action_save
            } else {
                R.string.action_add
            }
        )
        binding.detailsBtn.setOnClickListener {
            if (isAllDataGood()) {
                viewModel.btnWasClicked(titleUi, descriptionUi)
                findNavController().popBackStack()
            } else {
                Toast.makeText(requireContext(), R.string.details_message_valid_data, Toast.LENGTH_LONG)
            }
        }
    }

    private fun isAllDataGood(): Boolean {
        return titleUi.isNotBlank()
                && descriptionUi.isNotBlank()
    }

}