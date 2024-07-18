package com.practice.openinapp.domain.api

import com.practice.openinapp.data.DashBoardResponse
import retrofit2.Call
import retrofit2.http.GET

interface AppApi {

    @GET("api/v1/dashboardNew")
    suspend fun getDashBoard(
    ) : Call<DashBoardResponse>
}