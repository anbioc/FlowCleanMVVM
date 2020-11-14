package com.multithread.cocoon.error

import com.multithread.cocoon.AppConstants
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import javax.inject.Inject

class GeneralHandlerImpl @Inject constructor() : ErrorContainer {
    override fun getError(throwable: Throwable): ErrorEntity {
        throwable.printStackTrace()
        return when (throwable) {
            is IOException -> ErrorEntity.Network(throwable.message.toString())
            is HttpException -> {
                when (throwable.code()) {
                    AppConstants.Network.HttpStatusCode.UNSATISFIABLE_REQUEST -> ErrorEntity.Network(throwable.message.toString())

                    HttpURLConnection.HTTP_NOT_FOUND -> ErrorEntity.NotFound(throwable.message.toString())

                    HttpURLConnection.HTTP_FORBIDDEN -> ErrorEntity.AccessDenied(throwable.message.toString())

                    HttpURLConnection.HTTP_UNAVAILABLE -> ErrorEntity.ServiceUnavailable(throwable.message.toString())

                    else -> ErrorEntity.Unknown(throwable.message.toString())
                }
            }
            else -> ErrorEntity.Unknown(throwable.message.toString())
        }
    }
}