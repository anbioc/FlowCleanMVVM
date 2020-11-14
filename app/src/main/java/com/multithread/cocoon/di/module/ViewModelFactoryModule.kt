package com.multithread.cocoon.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.multithread.cocoon.di.ViewModelKey
import com.multithread.cocoon.factory.AppViewModelFactory
import com.multithread.cocoon.presentation.topstories.TopStoriesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {


    @Binds
    @IntoMap
    @ViewModelKey(TopStoriesViewModel::class)
    abstract fun bindTopStoriesViewModel(viewModel: TopStoriesViewModel): ViewModel


    @Binds
    internal abstract fun bindViewModelFactory(factory : AppViewModelFactory) : ViewModelProvider.Factory

}
