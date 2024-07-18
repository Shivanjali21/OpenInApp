package com.practice.openinapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.practice.openinapp.repository.DashBoardRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: DashBoardRepo) : ViewModel() {

    fun getDashboardData(token: String) = liveData(Dispatchers.IO) {
        val data = repository.getDashboardData(token)
        emit(data)
    }

}