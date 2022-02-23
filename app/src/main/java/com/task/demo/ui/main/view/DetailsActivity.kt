package com.task.demo.ui.main.view

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.task.demo.R
import com.task.demo.data.model.RedditResponseModel
import com.task.demo.databinding.ActivityDetailsBinding
import com.task.demo.ui.base.BaseActivity

/**
 * Class DetailsActivity
 * Description: Responsible for viewing the details of a post that has been clicked
 *
 * @author  Yash Kalola
 * @version 1.0
 * @since   2022-02-22
 */
class DetailsActivity : BaseActivity() {


    private lateinit var binding: ActivityDetailsBinding
    private lateinit var redditResponseModel: RedditResponseModel.Data1Bean.ChildrenBean.DataBean
    private var responseString: String = ""

    //--------------------------
    //region LifeCycle
    //---------------------------

    override fun getLayoutView(): View {
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_details
    }

    override fun initView() {
        val intent = this.intent
        val bundle = intent.extras

        redditResponseModel =
            bundle!!.getSerializable("Data") as RedditResponseModel.Data1Bean.ChildrenBean.DataBean


        /* val myList = intent.getStringArrayListExtra("Data")

         responseString = myList!![0]
         val gS = Gson()
         childrenModel = gS.fromJson(
             responseString,
             ChildrenModel.Data1Bean.ChildrenBean.DataBean::class.java
         )*/


    }

    override fun setListener() {

    }

    override fun populateData() {
        binding.title.text = redditResponseModel.title
        binding.author.text = "-" + redditResponseModel.author
        binding.comments.text = redditResponseModel.numComments.toString() + " Comments"
        binding.date.text = getDate(redditResponseModel.created)
        Glide.with(activity)
            .load(redditResponseModel.url)
            .into(binding.image)


    }


    override fun onBack(): ImageView? {
        return binding.btnBack
    }

    //---------------------
    //endregion
    //--------------------------
    //region Private
    //---------------------------

    private fun getDate(created: Double): CharSequence? {
        var hours = ((created / (1000 * 60 * 60)) % 24).toString()
        return "$hours hours ago";

    }

    //---------------------
    //endregion
    //--------------------------


}