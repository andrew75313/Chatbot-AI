# 🧠 Chatbot AI

이 프로젝트는 Spring Boot 애플리케이션, PostgreSQL로 구성된 Chatbot AI 백엔드 서비스입니다.  
Docker Compose를 통해 손쉽게 실행할 수 있도록 설정되어 있습니다.

---

### 구성

- **chatbotai_app**: Spring Boot 기반 백엔드 애플리케이션  
  - Java 17 (JDK 17)
  - Spring Boot 3.4.4

- **chatbotai_postgresql**: PostgreSQL 데이터베이스  
  - PostgreSQL 15

---

### 환경 변수 설정

Docker Compose를 실행하기 위해 `.env` 파일이 필요합니다.

실행 전에 반드시 `chatbot.env.example` 파일을 참고해 `chatbot.env` 파일을 프로젝트 루트 디렉토리에 생성해야합니다.
