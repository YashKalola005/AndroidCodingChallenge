package com.task.demo.utils

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.task.demo.data.model.RedditResponseModel

interface AdapterListener {
    fun onPage()
    fun onDelete(
        root: View,
        position: Int,
        redditResponseList: ArrayList<RedditResponseModel.Data1Bean.ChildrenBean>
    )
}