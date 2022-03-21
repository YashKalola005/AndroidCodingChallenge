package com.task.demo.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.task.demo.R
import com.task.demo.data.model.RedditResponseModel
import com.task.demo.databinding.ItemChildrenDataBinding
import com.task.demo.utils.*
import javax.inject.Inject


/**
 * Class MainAdapter
 * Description: Binds view with data
 *
 * @author  Yash Kalola
 * @version 1.0
 * @since   2022-02-21
 */
class MainAdapter @Inject constructor(
    adapterListener: AdapterListener,
    myPreferences: MyPreferences
) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {
    var redditResponseList: ArrayList<RedditResponseModel.Data1Bean.ChildrenBean> = arrayListOf()
    private val adapterListener: AdapterListener = adapterListener
    private val myPreferences: MyPreferences = myPreferences


    private val DIFF_CALLBACK: DiffUtil.ItemCallback<RedditResponseModel.Data1Bean.ChildrenBean> =
        object : DiffUtil.ItemCallback<RedditResponseModel.Data1Bean.ChildrenBean>() {
            override fun areItemsTheSame(
                @NonNull oldItem: RedditResponseModel.Data1Bean.ChildrenBean,
                @NonNull newItem: RedditResponseModel.Data1Bean.ChildrenBean
            ): Boolean {

                return oldItem.equals(newItem)
            }

            override fun areContentsTheSame(
                @NonNull oldItem: RedditResponseModel.Data1Bean.ChildrenBean,
                @NonNull newItem: RedditResponseModel.Data1Bean.ChildrenBean
            ): Boolean {

                return oldItem.equals(newItem)
            }
        }

    private val differ: AsyncListDiffer<RedditResponseModel.Data1Bean.ChildrenBean> =
        AsyncListDiffer(this, DIFF_CALLBACK)


    fun submitList(items: List<RedditResponseModel.Data1Bean.ChildrenBean>?) {
        differ.submitList(items)
    }

    /**
     * Using the list of posts (redditResponseList) received from
     * the API response we bind each element to a view element
     */
    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var binding: ItemChildrenDataBinding


        fun bind(
            redditResponseList: ArrayList<RedditResponseModel.Data1Bean.ChildrenBean>,
            position: Int,
            listener: AdapterListener,
            myPreferences: MyPreferences,
            differ: AsyncListDiffer<RedditResponseModel.Data1Bean.ChildrenBean>
        ) {

            var redditResponseModel: RedditResponseModel.Data1Bean.ChildrenBean.DataBean? =
                differ.currentList[position].data

            redditResponseModel?.let { redditResponseModel ->
                val context: Context = itemView.context
                binding = ItemChildrenDataBinding.bind(itemView)

                binding.title.text = redditResponseModel?.title
                binding.author.text = redditResponseModel?.author
                Glide.with(context)
                    .load(redditResponseModel.thumbnail)
                    .placeholder(context.getDrawable(R.drawable.place_holder))
                    .error(context.getDrawable(R.drawable.place_holder))
                    .into(binding.userMage)

                binding.btnDelete.setOnClickListener {
                    listener.onDelete(binding.root, position, redditResponseList)
                }
                if (myPreferences.getBoolean(redditResponseModel.title + Constants.IS_READ)
                ) {
                    binding.star.setImageResource(R.drawable.fav_s)

                } else {
                    binding.star.setImageResource(R.drawable.fav)
                }

                binding.star.setOnClickListener {
                    var image: Int
                    if (myPreferences.getBoolean(redditResponseModel.title + Constants.IS_READ)
                    ) {
                        image = R.drawable.fav
                        myPreferences.setBoolean(
                            redditResponseModel.title + Constants.IS_READ,
                            false
                        )
                    } else {
                        image = R.drawable.fav_s
                        myPreferences.setBoolean(
                            redditResponseModel.title + Constants.IS_READ,
                            true
                        )
                    }
                    setReadUnRead(context, binding.star, image)
                }

                itemView.setOnClickListener {
                    listener.onItemClick(redditResponseModel)
                }

                if (position == differ.currentList.size - 1) {
                    listener.onPage()
                }
            }
        }

        /**
         * Read/Unread status logic to indicate the read status for
         * a post using a star in the UI
         */
        private fun setReadUnRead(_activity: Context, v: ImageView, new_image: Int) {
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

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(redditResponseList, position, adapterListener, myPreferences, differ)

    fun addData(data: RedditResponseModel) {
        data.getData1()?.children?.let {
            redditResponseList.addAll(it)

            submitList(redditResponseList)
        }
        notifyDataSetChanged()
    }

}