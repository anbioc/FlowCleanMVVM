package com.multithread.cocoon

import com.multithread.cocoon.di.AppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class CocoonApplication : DaggerApplication(){
    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = appComponent


}