package com.bonobono.domain.usecase.chatting

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.repository.chatting.ChattingRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val chattingRepository: ChattingRepository
) {
    suspend operator fun invoke(message : String): NetworkResult<Unit> {
        return chattingRepository.sendMessage(message)
    }
}