package com.smile.park.utils.helpers

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import com.smile.park.MainActivity
import org.koin.core.component.KoinComponent

abstract class BaseFragment(layoutId: Int) : Fragment(layoutId), KoinComponent {

    companion object {

        private const val CLICK_LOCKING_TIME = 500L
    }

    private var isWasClicked = false
    private var bottomMenuWasHide = false

    protected fun checkDoubleClick(click: () -> Unit) {
        if (!isWasClicked) {
            isWasClicked = true
            click.invoke()
            Handler(Looper.getMainLooper()).postDelayed({
                isWasClicked = false
            }, CLICK_LOCKING_TIME)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isNeedToShowBottom(true)
    }

    protected fun setTitle(text: String) {
        (requireActivity() as MainActivity).supportActionBar?.title = text
    }

    protected fun hideBottomMenu() {
        isNeedToShowBottom(false)
    }

    private fun isNeedToShowBottom(isNeed: Boolean = true) {
        (requireActivity() as MainActivity).isNeedToShowBottom(isNeed)
        bottomMenuWasHide = isNeed
    }

}