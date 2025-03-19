package com.tangerine9.demofcm.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import javax.annotation.PostConstruct

@Configuration
class FCMConfig {
    private val logger = LoggerFactory.getLogger(FCMConfig::class.java)

    @Value("\${spring.cloud.gcp.credentials.location}")
    private lateinit var credentialsPath: String

    @PostConstruct
    fun initialize() {
        try {
            val cleanPath = credentialsPath.removePrefix("classpath:")
            val credentials = GoogleCredentials.fromStream(
                ClassPathResource(cleanPath).inputStream
            )
            
            val options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .build()

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options)
                logger.info("Firebase 애플리케이션이 초기화되었습니다.")
            }
        } catch (e: Exception) {
            logger.error("Firebase 초기화 중 오류 발생: ${e.message}")
            throw IllegalStateException("Firebase 초기화 실패", e)
        }
    }
} 