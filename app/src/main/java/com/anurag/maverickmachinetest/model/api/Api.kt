package com.anurag.maverickmachinetest.model.api

import android.content.Context
import com.anurag.maverickmachinetest.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Api {

    private val BASE_URL = "https://example.com/"

    private fun getRetrofit(context: Context): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOkhttpClient(context))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getLoggingInterceptor(): HttpLoggingInterceptor {

        val httpLoggingInterceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG){

            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        return httpLoggingInterceptor
    }

    private fun getOkhttpClient(context: Context): OkHttpClient {

        return OkHttpClient().newBuilder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)
            .addInterceptor(getLoggingInterceptor())
            .addNetworkInterceptor(getLoggingInterceptor())
            .addNetworkInterceptor(MockInterceptor(context))
            .build()
    }

    fun getApi(context: Context): ApiMethods {

        return getRetrofit(context).create(ApiMethods::class.java)
    }
}