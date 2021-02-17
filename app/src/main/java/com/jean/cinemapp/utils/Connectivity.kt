package com.jean.cinemapp.utils

import com.jean.cinemapp.utils.Constants.INTERNET_HOST_NAME
import kotlinx.coroutines.coroutineScope
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

object Connectivity {

    suspend fun isNetworkAvailable() = coroutineScope {
        return@coroutineScope try {
            val socket = Socket()
            val socketAddress = InetSocketAddress(INTERNET_HOST_NAME, 53)
            socket.connect(socketAddress, 2000)
            socket.close()
            true
        } catch (exception: IOException) {
            false
        }
    }
}