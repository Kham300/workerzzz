package ru.workers.api

import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException


object RetrofitClient {

    private var ourIncetan: Retrofit? = null

    private const val BASE_URL = "http://185.234.14.109:10101"
    private const val SESSION_GUID = "dc540cb5-f5ad-493b-9f36-dc7eb659adff"

    val instance: Retrofit
        get() {
            if (ourIncetan == null) {
                ourIncetan = Retrofit.Builder()
                        .baseUrl(BASE_URL).client(create().build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
            }
            return ourIncetan!!
        }


    private fun create(): OkHttpClient.Builder {
        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor { chain ->
            val request = chain.request()
                    .newBuilder()
//                    .addHeader("SessionGUID", SESSION_GUID)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .build()
            chain.proceed(request)
        }

        return httpClient
    }
}