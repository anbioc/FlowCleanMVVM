package com.multithread.cocoon.di

import android.app.Application
import com.multithread.cocoon.di.module.*
import com.multithread.cocoon.di.scope.PerApplication
import com.multithread.cocoon.presentation.topstories.di.TopStoriesFragmentBinding
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

@PerApplication
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidModule::class,
        ViewModelFactoryModule::class,
        NetworkModule::class,
        PersistenceModule::class,
        UseCaseModule::class,
        DataModule::class,
        TopStoriesFragmentBinding::class,
        RepositoryModule::class,
    ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(instance: DaggerApplication)
}