package com.bonobono.presentation.ui.community.util

import com.bonobono.domain.model.community.Article
import com.bonobono.presentation.ui.community.views.gallery.PhotoSelected
import com.bonobono.presentation.ui.community.views.comment.TestUser
import java.util.Date

object DummyData {
    val imageUrl =
        "https://images.unsplash.com/photo-1689852484069-3e0fe82cc7c1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1887&q=80"
    val imageUrl_2 =
        "https://images.unsplash.com/photo-1690736159167-b00621eba9f6?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1887&q=80"
    val imageUrl_3 =
        "https://plus.unsplash.com/premium_photo-1673002094413-4c5141902505?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80"
    val selectedPhotos = listOf(PhotoSelected(imageUrl))
    val commentUser = TestUser(
        profile = imageUrl,
        name = "황신운",
        comment = "댓글 테스트",
        commentList = listOf(
            TestUser(
                profile = imageUrl_2,
                name = "오케이",
                comment = "댓글 테스트"
            )
        )
    )
    val commentUserNotMe = TestUser(
        type = 1,
        profile = imageUrl_3,
        name = "홍길동",
        comment = "청소하러 가자",
        commentList = listOf(
            TestUser(
                profile = imageUrl,
                name = "이지은",
                comment = "굿굿"
            ),
            TestUser(
                profile = imageUrl_2,
                name = "장혁",
                comment = "이상해 씨"
            )
        )
    )
    val commentList = listOf(
        commentUser,
        commentUserNotMe
    )
    val dummyArticle = Article(
        id = 1,
        type = "FREE",
        title = "쓰레기 Article Title",
        content = "쓰레기 content Title",
        nickname = "홍길동",
        profileUrl = imageUrl,
        commentCnt = 3,
        likes = 3,
        recruitStatus = false,
        createdAt = Date(),
    )
}