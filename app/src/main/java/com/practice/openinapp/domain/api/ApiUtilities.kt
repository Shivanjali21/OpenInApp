package com.practice.openinapp.domain.api

import com.practice.openinapp.utils.Constant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiUtilities {

    private val headers = mapOf(
        "Accept" to "application/json",
        "Authorization" to "Bearer ${Constant.TOKEN}"
    )

    private val client = OkHttpClient.Builder().apply {
        addInterceptor { chain ->
            val newRequest = chain.request().newBuilder().apply {
                headers.forEach { (key, value) -> addHeader(key, value) }
            }.build()
            chain.proceed(newRequest)
        }
    }.connectTimeout(5, TimeUnit.MINUTES).build()

    val api : ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(Constant.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)

    }
}
