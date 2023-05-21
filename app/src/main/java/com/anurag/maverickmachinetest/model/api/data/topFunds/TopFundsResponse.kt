package com.anurag.maverickmachinetest.model.api.data.topFunds

data class TopFundsResponse(
    val message: String,
    val status: Boolean,
    val topFunds: List<TopFund>
)