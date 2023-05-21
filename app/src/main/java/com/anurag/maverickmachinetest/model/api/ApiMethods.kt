package com.anurag.maverickmachinetest.model.api

import com.anurag.maverickmachinetest.model.api.data.ApiResponse
import com.anurag.maverickmachinetest.model.api.data.hotNews.HotNewsResponse
import com.anurag.maverickmachinetest.model.api.data.topFunds.TopFundsResponse
import retrofit2.http.POST

interface ApiMethods {

    @POST("retrieveTopFunds")
    suspend fun retrieveTopFunds(): TopFundsResponse

    @POST("retrieveHotNews")
    suspend fun retrieveHotNews(): HotNewsResponse
}