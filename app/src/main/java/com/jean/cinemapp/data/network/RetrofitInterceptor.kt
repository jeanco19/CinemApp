package com.jean.cinemapp.data.network

import com.jean.cinemapp.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitInterceptor @Inject constructor(): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val mUrlWithParams = chain.request().url.newBuilder()
            .addQueryParameter(Constants.URL_PARAM_API_KEY, Constants.API_KEY)
            .addQueryParameter(Constants.URL_PARAM_LANGUAGE, Constants.LANGUAGE).build()
        var mRequest = chain.request()
        mRequest = mRequest.newBuilder().url(mUrlWithParams)
            .addHeader(Constants.URL_HEADER_CONTENT_TYPE, Constants.CONTENT_TYPE)
            .addHeader(Constants.URL_HEADER_ACCEPT, Constants.CONTENT_TYPE).build()
        return chain.proceed(mRequest)
    }
}