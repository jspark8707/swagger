# 1. 기본 이미지를 설정합니다.
FROM openjdk:17-jdk-slim

# 2. 어플리케이션 jar 파일을 이미지에 복사합니다.
ARG JAR_FILE=target/api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# 3. 어플리케이션 실행 포트를 설정합니다.
EXPOSE 8080

# 4. 컨테이너 시작 시 실행할 명령어를 정의합니다.
ENTRYPOINT ["java", "-jar", "app.jar"]
