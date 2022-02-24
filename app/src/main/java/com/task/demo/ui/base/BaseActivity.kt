package com.task.demo.ui.base

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.task.demo.R
import com.task.demo.utils.Constants

/**
 * Class BaseActivity
 * Description: Base class for all activities
 *
 * @author  Yash Kalola
 * @version 1.0
 * @since   2022-02-21
 */
abstract class BaseActivity : AppCompatActivity() {

    protected lateinit var activity: Activity
    private lateinit var _alertDialog: AlertDialog;
    private var _progressDialog: Dialog? = null

    //--------------------------
    //region LifeCycle
    //---------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getLayoutView() != null) {
            setContentView(getLayoutView())
        } else {
            setContentView(getLayoutRes())
        }
        activity = this
        if (onBack() != null) {
            onBack()?.setOnClickListener { onBackPressed() }
        }
        _progressDialog = Constants.getProgressDialog(activity)
        initView()
        initProgressBar()
        setListener()
        populateData()
    }

    //---------------------
    //endregion
    //----------------------

    //--------------------------
    //region ProgressBar
    //---------------------------

    private fun initProgressBar() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val layoutInf: LayoutInflater = layoutInflater
        val loader: View = layoutInf.inflate(R.layout.loader, null)
        builder.setView(loader)
        _alertDialog = builder.create()
        _alertDialog.setCancelable(false)
        _alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    }

    protected fun showProgressBar() {
        if (!isFinishing) {
            if (_progressDialog != null) {
                _progressDialog!!.setCancelable(false)
                _progressDialog!!.show()
            }
        }
    }

    protected fun hideProgressBar() {
        if (!isFinishing) {
            if (_progressDialog != null) {
                try {
                    _progressDialog!!.dismiss()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    //--------------------------
    //endregion ProgressBar
    //---------------------------
    //region PROTECTED
    //---------------------------

    protected abstract fun getLayoutView(): View
    protected abstract fun getLayoutRes(): Int
    protected abstract fun initView()
    protected abstract fun setListener()
    protected abstract fun populateData()
    protected abstract fun onBack(): ImageView?

    //--------------------------
    //endregion PROTECTED
    //---------------------------
}