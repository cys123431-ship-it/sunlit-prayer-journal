# APK 서명 키 관리 가이드 (Play Store 배포 전 단계 참고용)

> 현재 저장소 릴리스는 GitHub Release 배포 대상입니다.  
> Play Store 업로드는 이 프로젝트 범위에 포함하지 않습니다.

이 문서는 **로컬/CI에서 동일한 키로 서명 APK를 관리**하기 위한 정책입니다.

## 1) 키 생성(로컬)

```bash
keytool -genkeypair \
  -alias pray-release \
  -keyalg RSA -keysize 2048 -validity 10000 \
  -keystore pray-release.jks
```

권장값 예시:
- `CN=Sunlit Prayer Journal`
- `OU=Mobile Team`
- `O=Sunlit`
- `L=Seoul, ST=Seoul, C=KR`

## 2) 키 관리 원칙

- 서명키 원본(`.jks`, `.keystore`)은 절대 Git에 커밋하지 않습니다.
- 키 비밀번호는 분리 보관하고, CI에서는 GitHub Secrets를 사용합니다.
- 키 교체 시 기존 설치 앱 사용자에게는 새 키로 업데이트될 수 없음을 사전에 공지합니다.

## 3) GitHub Secrets 예시

- `RELEASE_KEY_ALIAS` : 키 별칭
- `RELEASE_KEY_PASSWORD` : 키 비밀번호
- `RELEASE_KEYSTORE_PASSWORD` : 키스토어 비밀번호
- `RELEASE_KEYSTORE_BASE64` : Base64로 인코딩한 키 파일
  - 인코딩 예: `base64 -w 0 pray-release.jks`

## 4) CI 적용 방식

워크플로우에서 아래 환경 변수를 읽어 서명 설정을 적용합니다.

- `RELEASE_KEYSTORE_FILE` (고정: `app/release-signing.keystore`)
- `RELEASE_KEY_ALIAS`
- `RELEASE_KEY_PASSWORD`
- `RELEASE_KEYSTORE_PASSWORD`

값이 전부 존재하고 키 파일이 복원되면 Release 서명이 적용됩니다.

## 5) 로컬 빌드 기준

서명값 미설정 시 기본 동작은 기존 릴리스 빌드 경로를 유지합니다.
