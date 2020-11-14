package com.multithread.cocoon

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class CocoonApplication : DaggerApplication(){
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        TODO("Not yet implemented")
    }

}