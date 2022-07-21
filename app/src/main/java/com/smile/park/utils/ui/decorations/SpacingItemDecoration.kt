package com.smile.park.utils.ui.decorations

import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView
import com.smile.park.utils.ui.resources.toDp

class SpacingItemDecoration(
    private val orientation: Orientation,
    @DimenRes private val externalSpacing: Int = DEFAULT_EXTERNAL_SPACING,
    @DimenRes private val internalSpacing: Int = DEFAULT_INTERNAL_SPACING
) : RecyclerView.ItemDecoration() {

    private companion object {

        const val DEFAULT_EXTERNAL_SPACING: Int = 16
        const val DEFAULT_INTERNAL_SPACING: Int = 8
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        when (orientation) {
            Orientation.HORIZONTAL -> {
                when (parent.getChildAdapterPosition(view)) {
                    //first
                    0 -> {
                        outRect.left = externalSpacing.toDp().toInt()
                        outRect.right = internalSpacing.toDp().toInt()
                    }
                    //last
                    getLastPosition(parent) -> {
                        outRect.right = externalSpacing.toDp().toInt()
                    }
                    //internal
                    else -> outRect.right = internalSpacing.toDp().toInt()
                }
            }
            Orientation.VERTICAL -> {
                when (parent.getChildAdapterPosition(view)) {
                    //first
                    0 -> {
                        outRect.top = externalSpacing.toDp().toInt()
                        outRect.bottom = internalSpacing.toDp().toInt()
                    }
                    //last
                    getLastPosition(parent) -> {
                        outRect.bottom = externalSpacing.toDp().toInt()
                    }
                    //internal
                    else -> outRect.bottom = internalSpacing.toDp().toInt()
                }
            }
        }
    }

    private fun getLastPosition(parent: RecyclerView): Int = (parent.adapter?.itemCount ?: 0) - 1

    enum class Orientation {
        HORIZONTAL, VERTICAL
    }
}