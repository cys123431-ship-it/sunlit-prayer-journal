# 기도/감사 한줄 기록 앱

안드로이드 앱 이름: `sunlit-prayer-journal`

## 참고한 오픈소스/디자인

- GitHub:  
  - `Presently` (알리슨: 오픈소스 감사 저널 앱, MVVM + Room 중심)  
    https://github.com/alisonthemonster/Presently
- GitHub:  
  - `android/compose-samples` (Jetpack Compose 기본 패턴 및 Material 3 샘플)
    https://github.com/android/compose-samples
- Material Design 3 색상/테마 가이드:  
  https://developer.android.com/develop/ui/compose/designsystems/material3
- Material Color Builder(색감 실무 적용 참고):  
  https://m3.material.io/theme-builder#/custom

## 프로젝트 구성

- 구조: 한 줄 입력 + 카테고리(기도/감사) + 목록 + 삭제
- 저장: Room(로컬 DB)
- UI: Jetpack Compose + Material 3
- 디자인: 밝고 단순한 컬러(노란/피치 톤), 카드형 리스트, 최소한의 동작 버튼

## 실행 환경

- Android Studio (권장) 또는 Gradle + JDK 17
- 최소 SDK: 24

## 로컬 빌드

```bash
cd sunlit-prayer-journal
./gradlew :app:assembleRelease
```

> 현재 환경이 Java를 제공하지 않으면 `java` 실행이 불가해 즉시 빌드가 되지 않습니다.  
> JDK 17이 설치된 환경에서 다시 실행해야 APK가 생성됩니다.

## 릴리즈 APK 출력 경로

`app/build/outputs/apk/release/app-release.apk`

## GitHub Actions

- `.github/workflows/release.yml`에 `workflow_dispatch`/태그 푸시(`v*`) 기반 릴리즈 빌드 추가됨
- 빌드 완료 후 `pray-journal-release-apk` 아티팩트로 APK 업로드

