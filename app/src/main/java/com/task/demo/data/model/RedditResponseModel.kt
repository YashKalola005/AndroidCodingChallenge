package com.task.demo.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * This class represents a Reddit API response
 */
class RedditResponseModel : Serializable {

    @SerializedName("kind")
    private var kind: String? = null


    @SerializedName("data")
    private var data1: Data1Bean? = null

    fun getKind(): String? {
        return kind
    }

    fun setKind(kind: String?) {
        this.kind = kind
    }

    fun getData1(): Data1Bean? {
        return data1
    }

    fun setData1(data1: Data1Bean?) {
        this.data1 = data1
    }

    class Data1Bean : Serializable {
        @SerializedName("after")
        var after: String? = ""

        @SerializedName("dist")
        var dist = 0

        @SerializedName("modhash")
        var modhash: String? = null

        @SerializedName("geo_filter")
        var geoFilter: String? = null

        @SerializedName("before")
        var before: Any? = null

        @SerializedName("children")
        var children: List<ChildrenBean>? = null

        class ChildrenBean : Serializable {
            @SerializedName("kind")
            var kind: String? = null

            @SerializedName("data")
            var data: DataBean? = null

            class DataBean : Serializable {
                @SerializedName("approved_at_utc")
                var approvedAtUtc: Any? = null

                @SerializedName("subreddit")
                var subreddit: String? = null

                @SerializedName("selftext")
                var selftext: String? = null

                @SerializedName("author_fullname")
                var authorFullname: String? = null

                @SerializedName("saved")
                var isSaved = false

                @SerializedName("mod_reason_title")
                var modReasonTitle: Any? = null

                @SerializedName("gilded")
                var gilded = 0

                @SerializedName("clicked")
                var isClicked = false

                @SerializedName("title")
                var title: String? = null

                @SerializedName("subreddit_name_prefixed")
                var subredditNamePrefixed: String? = null

                @SerializedName("hidden")
                var isHidden = false

                @SerializedName("pwls")
                var pwls = 0

                @SerializedName("link_flair_css_class")
                var linkFlairCssClass: Any? = null

                @SerializedName("downs")
                var downs = 0

                @SerializedName("thumbnail_height")
                var thumbnailHeight = 0

                @SerializedName("top_awarded_type")
                var topAwardedType: String? = null

                @SerializedName("hide_score")
                var isHideScore = false

                @SerializedName("name")
                var name: String? = null

                @SerializedName("quarantine")
                var isQuarantine = false

                @SerializedName("link_flair_text_color")
                var linkFlairTextColor: String? = null

                @SerializedName("upvote_ratio")
                var upvoteRatio = 0.0

                @SerializedName("author_flair_background_color")
                var authorFlairBackgroundColor: String? = null

                @SerializedName("subreddit_type")
                var subredditType: String? = null

                @SerializedName("ups")
                var ups = 0

                @SerializedName("total_awards_received")
                var totalAwardsReceived = 0

                @SerializedName("media_embed")
                var mediaEmbed: MediaEmbedBean? = null

                @SerializedName("thumbnail_width")
                var thumbnailWidth = 0

                @SerializedName("author_flair_template_id")
                var authorFlairTemplateId: Any? = null

                @SerializedName("is_original_content")
                var isIsOriginalContent = false
                    private set

                @SerializedName("secure_media")
                var secureMedia: Any? = null

                @SerializedName("is_reddit_media_domain")
                var isIsRedditMediaDomain = false
                    private set

                @SerializedName("is_meta")
                var isIsMeta = false
                    private set

                @SerializedName("category")
                var category: Any? = null

                @SerializedName("secure_media_embed")
                var secureMediaEmbed: SecureMediaEmbedBean? = null

                @SerializedName("link_flair_text")
                var linkFlairText: Any? = null

                @SerializedName("can_mod_post")
                var isCanModPost = false

                @SerializedName("score")
                var score = 0

                @SerializedName("approved_by")
                var approvedBy: Any? = null

                @SerializedName("is_created_from_ads_ui")
                var isIsCreatedFromAdsUi = false
                    private set

                @SerializedName("author_premium")
                var isAuthorPremium = false

                @SerializedName("thumbnail")
                var thumbnail: String? = null

                @SerializedName("edited")
                var isEdited = false

                @SerializedName("author_flair_css_class")
                var authorFlairCssClass: Any? = null

                @SerializedName("gildings")
                var gildings: GildingsBean? = null

                @SerializedName("post_hint")
                var postHint: String? = null

                @SerializedName("is_self")
                var isIsSelf = false
                    private set

                @SerializedName("mod_note")
                var modNote: Any? = null

                @SerializedName("created")
                var created = 0.0

                @SerializedName("link_flair_type")
                var linkFlairType: String? = null

