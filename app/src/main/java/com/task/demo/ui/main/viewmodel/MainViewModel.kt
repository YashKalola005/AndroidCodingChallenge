package com.task.demo.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.task.demo.data.model.RedditResponseModel
import com.task.demo.data.repository.MainRepository
import com.task.demo.utils.*

import kotlinx.coroutines.*
import javax.inject.Inject

/**
 * Class MainViewModel
 * Description: View model for fetching the data using Reddit API
 *
 * @author  Yash Kalola
 * @version 1.0
 * @since   2022-02-21
 */
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _data = MutableLiveData<Resource<RedditResponseModel>>()


    /**
     * Calls the service that makes the API call with appropriate parameters
     */
    val data: LiveData<Resource<RedditResponseModel>>
        get() = _data


    fun getData(after: String?) {
        var baseUrl: String? = Constants.DOMAIN + "r/" + Constants.SUBREDDIT + "/top/.json?t=all"
        baseUrl = if (after!!.isEmpty()) {
            baseUrl + "&limit=" + Constants.LIMIT
        } else {
            baseUrl + "&limit=" + Constants.LIMIT + "&after=" + after
        }
        getRedditData(baseUrl)
    }


    private fun getRedditData(url: String?) {
        viewModelScope.launch {
            _data.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getAllData(url)?.let {
                    if (it.isSuccessful) {
                        _data.postValue(Resource.success(it.body()))
                    } else _data.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _data.postValue(Resource.error("Please check Network connection!", null))

        }
    }


}