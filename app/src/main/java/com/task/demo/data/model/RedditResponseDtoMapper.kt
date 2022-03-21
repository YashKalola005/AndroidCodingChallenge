package com.task.demo.data.model

import com.task.demo.utils.DomainMapper

class RedditResponseDtoMapper : DomainMapper<RedditResponseDTO, RedditResponseModel.Data1Bean.ChildrenBean.DataBean> {

    override fun mapFromDomainModel(redditResponseModel: RedditResponseModel.Data1Bean.ChildrenBean.DataBean): RedditResponseDTO {
        return RedditResponseDTO(
            title = redditResponseModel.title,
            author = redditResponseModel.author,
            thumbnail = redditResponseModel.thumbnail,
            num_comments = redditResponseModel.numComments,
            created = redditResponseModel.created,
            url = redditResponseModel.url
        )
    }




}