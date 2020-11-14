package com.multithread.cocoon.util

import okhttp3.Interceptor
import okhttp3.Response
import java.net.InetAddress
import java.util.concurrent.TimeUnit

class NetworkInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isInternetAvailable()){
            throw NetworkException("network is not available!")
        }
        return chain.proceed(chain.withReadTimeout(4, TimeUnit.SECONDS).request())
    }

    private fun isInternetAvailable(): Boolean {
        return try {
            val ipAddr = InetAddress.getByName("google.com")
            !ipAddr.equals("")
        } catch (e: Exception) {
            false
        }

    }
}

class NetworkException(message: String): java.lang.Exception(message)