                @SerializedName("wls")
                var wls = 0

                @SerializedName("removed_by_category")
                var removedByCategory: Any? = null

                @SerializedName("banned_by")
                var bannedBy: Any? = null

                @SerializedName("author_flair_type")
                var authorFlairType: String? = null

                @SerializedName("domain")
                var domain: String? = null

                @SerializedName("allow_live_comments")
                var isAllowLiveComments = false

                @SerializedName("selftext_html")
                var selftextHtml: Any? = null

                @SerializedName("likes")
                var likes: Any? = null

                @SerializedName("suggested_sort")
                var suggestedSort: Any? = null

                @SerializedName("banned_at_utc")
                var bannedAtUtc: Any? = null

                @SerializedName("url_overridden_by_dest")
                var urlOverriddenByDest: String? = null

                @SerializedName("view_count")
                var viewCount: Any? = null

                @SerializedName("archived")
                var isArchived = false

                @SerializedName("no_follow")
                var isNoFollow = false

                @SerializedName("is_crosspostable")
                var isIsCrosspostable = false
                    private set

                @SerializedName("pinned")
                var isPinned = false

                @SerializedName("over_18")
                var isOver18 = false

                @SerializedName("preview")
                var preview: PreviewBean? = null

                @SerializedName("media_only")
                var isMediaOnly = false

                @SerializedName("can_gild")
                var isCanGild = false

                @SerializedName("spoiler")
                var isSpoiler = false

                @SerializedName("locked")
                var isLocked = false

                @SerializedName("author_flair_text")
                var authorFlairText: String? = null

                @SerializedName("visited")
                var isVisited = false

                @SerializedName("removed_by")
                var removedBy: Any? = null

                @SerializedName("num_reports")
                var numReports: Any? = null

                @SerializedName("distinguished")
                var distinguished: Any? = null

                @SerializedName("subreddit_id")
                var subredditId: String? = null

                @SerializedName("author_is_blocked")
                var isAuthorIsBlocked = false

                @SerializedName("mod_reason_by")
                var modReasonBy: Any? = null

                @SerializedName("removal_reason")
                var removalReason: Any? = null

                @SerializedName("link_flair_background_color")
                var linkFlairBackgroundColor: String? = null

                @SerializedName("id")
                var id: String? = null

                @SerializedName("is_robot_indexable")
                var isIsRobotIndexable = false
                    private set

                @SerializedName("report_reasons")
                var reportReasons: Any? = null

                @SerializedName("author")
                var author: String? = null

                @SerializedName("discussion_type")
                var discussionType: Any? = null

                @SerializedName("num_comments")
                var numComments = 0

                @SerializedName("send_replies")
                var isSendReplies = false

                @SerializedName("whitelist_status")
                var whitelistStatus: String? = null

                @SerializedName("contest_mode")
                var isContestMode = false

                @SerializedName("author_patreon_flair")
                var isAuthorPatreonFlair = false

                @SerializedName("author_flair_text_color")
                var authorFlairTextColor: String? = null

                @SerializedName("permalink")
                var permalink: String? = null

                @SerializedName("parent_whitelist_status")
                var parentWhitelistStatus: String? = null

                @SerializedName("stickied")
                var isStickied = false

                @SerializedName("url")
                var url: String? = null

                @SerializedName("subreddit_subscribers")
                var subredditSubscribers = 0

                @SerializedName("created_utc")
                var createdUtc = 0.0

                @SerializedName("num_crossposts")
                var numCrossposts = 0

                @SerializedName("media")
                var media: Any? = null

                @SerializedName("is_video")
                var isIsVideo = false
                    private set

                @SerializedName("link_flair_richtext")
                var linkFlairRichtext: List<*>? = null

                @SerializedName("user_reports")
                var userReports: List<*>? = null

                @SerializedName("author_flair_richtext")
                var authorFlairRichtext: List<*>? = null

                @SerializedName("content_categories")
                var contentCategories: List<String>? = null

                @SerializedName("all_awardings")
                var allAwardings: List<AllAwardingsBean>? = null

                @SerializedName("awarders")
                var awarders: List<*>? = null

                @SerializedName("treatment_tags")
                var treatmentTags: List<String>? = null

                @SerializedName("mod_reports")
                var modReports: List<*>? = null

                fun setIsOriginalContent(isOriginalContent: Boolean) {
                    isIsOriginalContent = isOriginalContent
                }

                fun setIsRedditMediaDomain(isRedditMediaDomain: Boolean) {
                    isIsRedditMediaDomain = isRedditMediaDomain
                }

                fun setIsMeta(isMeta: Boolean) {
                    isIsMeta = isMeta
                }

