package com.smile.park.ui.rating

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.smile.park.domain.RatingsUc
import com.smile.park.domain.models.Attraction
import com.smile.park.utils.helpers.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class RatingViewModel(private val attraction: Attraction) : BaseViewModel() {

    private val ratingsUc: RatingsUc by inject()

    fun addLike() {
        viewModelScope.launch(Dispatchers.IO) {
            ratingsUc.addLike(attractionId = attraction.id)
        }
    }

    fun addDislike() {
        viewModelScope.launch(Dispatchers.IO) {
            ratingsUc.addDislike(attractionId = attraction.id)
        }
    }

    class RatingViewModelFactory(
        private val attraction: Attraction,
    ) : ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T = RatingViewModel(attraction) as T
    }
}