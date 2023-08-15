package com.bonobono.data.repository

import com.bonobono.data.local.dao.NotificationDao
import com.bonobono.data.mapper.toDomain
import com.bonobono.data.mapper.toModel
import com.bonobono.domain.model.notification.Notification
import com.bonobono.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val notificationDao: NotificationDao
): NotificationRepository {

    override fun getAllNotifications(): Flow<List<Notification>> = flow {
        notificationDao.getAllNotifications()
    }

    override suspend fun getNotification(id: Long): Notification {
        return notificationDao.getNotification(id).toDomain()
    }

    override suspend fun insertNotification(notification: Notification) {
        notificationDao.insertNotification(notification.toModel())
    }

    override suspend fun deleteNotification(notification: Notification) {
        notificationDao.deleteNotification(notification.toModel())
    }
}