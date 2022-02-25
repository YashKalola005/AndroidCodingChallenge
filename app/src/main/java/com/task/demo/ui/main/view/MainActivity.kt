package com.task.demo.ui.main.view

import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.demo.R
import com.task.demo.data.api.RetrofitService
import com.task.demo.data.model.RedditResponseModel
import com.task.demo.data.repository.MainRepository
import com.task.demo.databinding.ActivityMainBinding
import com.task.demo.ui.base.BaseActivity
import com.task.demo.ui.base.MyViewModelFactory
import com.task.demo.ui.main.adapter.MainAdapter
import com.task.demo.ui.main.viewmodel.MainViewModel
import com.task.demo.utils.AdapterListener
import com.task.demo.utils.Constants
import com.task.demo.utils.MyPreferences


/**
 * Application Description: Android app that fetches
 * top posts from a predefined subreddit paginated as 25 posts
 * per page
 *
 * @author  Yash Kalola
 * @version 1.0
 * @since   2022-02-21
 */
class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    lateinit var binding: ActivityMainBinding
    private lateinit var myPreferences: MyPreferences
    private var after: String? = ""



    override fun getLayoutView(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    /**
     * Initialize preferences and view model
     */
    override fun initView() {
        myPreferences = MyPreferences(activity)
        val retrofitService = RetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)

        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(mainRepository)
        ).get(MainViewModel::class.java)

        setUpRecyclerView()

    }

    /**
     * Listener for Refresh and Click actions
     */
    override fun setListener() {
        binding.swipeRefresh.setOnRefreshListener {
            adapter.clearData()
            after = ""
            binding.swipeRefresh.isRefreshing = false
            getData()
        }

        binding.btnDeleteAll.setOnClickListener {
            deleteAllItems()
        }
    }

    /**
     * Populate Reddit top posts from the API response in UI
     */
    override fun populateData() {

        viewModel.data.observe(this) {
            after = it.getData1()!!.after.toString()

            adapter.addData(it)
        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe(this, Observer {
            if (it) {
                showProgressBar()
            } else {
                hideProgressBar()
            }
        })

        getData()
    }


    override fun onBack(): ImageView? {
        return null
    }

    /**
     * Calls the view model function that calls the required service
     */
    private fun getData() {
        var baseUrl: String? = Constants.DOMAIN + "r/" + Constants.SUBREDDIT + "/top/.json?t=all"

        baseUrl = if (after!!.isEmpty()) {
            baseUrl + "&limit=" + Constants.LIMIT
        } else {
            baseUrl + "&limit=" + Constants.LIMIT + "&after=" + after
        }
        viewModel.getData(baseUrl)
    }

    /**
     * setUpRecyclerView
     */
    private fun setUpRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(activity, object : AdapterListener {

            override fun onPage() {
                getData()
            }

            override fun onDelete(
                root: View,
                position: Int,
                redditResponseList: ArrayList<RedditResponseModel.Data1Bean.ChildrenBean>
            ) {
                deleteItem(root, position, redditResponseList)
            }
        })
        binding.recyclerView.adapter = adapter
    }

    /**
     * Function used to delete an item from the subreddit post list
     */
    private fun deleteItem(
        root: View,
        position: Int,
        redditResponseList: ArrayList<RedditResponseModel.Data1Bean.ChildrenBean>
    ) {
        val anim: Animation = AnimationUtils.loadAnimation(
            activity,
            android.R.anim.slide_out_right
        )
        anim.duration = 300
        root.startAnimation(anim)
        Handler().postDelayed(Runnable {
            if (redditResponseList.size == 0) {
                return@Runnable
            }
            //  myPreferences.setBoolean(childrenList[position].data!!.title, true)
            redditResponseList.removeAt(position) //Remove the current content from the array
            adapter.notifyDataSetChanged()
        }, anim.duration)
    }

    /**
     * Function used to delete all items from the subreddit post list
     */
    private fun deleteAllItems() {
        val anim: Animation = AnimationUtils.loadAnimation(
            activity,
            android.R.anim.slide_out_right
        )
        anim.duration = 300
        binding.recyclerView.startAnimation(anim)
        Handler().postDelayed(Runnable {
            adapter.clearData()
            after = ""
        }, anim.duration)
    }

}