package com.tangerine9.demofcm.model.dto

import jakarta.validation.constraints.NotBlank

data class TokenRequest(
    @field:NotBlank(message = "토큰은 필수입니다") 
    val token: String,
    @field:NotBlank(message = "제목은 필수입니다") 
    val title: String,
    @field:NotBlank(message = "내용은 필수입니다") 
    val body: String
) 