package com.bonobono.domain.model.chatting

import java.time.LocalDateTime

data class Chat (
    val name: String,
    val profileImg: Int,
    val latestChatStr: String,
    val latestTime: LocalDateTime,
    val latestCnt: Int,
    val cardType : String
)