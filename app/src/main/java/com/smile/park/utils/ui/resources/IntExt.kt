package com.smile.park.utils.ui.resources

import android.content.res.Resources

fun Int.dpToPx() = (this * Resources.getSystem().displayMetrics.density)

fun Int.toDp() = (this * Resources.getSystem().displayMetrics.density + 0.5f)

fun Float.toDp() = (this * Resources.getSystem().displayMetrics.density + 0.5f)