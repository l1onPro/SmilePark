package com.smile.park.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smile.park.domain.AttractionsUc
import com.smile.park.domain.RatingsUc
import com.smile.park.domain.models.Attraction
import com.smile.park.utils.helpers.BaseViewModel
import com.smile.park.utils.helpers.Event
import com.smile.park.utils.ui.adapters.UiStatsMain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import org.koin.core.component.inject

class DashboardViewModel : BaseViewModel() {

    private val attractionsUc: AttractionsUc by inject()
    private val ratingsUc: RatingsUc by inject()

    private val navigationMutableLiveData = MutableLiveData<Event<Navigation>>()
    val navigationLiveData: LiveData<Event<Navigation>> get() = navigationMutableLiveData

    val attractionsFlow: Flow<List<UiStatsMain>> =
        attractionsUc.attractions.combine(ratingsUc.ratingsFlow) { attractions, ratings ->
            attractions.map { attraction ->
                UiStatsMain(
                    id = attraction.id,
                    title = attraction.title,
                    description = attraction.description,
                    countLikes = ratings.filter { attraction.id == it.attractionId && it.isGood }.size,
                    countDislikes = ratings.filter { attraction.id == it.attractionId && !it.isGood }.size,
                    onClick = {
                        navigationMutableLiveData.value = Event(Navigation.DetailsStatScreen(attraction))
                    },
                )
            }
        }

    sealed class Navigation {
        class DetailsStatScreen(val attraction: Attraction) : Navigation()
    }
}