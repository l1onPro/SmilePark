package com.smile.park.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.smile.park.domain.AttractionsUc
import com.smile.park.domain.models.Attraction
import com.smile.park.utils.helpers.BaseViewModel
import com.smile.park.utils.helpers.Event
import com.smile.park.utils.ui.adapters.UiAttraction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class SettingViewModel : BaseViewModel() {

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
                    navigationMutableLiveData.value = Event(Navigation.Details(it))
                },
                onDeleteClick = {
                    viewModelScope.launch(Dispatchers.IO) {
                        attractionsUc.deleteById(it.id)
                    }
                }
            )
        }
    }

    fun onMenuOptionItemClicked() {
        navigationMutableLiveData.value = Event(Navigation.AboutScreen)
    }

    fun onAddClicked() {
        navigationMutableLiveData.value = Event(Navigation.Details())
    }

    sealed class Navigation() {
        object AboutScreen : Navigation()
        class Details(val attraction: Attraction? = null) : Navigation()
    }
}