package com.smile.park.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smile.park.domain.AttractionsUc
import com.smile.park.domain.models.Attraction
import com.smile.park.utils.helpers.BaseViewModel
import com.smile.park.utils.helpers.Event
import com.smile.park.utils.ui.adapters.UiAttraction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.inject

class HomeViewModel : BaseViewModel() {

    private val attractionsUc: AttractionsUc by inject()

    private val navigationMutableLiveData = MutableLiveData<Event<Navigation>>()
    val navigationLiveData: LiveData<Event<Navigation>> get() = navigationMutableLiveData

    val attractionsFlow: Flow<List<UiAttraction>> = attractionsUc.attractions.map { list ->
        list.map {
            UiAttraction(
                id = it.id,
                title = it.title,
                description = it.description,
                onClick = {
                    navigationMutableLiveData.value = Event(Navigation.RatingScreen(it))
                },
            )
        }
    }

    sealed class Navigation() {
        class RatingScreen(val attraction: Attraction) : Navigation()
    }
}