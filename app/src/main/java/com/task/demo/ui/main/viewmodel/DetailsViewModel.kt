package com.task.demo.ui.main.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.task.demo.R
import com.task.demo.data.model.RedditResponseDTO
import com.task.demo.utils.MyApp
import javax.inject.Inject


class DetailsViewModel @Inject constructor() : ViewModel() {

    var title = MutableLiveData<String>()
    var author = MutableLiveData<String>()
    var comments = MutableLiveData<String>()
    var date = MutableLiveData<String>()
    var url = MutableLiveData<String>()
    val context: Context = MyApp.getAppContext()


    fun setData(dataBeen: RedditResponseDTO) {
        this.title.value = dataBeen.title
        this.author.value =
            "${this.context.getString(R.string.dash)} ${dataBeen.author.toString()} "
        this.comments.value = "${dataBeen.num_comments} ${this.context.getString(R.string.comment)}"
        this.date.value = getTimeInHours(dataBeen.created).toString()
        this.url.value = dataBeen.url

    }

    /**
     * Converts milliseconds into hours
     */
    private fun getTimeInHours(created: Double): CharSequence? {
        var hours = ((created / (1000 * 60 * 60)) % 24).toString()
        hours = hours.substringBefore(".")
        return "$hours hours ago"

    }




}