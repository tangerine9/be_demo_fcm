// exception 패키지에 새로 생성
package com.tangerine9.demofcm.exception

import com.google.firebase.messaging.FirebaseMessagingException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.MethodArgumentNotValidException
import java.time.LocalDateTime

@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    data class ErrorResponse(
        val message: String,
        val status: Int,
        val errors: List<String> = emptyList(),
        val timestamp: LocalDateTime = LocalDateTime.now()
    )

    @ExceptionHandler(FirebaseMessagingException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleFirebaseException(ex: FirebaseMessagingException): ErrorResponse {
        logger.error("FCM 메시지 전송 실패: ${ex.message}")
        return ErrorResponse(
            message = "FCM 메시지 전송 실패: ${ex.message}",
            status = HttpStatus.INTERNAL_SERVER_ERROR.value()
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationException(ex: MethodArgumentNotValidException): ErrorResponse {
        val errors = ex.bindingResult.fieldErrors.map { 
            "${it.field}: ${it.defaultMessage}" 
        }
        logger.error("유효성 검사 실패: $errors")
        return ErrorResponse(
            message = "유효성 검사 실패",
            status = HttpStatus.BAD_REQUEST.value(),
            errors = errors
        )
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleAllExceptions(ex: Exception): ErrorResponse {
        logger.error("서버 내부 오류: ${ex.message}", ex)
        return ErrorResponse(
            message = "서버 내부 오류가 발생했습니다",
            status = HttpStatus.INTERNAL_SERVER_ERROR.value()
        )
    }
}