package com.task.demo.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.task.demo.data.repository.MainRepository
import com.task.demo.ui.main.viewmodel.MainViewModel
import com.task.demo.utils.MyApp
import com.task.demo.utils.NetworkHelper
import javax.inject.Inject

/**
 * Class MyViewModelFactory
 * Description: Class for view model service functions
 *
 * @author  Yash Kalola
 * @version 1.0
 * @since   2022-02-21
 */
class MyViewModelFactory @Inject constructor(private val repository: MainRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.repository, NetworkHelper(MyApp.getAppContext())) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}