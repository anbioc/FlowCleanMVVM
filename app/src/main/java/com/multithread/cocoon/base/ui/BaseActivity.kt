package com.multithread.cocoon.base.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.multithread.cocoon.base.ViewModelState
import com.multithread.cocoon.base.viewmodel.BaseFlowViewModel
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<STATE : ViewModelState, VIEWMODEL : BaseFlowViewModel<STATE>> :
    DaggerAppCompatActivity() {

    protected abstract val contentResourceId: Int

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: VIEWMODEL by lazy {
        viewModelFactory.create(getViewModelClass())
    }

    abstract fun getViewModelClass(): Class<VIEWMODEL>


    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(contentResourceId)
        initView()
    }

    open fun initView() {}
}