# Firebase Cloud Messaging (FCM) Demo Project

## 프로젝트 개요
이 프로젝트는 Firebase Cloud Messaging을 사용하여 푸시 알림을 전송하는 데모 애플리케이션입니다.

## 기술 스택
- Kotlin
- Spring Boot 3.x
- Firebase Admin SDK
- Gradle

## 주요 기능
1. 특정 디바이스로 푸시 알림 전송
2. 특정 토픽으로 푸시 알림 전송
3. 전체 디바이스로 푸시 알림 전송

## API 엔드포인트
### 1. 특정 디바이스로 알림 전송
- **URL**: `/api/notifications/send/token`
- **Method**: POST
- **Request Body**:
```json
{
    "token": "디바이스_FCM_토큰",
    "title": "알림 제목",
    "body": "알림 내용"
}
```

### 2. 특정 토픽으로 알림 전송
- **URL**: `/api/notifications/send/topic`
- **Method**: POST
- **Request Body**:
```json
{
    "topic": "토픽_이름",
    "title": "알림 제목",
    "body": "알림 내용"
}
```

### 3. 전체 디바이스로 알림 전송
- **URL**: `/api/notifications/send/all`
- **Method**: POST
- **Request Body**:
```json
{
    "title": "알림 제목",
    "body": "알림 내용"
}
```

## 프로젝트 구조
```
src/main/kotlin/com/tangerine9/demofcm/
├── controller/
│   └── FCMController.kt
├── service/
│   └── FCMService.kt
├── model/
│   └── dto/
│       ├── FCMRequest.kt
│       ├── TokenRequest.kt
│       └── TopicRequest.kt
├── config/
│   └── FCMConfig.kt
└── exception/
    └── GlobalExceptionHandler.kt
```

## 설정
1. Firebase Console에서 서비스 계정 키 다운로드
2. `src/main/resources/firebase-service-account.json`에 저장
3. `application.properties`에 Firebase 프로젝트 ID 설정

## 실행 방법
```bash
./gradlew bootRun
```

## 개발 모드 실행
```bash
./gradlew bootRun --args='--spring.profiles.active=dev'
```

## 테스트
```bash
./gradlew test
```

## 라이선스
이 프로젝트는 MIT 라이선스를 따릅니다. 