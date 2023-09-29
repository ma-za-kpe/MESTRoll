package com.mest.mestroll.core.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class NetworkStatusInterceptor @Inject constructor(private val connectionManager: ConnectionManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (connectionManager.isConnected()) {
            chain.proceed(chain.request())
        } else {
            throw NetworkUnavailableException()
        }
    }
}

class NoDrinksException(message: String): Exception(message)
class NetworkUnavailableException(message: String = "No network available :(") : IOException(message)
class NetworkException(message: String): Exception(message)