                fun setIsCreatedFromAdsUi(isCreatedFromAdsUi: Boolean) {
                    isIsCreatedFromAdsUi = isCreatedFromAdsUi
                }

                fun setIsSelf(isSelf: Boolean) {
                    isIsSelf = isSelf
                }

                fun setIsCrosspostable(isCrosspostable: Boolean) {
                    isIsCrosspostable = isCrosspostable
                }

                fun setIsRobotIndexable(isRobotIndexable: Boolean) {
                    isIsRobotIndexable = isRobotIndexable
                }

                fun setIsVideo(isVideo: Boolean) {
                    isIsVideo = isVideo
                }

                class MediaEmbedBean
                class SecureMediaEmbedBean
                class GildingsBean : Serializable {
                    @SerializedName("gid_1")
                    var gid1 = 0

                    @SerializedName("gid_2")
                    var gid2 = 0

                    @SerializedName("gid_3")
                    var gid3 = 0
                }

                class PreviewBean : Serializable {
                    @SerializedName("enabled")
                    var isEnabled = false

                    @SerializedName("images")
                    var images: List<ImagesBean>? = null

                    class ImagesBean : Serializable {
                        @SerializedName("source")
                        var source: SourceBean? = null

                        @SerializedName("variants")
                        var variants: VariantsBean? = null

                        @SerializedName("id")
                        var id: String? = null

                        @SerializedName("resolutions")
                        var resolutions: List<ResolutionsBean>? = null

                        class SourceBean
                        class VariantsBean
                        class ResolutionsBean : Serializable {
                            @SerializedName("url")
                            var url: String? = null

                            @SerializedName("width")
                            var width = 0

                            @SerializedName("height")
                            var height = 0
                        }
                    }
                }

                class AllAwardingsBean : Serializable {
                    @SerializedName("giver_coin_reward")
                    var giverCoinReward = 0

                    @SerializedName("subreddit_id")
                    var subredditId: Any? = null

                    @SerializedName("is_new")
                    var isIsNew = false
                        private set

                    @SerializedName("days_of_drip_extension")
                    var daysOfDripExtension = 0

                    @SerializedName("coin_price")
                    var coinPrice = 0

                    @SerializedName("id")
                    var id: String? = null

                    @SerializedName("penny_donate")
                    var pennyDonate = 0

                    @SerializedName("award_sub_type")
                    var awardSubType: String? = null

                    @SerializedName("coin_reward")
                    var coinReward = 0

                    @SerializedName("icon_url")
                    var iconUrl: String? = null

                    @SerializedName("days_of_premium")
                    var daysOfPremium = 0

                    @SerializedName("tiers_by_required_awardings")
                    var tiersByRequiredAwardings: Any? = null

                    @SerializedName("icon_width")
                    var iconWidth = 0

                    @SerializedName("static_icon_width")
                    var staticIconWidth = 0

                    @SerializedName("start_date")
                    var startDate: Any? = null

                    @SerializedName("is_enabled")
                    var isIsEnabled = false
                        private set

                    @SerializedName("awardings_required_to_grant_benefits")
                    var awardingsRequiredToGrantBenefits: Any? = null

                    @SerializedName("description")
                    var description: String? = null

                    @SerializedName("end_date")
                    var endDate: Any? = null

                    @SerializedName("subreddit_coin_reward")
                    var subredditCoinReward = 0

                    @SerializedName("count")
                    var count = 0

                    @SerializedName("static_icon_height")
                    var staticIconHeight = 0

                    @SerializedName("name")
                    var name: String? = null

                    @SerializedName("icon_format")
                    var iconFormat: String? = null

                    @SerializedName("icon_height")
                    var iconHeight = 0

                    @SerializedName("penny_price")
                    var pennyPrice = 0

                    @SerializedName("award_type")
                    var awardType: String? = null

                    @SerializedName("static_icon_url")
                    var staticIconUrl: String? = null

                    @SerializedName("resized_icons")
                    var resizedIcons: List<ResizedIconsBean>? = null

                    @SerializedName("resized_static_icons")
                    var resizedStaticIcons: List<ResizedStaticIconsBean>? = null

                    fun setIsNew(isNew: Boolean) {
                        isIsNew = isNew
                    }

                    fun setIsEnabled(isEnabled: Boolean) {
                        isIsEnabled = isEnabled
                    }

                    class ResizedIconsBean : Serializable {
                        @SerializedName("url")
                        var url: String? = null

                        @SerializedName("width")
                        var width = 0

                        @SerializedName("height")
                        var height = 0
                    }

                    class ResizedStaticIconsBean : Serializable {
                        @SerializedName("url")
                        var url: String? = null

                        @SerializedName("width")
                        var width = 0

                        @SerializedName("height")
                        var height = 0
                    }
                }
            }
        }
    }

}