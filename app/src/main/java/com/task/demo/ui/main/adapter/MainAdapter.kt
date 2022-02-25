package com.task.demo.ui.main.adapter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.task.demo.R
import com.task.demo.data.model.RedditResponseModel
import com.task.demo.databinding.ItemChildrenDataBinding
import com.task.demo.ui.main.view.DetailsActivity
import com.task.demo.utils.Constants
import com.task.demo.utils.MyPreferences
import com.task.demo.utils.AdapterListener
import java.lang.Exception

/**
 * Class MainAdapter
 * Description: Binds view with data
 *
 * @author  Yash Kalola
 * @version 1.0
 * @since   2022-02-21
 */
class MainAdapter(_activity: Activity, pageListener: AdapterListener) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {
    var redditResponseList: ArrayList<RedditResponseModel.Data1Bean.ChildrenBean> = arrayListOf()
    private val activity: Activity = _activity
    private val pageListener: AdapterListener = pageListener

    /**
     * Using the list of posts (redditResponseList) received from
     * the API response we bind each element to a view element
     */
    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var binding: ItemChildrenDataBinding
        private lateinit var preferences: MyPreferences

        fun bind(
            redditResponseList: ArrayList<RedditResponseModel.Data1Bean.ChildrenBean>,
            activity: Activity,
            position: Int,
            pageListener: AdapterListener
        ) {
            var redditResponseModel: RedditResponseModel.Data1Bean.ChildrenBean.DataBean? =
                redditResponseList[position].data
            preferences = MyPreferences(activity)
            binding = ItemChildrenDataBinding.bind(itemView)

            binding.title.text = redditResponseModel!!.title
            binding.author.text = redditResponseModel.author
            Glide.with(binding.userMage.context)
                .load(redditResponseModel.thumbnail)
                .placeholder(activity.getDrawable(R.drawable.place_holder))
                .error(activity.getDrawable(R.drawable.place_holder))
                .into(binding.userMage)

            binding.btnDelete.setOnClickListener {
                pageListener.onDelete(binding.root, position, redditResponseList)
            }
            if (preferences.getBoolean(redditResponseModel.title + Constants.IS_READ)) {
                binding.star.setImageResource(R.drawable.fav_s)

            } else {
                binding.star.setImageResource(R.drawable.fav)
            }

            binding.star.setOnClickListener {
                var image: Int
                if (preferences.getBoolean(redditResponseModel.title + Constants.IS_READ)) {
                    image = R.drawable.fav
                    preferences.setBoolean(redditResponseModel.title + Constants.IS_READ, false)
                } else {
                    image = R.drawable.fav_s
                    preferences.setBoolean(redditResponseModel.title + Constants.IS_READ, true)
                }
                setReadUnRead(activity, binding.star, image)
            }

            itemView.setOnClickListener {
                try {
                    val bundle = Bundle()
                    bundle.putParcelable("Data", redditResponseModel)
                    activity.startActivity(
                        Intent(activity, DetailsActivity::class.java).putExtras(
                            bundle
                        )
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
            if (position == redditResponseList.size - 1) {
                pageListener.onPage()
            }

        }

        /**
         * Read/Unread status logic to indicate the read status for
         * a post using a star in the UI
         */
        private fun setReadUnRead(_activity: Activity, v: ImageView, new_image: Int) {
            val animOut: Animation =
                AnimationUtils.loadAnimation(_activity, android.R.anim.fade_out)
            val animIn: Animation = AnimationUtils.loadAnimation(_activity, android.R.anim.fade_in)
            animOut.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}
                override fun onAnimationRepeat(animation: Animation) {}
                override fun onAnimationEnd(animation: Animation) {
                    v.setImageResource(new_image)
                    animIn.setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation) {}
                        override fun onAnimationRepeat(animation: Animation) {}
                        override fun onAnimationEnd(animation: Animation) {}
                    })
                    v.startAnimation(animIn)
                }
            })
            v.startAnimation(animOut)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_children_data, parent,
                false
            )
        )

    override fun getItemCount(): Int = redditResponseList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(redditResponseList, activity, position, pageListener)

    fun addData(data: RedditResponseModel) {
        data.getData1()?.children?.let {
            redditResponseList.addAll(it)
        }
        notifyDataSetChanged()
    }

    fun clearData() {
        redditResponseList = arrayListOf()
        notifyDataSetChanged()
    }

}