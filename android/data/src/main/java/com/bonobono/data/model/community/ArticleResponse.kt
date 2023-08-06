package com.bonobono.data.model.community

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class ArticleResponse(
    val id: String? = null,
    val type: String,
    val title: String,
    val content: String,
    val imageSize: Int,
    val images: ImageResponse?,
    val comments: Int,
    val likes: Int,
    val nickname: String,
//    val profileUrl: String?,
    val recruitStatus: Boolean,
    val url: String? = null,
    val urlTitle: String? = null,
    val views: Int = 0,
//    val createdAt: Date = Date(),
): Parcelable