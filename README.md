# 기도/감사 한 줄 기록 앱

- 레포지터리: `sunlit-prayer-journal`

## 앱 개요

- 한 줄로 `기도`와 `감사`를 즉시 기록할 수 있는 안드로이드 앱
- 카테고리별(기도 / 감사) 목록과 삭제 기능 제공
- 오프라인 영구 저장: `Room` + `Flow`
- 단순하고 밝은 느낌의 색상 톤(파스텔/노란빛) Material3 UI

## 참고한 GitHub 리포지터리

- [android/compose-samples](https://github.com/android/compose-samples) — 공식 Jetpack Compose 샘플 모음, 구조 및 Compose 사용 패턴 참고
- [utkarsh006/Notes-app-compose](https://github.com/utkarsh006/Notes-app-compose) — MVVM + Room 기반 노트 앱 구성 방식 참고
- [erenalpaslan/DiaryApp](https://github.com/erenalpaslan/DiaryApp) — 리스트형 기록 앱 UI/DB 흐름 참고

## UI 참고 포인트

- [Material Design 3](https://m3.material.io) + [Material 3 Theme Builder](https://m3.material.io/theme-builder)에서 색상/타이포 구성
- 심플한 카드형 항목 카드와 FAB, 칩(카테고리) 패턴을 반영해 구성

## 빌드 방법

```bash
cd sunlit-prayer-journal
./gradlew :app:assembleRelease
```

빌드 산출물

- `app/build/outputs/apk/release/app-release-unsigned.apk`

## GitHub Actions 릴리즈

- `.github/workflows/release.yml`
- `workflow_dispatch` 또는 `v*` 태그 push 시 워크플로우 실행
- 빌드 결과 APK는 GitHub Actions Artifact `pray-journal-release-apk`로 업로드

## 최소 실행 환경

- JDK 17
- Android SDK (`compileSdk 34`, `targetSdk 34`, `minSdk 24`)
