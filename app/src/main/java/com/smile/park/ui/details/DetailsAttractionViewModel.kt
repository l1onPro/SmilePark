package com.smile.park.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.smile.park.domain.AttractionsUc
import com.smile.park.domain.models.Attraction
import com.smile.park.utils.helpers.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class DetailsAttractionViewModel(private val attraction: Attraction?) : BaseViewModel() {

    private val attractionsUc: AttractionsUc by inject()

    fun btnWasClicked(title: String, description: String) {
        viewModelScope.launch(Dispatchers.IO + NonCancellable) {
            if (attraction == null) {
                attractionsUc.addNew(title, description)
            } else {
                attractionsUc.update(
                    attraction.copy(
                        title = title,
                        description = description,
                    )
                )
            }
        }
    }

    class DetailsAttractionViewModelFactory(
        private val attraction: Attraction?,
    ) : ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T = DetailsAttractionViewModel(attraction) as T
    }
}