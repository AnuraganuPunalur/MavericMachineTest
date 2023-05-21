package com.anurag.maverickmachinetest.model.api

import android.content.Context
import com.anurag.maverickmachinetest.utils.AppUtils
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class MockInterceptor(private val context: Context) : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {

        val requestString = chain.request().url.toUri().toString()
        if (requestString.endsWith("retrieveTopFunds") ||
            requestString.endsWith("retrieveHotNews")){

            val responseString = if (requestString.endsWith("retrieveTopFunds")){

                AppUtils.getJsonFromAssets(context = context, "top_funds.json")
            }else if (requestString.endsWith("retrieveHotNews")){

                AppUtils.getJsonFromAssets(context = context, "hot_news.json")
            }else{

                ""
            }
            return chain.proceed(chain.request())
                .newBuilder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message("success")
                .body(
                    responseString?.toByteArray()
                        ?.toResponseBody(
                            "application/json".toMediaTypeOrNull()
                        ))
                .addHeader("content-type", "application/json").build()

        }else{

            return chain.proceed(chain.request())
        }
    }
}