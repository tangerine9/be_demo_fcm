// FCMService.kt 파일 내용을 이것으로 교체
package com.tangerine9.demofcm.service

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class FCMService {
    private val logger = LoggerFactory.getLogger(FCMService::class.java)

    fun sendMessageToToken(token: String, title: String, body: String): String {
        try {
            if (token.isBlank()) throw IllegalArgumentException("토큰은 필수입니다")
            return sendMessage(title, body) { builder -> builder.setToken(token) }
        } catch (e: Exception) {
            logger.error("토큰 기반 메시지 전송 실패: ${e.message}")
            throw e
        }
    }

    fun sendMessageToTopic(topic: String, title: String, body: String): String {
        try {
            if (topic.isBlank()) throw IllegalArgumentException("토픽은 필수입니다")
            return sendMessage(title, body) { builder -> builder.setTopic(topic) }
        } catch (e: Exception) {
            logger.error("토픽 기반 메시지 전송 실패: ${e.message}")
            throw e
        }
    }

    fun sendMessageToAll(title: String, body: String): String {
        try {
            return sendMessage(title, body) { builder -> builder.setTopic("all") }
        } catch (e: Exception) {
            logger.error("전체 메시지 전송 실패: ${e.message}")
            throw e
        }
    }

    private fun sendMessage(title: String, body: String, configureTarget: (Message.Builder) -> Message.Builder): String {
        if (title.isBlank() || body.isBlank()) throw IllegalArgumentException("제목과 내용은 필수입니다")
        val notification = Notification.builder().setTitle(title).setBody(body).build()
        val message = configureTarget(Message.builder().setNotification(notification)).build()
        return FirebaseMessaging.getInstance().send(message)
    }
}