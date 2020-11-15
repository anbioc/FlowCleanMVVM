package com.multithread.cocoon.presentation.main.di

import com.multithread.cocoon.presentation.favorite.FavoriteFragment
import com.multithread.cocoon.presentation.main.DetailActivity
import com.multithread.cocoon.presentation.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract fun bindsMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindsDetailActivity(): DetailActivity

    @ContributesAndroidInjector
    abstract fun bindsFavoriteFragment(): FavoriteFragment
}