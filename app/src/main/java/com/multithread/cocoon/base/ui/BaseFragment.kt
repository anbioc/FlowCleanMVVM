package com.multithread.cocoon.base.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import com.multithread.cocoon.CocoonApplication
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment


abstract class BaseFragment : DaggerFragment() {

    companion object {
        const val DO_NOTHING = "do nothing"
    }

    protected abstract val contentResourceId: Int

    private val backPressCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressEventCalled()
            }
        }

    override fun onAttach(context: Context) {
        injectMembers()
        super.onAttach(context)
    }

    protected open fun injectMembers() {
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(
            requireActivity(), backPressCallback
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        backPressCallback.isEnabled = false
        backPressCallback.remove()
    }

    open fun onBackPressEventCalled() {

    }

    fun getApplication() = (requireActivity().application as CocoonApplication)


}