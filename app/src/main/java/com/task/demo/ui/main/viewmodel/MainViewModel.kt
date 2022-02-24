package com.task.demo.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.task.demo.data.model.RedditResponseModel
import com.task.demo.data.repository.MainRepository

import kotlinx.coroutines.*

/**
 * Class MainViewModel
 * Description: View model for fetching the data using Reddit API
 *
 * @author  Yash Kalola
 * @version 1.0
 * @since   2022-02-21
 */
class MainViewModel(val mainRepository: MainRepository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val data = MutableLiveData<RedditResponseModel>()
    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    /**
     * Calls the service that makes the API call with appropriate parameters
     */
    fun getData(url: String?) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = mainRepository.getAllData(url)
            withContext(Dispatchers.Main) {
                if (response!!.isSuccessful) {
                    data.postValue(response.body())
                    loading.postValue(false)
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}