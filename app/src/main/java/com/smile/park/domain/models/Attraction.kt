package com.smile.park.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Attraction(
    val id: Int,
    val title: String,
    val description: String,
) : Parcelable
