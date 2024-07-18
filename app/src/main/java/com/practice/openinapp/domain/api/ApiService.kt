package com.practice.openinapp.domain.api

import com.practice.openinapp.data.DashBoardResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("api/v1/dashboardNew")
    suspend fun getDashboardData(
        @Header("authorization") token: String
    ): DashBoardResponse
}