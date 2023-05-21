package com.anurag.maverickmachinetest.model.api.data.hotNews

data class HotNewsResponse(
    val hotNews: List<HotNews>,
    val message: String,
    val status: Boolean
)