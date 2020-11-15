package com.multithread.cocoon.presentation.topstories.di

import com.multithread.cocoon.di.scope.PerFragment
import com.multithread.cocoon.presentation.topstories.TopStoriesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TopStoriesFragmentBinding() {
    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindTopStoriesFragment(): TopStoriesFragment
}