package com.multithread.cocoon.di.module

import com.multithread.cocoon.di.scope.DefaultDispatcher
import com.multithread.cocoon.di.scope.IODispatcher
import com.multithread.cocoon.di.scope.MainDispatcher
import com.multithread.cocoon.di.scope.UnconfinedDispatcher
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
abstract class AppModule {

    companion object {
        @MainDispatcher
        @Provides
        fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

        @DefaultDispatcher
        @Provides
        fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

        @IODispatcher
        @Provides
        fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

        @UnconfinedDispatcher
        @Provides
        fun provideUnconfinedDispatcher(): CoroutineDispatcher = Dispatchers.Unconfined
    }

}