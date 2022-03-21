package com.task.demo.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.demo.R
import com.task.demo.data.model.RedditResponseDTO
import com.task.demo.data.model.RedditResponseModel
import com.task.demo.databinding.ActivityMainBinding
import com.task.demo.ui.base.BaseActivity
import com.task.demo.ui.main.adapter.MainAdapter
import com.task.demo.ui.main.viewmodel.MainViewModel
import com.task.demo.utils.AdapterListener
import com.task.demo.utils.MyPreferences
import com.task.demo.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.collections.ArrayList


/**
 * Application Description: Android app that fetches
 * top posts from a predefined subreddit paginated as 25 posts
 * per page
 *
 * @author  Yash Kalola
 * @version 1.0
 * @since   2022-02-21
 */
@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var myPreferences: MyPreferences

    private lateinit var adapter: MainAdapter
    lateinit var binding: ActivityMainBinding


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
        setUpRecyclerView()
    }

    /**
     * Listener for Refresh and Click actions
     */
    override fun setListener() {
        binding.swipeRefresh.setOnRefreshListener {
            adapter.submitList(null)
            binding.swipeRefresh.isRefreshing = false
            getData()
        }

        binding.btnDeleteAll.setOnClickListener {
            deleteItem(binding.recyclerView, 0, null, true)
        }
    }

    /**
     * Populate Reddit top posts from the API response in UI
     */

    override fun populateData() {
        viewModel.data.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    hideProgressBar()
                    it.data?.let { data -> adapter.addData(data) }
                    binding.recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    showProgressBar()
                }
                Status.ERROR -> {
                    hideProgressBar()
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
        getData()
    }


    override fun onBack(): ImageView? {
        return null
    }

    /**
     * Calls the view model function that calls the required service
     */
    private fun getData() {
        viewModel.getRedditData()
    }

    /**
     * setUpRecyclerView
     */
    private fun setUpRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(object : AdapterListener {
            override fun onItemClick(redditResponseModel: RedditResponseModel.Data1Bean.ChildrenBean.DataBean) {
                try {
                    val data = RedditResponseDTO(
                        title = redditResponseModel.title,
                        author = redditResponseModel.author,
                        thumbnail = redditResponseModel.thumbnail,
                        num_comments = redditResponseModel.numComments,
                        created = redditResponseModel.created,
                        url = redditResponseModel.url
                    )
                    val bundle = Bundle()
                    bundle.putSerializable("Data", data)
                    startActivity(Intent(activity, DetailsActivity::class.java).putExtras(bundle))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onPage() {
                getData()
            }

            override fun onDelete(
                root: View,
                position: Int,
                redditResponseList: ArrayList<RedditResponseModel.Data1Bean.ChildrenBean>
            ) {
                deleteItem(root, position, redditResponseList, false)
            }
        }, myPreferences)
        binding.recyclerView.adapter = adapter
    }

    /**
     * Function used to delete an item from the subreddit post list
     */
    private fun deleteItem(
        root: View,
        position: Int,
        redditResponseList: ArrayList<RedditResponseModel.Data1Bean.ChildrenBean>?,
        deleteAll: Boolean)
    {
        val anim: Animation = AnimationUtils.loadAnimation(activity, android.R.anim.slide_out_right)
        anim.duration = 300
        root.startAnimation(anim)

        Handler(Looper.getMainLooper()).postDelayed({
            if (deleteAll) {
                adapter.submitList(null)
            } else {
                if (redditResponseList?.size == 0) {
                    return@postDelayed
                }
                redditResponseList?.removeAt(position)
                redditResponseList?.let { adapter.submitList(it) }

            }
            adapter.notifyDataSetChanged()
        }, anim.duration)
    }


}