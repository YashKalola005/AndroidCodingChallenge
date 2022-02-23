package com.task.demo.utils

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import com.task.demo.R

object Constants {


    val BASEURL = "https://www.reddit.com/r/all/top/"
    const val DOMAIN = "https://www.reddit.com/"
    const val SUBREDDIT = "all"
    const val LIMIT = 25


  //  var deleteAllData = false

    fun getProgressDialog(context: Activity): Dialog? {
        val builder = AlertDialog.Builder(context)
        val inflater = context.layoutInflater
        val view: View = inflater.inflate(R.layout.loader, null)
        builder.setView(view)
        val dialog: Dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)
        return dialog
    }

}