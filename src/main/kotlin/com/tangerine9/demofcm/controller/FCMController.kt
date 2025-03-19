// FCMController.kt 파일 내용을 이것으로 교체
package com.tangerine9.demofcm.controller

import com.tangerine9.demofcm.service.FCMService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@RestController
@RequestMapping("/api/notifications")
class FCMController(private val fcmService: FCMService) {
    private val logger = LoggerFactory.getLogger(FCMController::class.java)

    data class FCMRequest(@field:NotBlank(message = "제목은 필수입니다") val title: String,
                         @field:NotBlank(message = "내용은 필수입니다") val body: String)

    data class TokenRequest(@field:NotBlank(message = "토큰은 필수입니다") val token: String,
                          @field:NotBlank(message = "제목은 필수입니다") val title: String,
                          @field:NotBlank(message = "내용은 필수입니다") val body: String)

    data class TopicRequest(@field:NotBlank(message = "토픽은 필수입니다") val topic: String,
                          @field:NotBlank(message = "제목은 필수입니다") val title: String,
                          @field:NotBlank(message = "내용은 필수입니다") val body: String)

    @PostMapping("/send/token")
    fun sendNotificationToToken(@Valid @RequestBody request: TokenRequest) = 
        fcmService.sendMessageToToken(request.token, request.title, request.body)

    @PostMapping("/send/topic")
    fun sendNotificationToTopic(@Valid @RequestBody request: TopicRequest) = 
        fcmService.sendMessageToTopic(request.topic, request.title, request.body)

    @PostMapping("/send/all")
    fun sendNotificationToAll(@Valid @RequestBody request: FCMRequest) = 
        fcmService.sendMessageToAll(request.title, request.body)
}