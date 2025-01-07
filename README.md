- 1.로컬 swagger 어플리케이션 구성  
- 2.git 레포지토리 푸시  
- 3.jenkins-git 레포지토리 연계 및 빌드  
- 4.kt클라우드 내 어플리케이션 구성  
- 5.docker 이미지 생성 및 빌드  
- 6.docker 레포지토리 연계  
- 7.로컬환경에 docker 이미지 pull 및 구동  

### H2 DB JDBC URL
```
jdbc:h2:mem:testdb
```

### GitHub 연동 및 Jenkins 설정
```bash
# Git 초기화
git init

# 프로젝트 파일 추가
git add .

# 커밋 메시지 작성
git commit -m "Initial commit"

# 원격 저장소 추가
git remote add origin https://github.com/jspark8707/swagger.git

# warning: in the working copy of '.gitattributes', LF will be replaced by CRLF the next time Git touches it 
git config --global core.autocrlf false

# GitHub에 로그인하고, 우측 상단의 New 버튼을 클릭합니다.
# 저장소 이름을 swagger로 설정하고 Create repository를 클릭합니다.
# 브랜치 기본값 설정
git branch -M main

# 원격 저장소로 Push
git push -u origin main

#swagger log
tail -f /var/lib/jenkins/workspace/swagger/logs/output.log

#swagger 종료
ps aux | grep 'api-0.0.1-SNAPSHOT.jar' | grep -v grep | awk '{print $2}' | xargs kill
```
### Docker 빌드 및 DockerHub 연동 
```bash
#Dockerfile 생성,푸시,빌드
cd /var/lib/jenkins/workspace/swagger
mvn clean package

#도커 빌드
docker build -t swagger-image-kt .

#도커 실행
docker run -p 8080:8080 swagger-image-kt
docker run -d -p 8080:8080 swagger-image-kt


#도커로그인 후 이미지 push
docker login -u parkjs8707@gmail.com
docker tag swagger-image-kt jongsungpark/swagger-image-kt
docker push jongsungpark/swagger-image-kt

```
### Slack 연동 
```bash
git pull origin main --rebase

#Slack 연동 후 권한문제 발생하여 jar 실행 안됨
sudo chown -R jenkins:jenkins /var/lib/jenkins/workspace/swagger
chmod -R 755 /var/lib/jenkins/workspace/swagger/target
```
### Jenkins-Slack 연동 설정가이드
- Jenkins 전역변수 부분
![image](https://github.com/user-attachments/assets/573489c5-873d-4ec6-8641-0ab7153733c9)
![image](https://github.com/user-attachments/assets/806dc4f2-0926-4a37-8d83-a10983839720)

- https://thejspark.slack.com/services/B085XR40N95?added=1

### 🔑Jenkins 암호 확인
```bash
sudo cat /var/lib/jenkins/secrets/initialAdminPassword
```
- id : jspark
