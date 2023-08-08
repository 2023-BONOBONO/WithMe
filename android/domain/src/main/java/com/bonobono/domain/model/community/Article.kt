package com.bonobono.domain.model.community

import java.util.Date

data class Article(
    val articleId: Int,
    val type: String,
    val title: String,
    val content: String,
    val imageSize: Int = 0,
    val mainImage: Image? = null,
    val images: List<Image> = emptyList(),
    val commentCnt: Int,
    val comments: List<Comment> = emptyList(),
    val liked: Boolean = false,
    val likes: Int,
    val nickname: String,
    val profileImg: String = "",
    val recruitStatus: Boolean,
    val url: String = "",
    val urlTitle: String = "",
    val views: Int = 0,
    val createdDate: Date = Date(),
)