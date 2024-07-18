package com.practice.openinapp.repository

import com.practice.openinapp.domain.api.ApiService
import javax.inject.Inject

class DashBoardRepo @Inject constructor(private val apiService: ApiService) {
    suspend fun getDashboardData(token: String) = apiService.getDashboardData(token)
}