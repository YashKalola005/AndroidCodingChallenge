package com.task.demo.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Class RedditResponseDTO
 * Description: data transfer object class (DTO)
 *
 * @author  Yash Kalola
 * @version 1.0
 * @since   2022-03-15
 */
data class RedditResponseDTO(
    @SerializedName("title")
    var title: String?,
    @SerializedName("author")
    var author: String?,
    @SerializedName("thumbnail")
    var thumbnail: String?,
    @SerializedName("num_comments")
    var num_comments: Int,
    @SerializedName("created")
    var created: Double,
    @SerializedName("url")
    var url: String?
) : Serializable