package com.multithread.cocoon.di.module

import android.content.Context
import androidx.annotation.NonNull
import com.multithread.cocoon.BuildConfig
import com.multithread.cocoon.data.network.NewsAPI
import com.multithread.cocoon.error.ErrorContainer
import com.multithread.cocoon.error.GeneralHandlerImpl
import com.multithread.cocoon.util.GliderImageLoaderImpl
import com.multithread.cocoon.util.ImageLoader
import com.multithread.cocoon.util.NetworkInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(context: Context): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(NetworkInterceptor())
            .cache(null)
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
             }).build()

    @Provides
    @Singleton
    fun provideRetrofit(
        @NonNull client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideErrorHandler(errorHandler: GeneralHandlerImpl): ErrorContainer = errorHandler

    @Provides
    fun provideImageLoader(imageLoader: GliderImageLoaderImpl): ImageLoader = imageLoader

    @Provides
    fun provideApiService(@NonNull retrofit: Retrofit): NewsAPI =
        retrofit.create(NewsAPI::class.java)


}