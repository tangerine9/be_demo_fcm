// FCMController.kt 파일 내용을 이것으로 교체
package com.tangerine9.demofcm.controller

import com.tangerine9.demofcm.service.FCMService
import com.tangerine9.demofcm.model.dto.FCMRequest
import com.tangerine9.demofcm.model.dto.TokenRequest
import com.tangerine9.demofcm.model.dto.TopicRequest
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid

@RestController
@RequestMapping("/api/notifications")
class FCMController(private val fcmService: FCMService) {
    private val logger = LoggerFactory.getLogger(FCMController::class.java)

    @PostMapping("/send/token")
    fun sendNotificationToToken(@Valid @RequestBody request: TokenRequest) =
        fcmService.sendMessageToToken(request.token, request.title, request.body, request.data)
    
    @PostMapping("/send/topic")
    fun sendNotificationToTopic(@Valid @RequestBody request: TopicRequest) = 
        fcmService.sendMessageToTopic(request.topic, request.title, request.body, request.data)

    @PostMapping("/send/all")
    fun sendNotificationToAll(@Valid @RequestBody request: FCMRequest) = 
        fcmService.sendMessageToAll(request.title, request.body, request.data)
}