package com.multithread.cocoon.di.module

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class AndroidModule {
    @Provides
    fun provideContext(application: Application) = application.applicationContext

}
