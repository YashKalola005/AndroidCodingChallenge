package com.task.demo.ui.main.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.task.demo.R
import com.task.demo.data.model.RedditResponseModel
import com.task.demo.databinding.ActivityDetailsBinding
import com.task.demo.ui.base.BaseActivity
import com.task.demo.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

/**
 * Class DetailsActivity
 * Description: Responsible for viewing the details of a post that has been clicked
 *
 * @author  Yash Kalola
 * @version 1.0
 * @since   2022-02-22
 */
class DetailsActivity : BaseActivity() {

    lateinit var binding: ActivityDetailsBinding
    var redditResponseModel: RedditResponseModel.Data1Bean.ChildrenBean.DataBean? = null


    override fun getLayoutView(): View {
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_details
    }

    /**
     * Parse data from the post that has been clicked on into RedditResponseModel
     */
    override fun initView() {
        val intent = this.intent
        val bundle = intent.extras
        redditResponseModel = bundle?.getParcelable("Data")
    }

    override fun setListener() {
        binding.btnDownload.setOnClickListener {
            checkPermission(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                200
            )

        }
    }

    /**
     * Populate Reddit post details from the API response in UI
     */
    override fun populateData() {
        redditResponseModel?.let {
            binding.title.text = it.title
            binding.author.text = "- " + it.author
            binding.comments.text = it.numComments.toString() + " Comments"
            binding.date.text = getTimeInHours(it.created)
            Glide.with(activity)
                .load(it.url)
                .placeholder(getDrawable(R.drawable.place_holder))
                .error(getDrawable(R.drawable.place_holder))
                .into(binding.image)
        }

    }

    override fun onBack(): ImageView? {
        return binding.btnBack
    }

    /**
     * Checks storage permissions before downloading the image
     */
    private fun checkPermission(
        readExternalStorage: String,
        writeExternalStorage: String,
        requestCode: Int
    ) {
        if (ContextCompat.checkSelfPermission(
                activity,
                writeExternalStorage
            ) == PackageManager.PERMISSION_DENIED &&
            ContextCompat.checkSelfPermission(
                activity,
                readExternalStorage
            ) == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(readExternalStorage, writeExternalStorage),
                requestCode
            )
        } else {
            redditResponseModel?.url?.let { it1 ->
                CoroutineScope(Dispatchers.IO).launch {
                    downloadImage(it1)
                }
            }
        }
    }


    /**
     * Converts milliseconds into hours
     */
    fun getTimeInHours(created: Double): CharSequence? {
        var hours = ((created / (1000 * 60 * 60)) % 24).toString()
        hours = hours.substringBefore(".")
        return "$hours hours ago";

    }

    /**
     * Function responsible for downloading post thumbnail image
     */
    @SuppressLint("Range")
    fun downloadImage(url: String) {

        val directory = File(Environment.DIRECTORY_PICTURES)

        val downloadManager = this.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        val downloadUri = Uri.parse(url)

        // Setup the download request
        val request = DownloadManager.Request(downloadUri).apply {
            setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle(url.substring(url.lastIndexOf("/")))
                .setDescription("")
                .setDestinationInExternalPublicDir(
                    directory.toString(),
                    url.substring(url.lastIndexOf("/"))
                )
        }

        // Enqueue the download request
        val downloadId = downloadManager.enqueue(request)

        val query = DownloadManager.Query().setFilterById(downloadId)
        Thread {
            var downloading = true
            var msg: String? = ""
            var lastMsg = ""
            while (downloading) {
                val cursor: Cursor = downloadManager.query(query)
                cursor.moveToFirst()
                if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                    downloading = false
                }
                val status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
                msg = statusMessage(url, directory, status)
                if (msg != lastMsg) {
                    this.runOnUiThread {
                        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                    }
                    lastMsg = msg ?: ""
                }
                cursor.close()
            }
        }.start()
    }

    /**
     * Returns the download status of the post thumbnail image
     */
    private fun statusMessage(url: String, directory: File, status: Int): String? {
        var msg = ""
        msg = when (status) {
            DownloadManager.STATUS_FAILED -> Constants.DOWNLOAD_FAILED
            DownloadManager.STATUS_PAUSED -> Constants.DOWNLOAD_PAUSED
            DownloadManager.STATUS_PENDING -> Constants.DOWNLOAD_PENDING
            DownloadManager.STATUS_RUNNING -> Constants.DOWNLOAD_RUNNING
            DownloadManager.STATUS_SUCCESSFUL -> Constants.DOWNLOAD_SUCCESS + directory + url.substring(
                url.lastIndexOf("/")
            )
            else -> Constants.NO_DATA_FOUND
        }
        return msg
    }


}