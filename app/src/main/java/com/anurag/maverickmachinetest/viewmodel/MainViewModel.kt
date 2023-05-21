package com.anurag.maverickmachinetest.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.anurag.maverickmachinetest.model.api.Api
import com.anurag.maverickmachinetest.model.api.data.ApiResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    private val hotNewsData: MutableLiveData<ApiResponse<*>> = MutableLiveData()
    private val topFunds: MutableLiveData<ApiResponse<*>> = MutableLiveData()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->

        hotNewsData.postValue(throwable.message?.let { ApiResponse.Error(it) })
    }

    private val fundExceptionHandler = CoroutineExceptionHandler{ _, throwable ->

        topFunds.postValue(throwable.message?.let { ApiResponse.Error(it) })
    }

    fun retrieveHotNews(): LiveData<ApiResponse<*>>{

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {

            hotNewsData.postValue(ApiResponse.Loading)
            val response = Api.getApi(context).retrieveHotNews()
            delay(2000)
            hotNewsData.postValue(ApiResponse.Success(response))
        }

        return hotNewsData
    }

    fun retrieveTopFunds(): LiveData<ApiResponse<*>>{

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {

            topFunds.postValue(ApiResponse.Loading)
            val response = Api.getApi(context).retrieveTopFunds()
            delay(2000)
            topFunds.postValue(ApiResponse.Success(response))
        }
        return topFunds
    }
}