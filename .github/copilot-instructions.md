# GCS 안드로이드 프로젝트 - AI 코딩 가이드

## 프로젝트 개요
Kotlin, Material Design 3, XML 레이아웃(View 시스템, Compose 아님)을 사용하는 기본 안드로이드 애플리케이션입니다.
- **패키지**: `com.example.gcs`
- **최소 SDK**: 24 (Android 7.0) | **타겟 SDK**: 34 (Android 14)
- **빌드**: Gradle with Kotlin DSL

## 아키텍처 및 구조
- **단일 액티비티**: [MainActivity.kt](app/src/main/java/com/example/gcs/MainActivity.kt) - 전통적인 View 기반 레이아웃을 사용하는 진입점
- **UI**: `app/src/main/res/layout/`의 XML 레이아웃, ConstraintLayout + Material Design 3 테마 사용
- **테마**: [themes.xml](app/src/main/res/values/themes.xml)의 Material3.DayNight.NoActionBar

## Gradle 빌드 시스템
- **루트**: [build.gradle.kts](build.gradle.kts) - 플러그인 버전 (AGP 8.2.0, Kotlin 1.9.20)
- **앱 모듈**: [app/build.gradle.kts](app/build.gradle.kts) - 의존성 및 SDK 설정
- **설정**: [settings.gradle.kts](settings.gradle.kts) - `FAIL_ON_PROJECT_REPOS` 설정된 저장소 구성

### 주요 의존성
```kotlin
implementation("androidx.core:core-ktx:1.12.0")
implementation("com.google.android.material:material:1.11.0")
implementation("androidx.constraintlayout:constraintlayout:2.1.4")
```

## 개발 워크플로우
Windows에서 PowerShell을 사용한 빌드 및 테스트 명령어:

```powershell
# 디버그 APK 빌드
.\gradlew assembleDebug

# 단위 테스트 실행
.\gradlew test

# 계측 테스트 실행 (기기/에뮬레이터 필요)
.\gradlew connectedAndroidTest

# 린트 검사
.\gradlew lint

# 클린 빌드
.\gradlew clean
```

## 코딩 규칙
- **Kotlin**: 표준 Kotlin 규칙 준수; Java 상호운용이 필요한 경우가 아니면 Kotlin 사용
- **리소스**: 레이아웃/드로어블은 `snake_case` 사용 (예: `activity_main.xml`)
- **XML 레이아웃**: 기본으로 ConstraintLayout 사용; 기존 뷰 계층 패턴 유지
- **액티비티**: [AndroidManifest.xml](app/src/main/AndroidManifest.xml)에 등록하며 적절한 intent-filter 설정
- **문자열**: 사용자 표시 텍스트는 [strings.xml](app/src/main/res/values/strings.xml)에 외부화

## 기능 추가 시 작업 순서
1. 새 액티비티: 매니페스트에 추가하며 가시성에 따라 `android:exported` 속성 설정
2. UI 변경: XML 레이아웃 먼저 수정 후, Kotlin에서 `findViewById()` 또는 뷰 바인딩으로 참조
3. 의존성: [app/build.gradle.kts](app/build.gradle.kts)에 추가 후 Gradle 동기화
4. 리소스: Material Design 3 가이드라인 준수; 색상은 테마 속성 사용

## 중요 사항
- Jetpack Compose 없음 - 전통적인 XML View 시스템 사용
- ActionBar 없는 Material 3 테마 - 필요 시 레이아웃에 Toolbar 사용
- JVM 타겟은 1.8 (Java 8 호환)
- 디버그 빌드에서 ProGuard 비활성화; 릴리즈 시 최적화 검토 필요
