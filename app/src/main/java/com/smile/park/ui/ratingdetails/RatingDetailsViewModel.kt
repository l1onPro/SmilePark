package com.smile.park.ui.ratingdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.smile.park.domain.RatingsUc
import com.smile.park.domain.models.Attraction
import com.smile.park.utils.helpers.BaseViewModel
import com.smile.park.utils.ui.adapters.UiStatsDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.inject
import java.text.SimpleDateFormat
import java.util.*

class RatingDetailsViewModel(private val attraction: Attraction) : BaseViewModel() {

    private val ratingsUc: RatingsUc by inject()

    val attractionsFlow: Flow<List<UiStatsDetails>> =
        ratingsUc.ratingsFlow.map { ratings ->
            ratings.filter { it.attractionId == attraction.id }
                .sortedByDescending { it.dateTimeStamp }
                .groupBy { getDate(it.dateTimeStamp).toString() }
                .map {
                    var countLikes = 0
                    var countDislikes = 0
                    it.value.forEach { rating ->
                        if (rating.isGood) {
                            countLikes++
                        } else {
                            countDislikes++
                        }
                    }
                    UiStatsDetails(
                        date = it.key,
                        countLikes = countLikes,
                        countDislikes = countDislikes,
                    )
                }
        }

    private fun getDate(time: Long): String? {
        val date = Date(time) // *1000 is to convert seconds to milliseconds
        val sdf = SimpleDateFormat("EEE, dd MMM yyyy ") // the format of your date
        return sdf.format(date)
    }

    class RatingDetailsViewModelFactory(
        private val attraction: Attraction,
    ) : ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T = RatingDetailsViewModel(attraction) as T
    }

}