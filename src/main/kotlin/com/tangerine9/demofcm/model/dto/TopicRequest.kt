package com.tangerine9.demofcm.model.dto

import jakarta.validation.constraints.NotBlank

data class TopicRequest(
    @field:NotBlank(message = "토픽은 필수입니다") 
    val topic: String,
    @field:NotBlank(message = "제목은 필수입니다") 
    val title: String,
    @field:NotBlank(message = "내용은 필수입니다") 
    val body: String,
    val data: Map<String, String> = emptyMap()
) 