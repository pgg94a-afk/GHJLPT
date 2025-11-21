# GHJLPT - JLPT N3 학습 애플리케이션

JLPT N3 수준 합격을 위한 문법과 단어를 효과적으로 학습할 수 있는 Android 애플리케이션입니다.

## 📱 주요 기능

### 1. 단어 학습 (700+ 단어)
- **14개 레벨**: 5일 학습 과정으로 구성
  - 1일차 (레벨 1-2): 76단어
  - 2일차 (레벨 3-5): 126단어
  - 3일차 (레벨 6-8): 152단어
  - 4일차 (레벨 9-11): 156단어
  - 5일차 (레벨 12-14): 154단어

- **학습 모드**:
  - 📚 **암기 모드**: 플래시카드 방식으로 단어 암기
  - 📝 **퀴즈 모드**: 한국어 발음 입력으로 학습 확인
  - 🔖 **어려운 단어 표시**: 어려운 단어를 따로 표시하고 필터링
  - 🎲 **랜덤 셔플**: 문제 순서 무작위 배치

### 2. 문법 학습 (60+ 문법 패턴)
- **14개 레벨**: 체계적인 문법 학습
- **문법 정보**:
  - 문법 패턴 (예: ~さえ~ば, ~といった)
  - 접속 형태
  - 한국어 의미
  - 히라가나 표기
  - 한국어 발음

- **학습 모드**:
  - 📚 **암기 모드**: 문법 패턴 플래시카드
  - 🔖 **어려운 문법 표시**: 복습이 필요한 문법 마킹

### 3. 퀴즈 기능
- ✅ **정답/오답 실시간 피드백**: Lottie 애니메이션으로 시각적 표시
- 📊 **진행률 표시**: 현재 문제 번호와 전체 진행 상황
- 📈 **점수 집계**: 정답률 계산 및 표시
- 📋 **오답노트**: 틀린 문제 복습 기능

## 🛠️ 기술 스택

- **언어**: Kotlin 100%
- **플랫폼**: Android (minSdk 26, targetSdk 35)
- **UI 프레임워크**: Jetpack Compose
- **아키텍처**: MVVM 패턴
- **네비게이션**: Jetpack Navigation Compose
- **애니메이션**: Lottie
- **빌드 시스템**: Gradle with Kotlin DSL
- **JVM**: Java 17

## 📂 프로젝트 구조

```
GHJLPT/
├── app/
│   └── src/
│       └── main/
│           ├── java/com/gg/ghjnpt/
│           │   ├── MainActivity.kt          # 메인 UI (Jetpack Compose)
│           │   ├── QuizViewModel.kt         # 상태 관리
│           │   ├── test.kt                  # CLI 테스트 도구
│           │   ├── data/
│           │   │   ├── DataModel.kt         # 데이터 모델 (JPWord, Grammar)
│           │   │   ├── JPWordData.kt        # 700+ 단어 데이터
│           │   │   └── GrammarData.kt       # 60+ 문법 데이터
│           │   └── ui/theme/                # 테마 및 스타일
│           └── res/
│               └── raw/                      # Lottie 애니메이션
├── build.gradle.kts
└── settings.gradle.kts
```

## 🎨 데이터 모델

### JPWord (단어)
```kotlin
data class JPWord(
    val word: String,               // 일본어 단어 (예: "連想する")
    val kana: String,               // 히라가나 (예: "れんそうする")
    val meaning: String,            // 한국어 의미 (예: "연상하다")
    val koreanPronounce: String    // 한국어 발음 (예: "렌소우스루")
)
```

### Grammar (문법)
```kotlin
data class Grammar(
    val japaneseGrammar: String,    // 문법 패턴 (예: "~さえ~ば")
    val connection: String,         // 접속 형태
    val meaning: String,            // 한국어 의미
    val hiragana: String,           // 히라가나 표기
    val koreanPronounce: String    // 한국어 발음
)
```

## 🚀 시작하기

### 요구사항
- Android Studio (최신 버전 권장)
- JDK 17 이상
- Android SDK (minSdk 26)

### 설치 및 실행

1. 저장소 클론
```bash
git clone https://github.com/pgg94a-afk/GHJLPT.git
cd GHJLPT
```

2. Android Studio에서 프로젝트 열기

3. Gradle 동기화 대기

4. 에뮬레이터 또는 실제 기기에서 실행

### CLI 테스트 도구 사용

```bash
# test.kt를 사용한 콘솔 학습 (개발/테스트용)
# MainActivity에서 사용하거나 별도로 실행 가능
```

## 🎯 사용 방법

1. **메인 화면**: "단어" 또는 "문법" 탭 선택
2. **레벨 선택**: 학습하고 싶은 레벨 체크박스 선택
3. **모드 선택**:
   - **문제 풀기**: 퀴즈 모드로 실력 테스트
   - **암기 모드**: 플래시카드로 암기 학습
4. **학습 진행**:
   - 암기 모드: 카드를 탭하여 내용 확인
   - 퀴즈 모드: 한국어 발음 입력 후 확인
5. **복습**: 어려운 단어/문법 표시 후 필터링하여 집중 학습

## 🎨 테마 색상

애플리케이션은 "YongdalBlue" 커스텀 테마를 사용합니다:
- 메인 블루: `#0D47A1`
- 라이트 액센트: `#42A5F5`
- 다크 변형: `#01579B`
- 배경: `#E3F2FD`

## 📊 학습 통계

| 항목 | 수량 |
|------|------|
| 단어 레벨 | 14개 |
| 총 단어 수 | 700+ |
| 문법 레벨 | 14개 |
| 총 문법 패턴 | 60+ |
| UI 화면 | 4개 |

## 🤝 기여하기

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 라이선스

이 프로젝트는 개인 학습 목적으로 개발되었습니다.

## 📞 연락처

프로젝트 링크: [https://github.com/pgg94a-afk/GHJLPT](https://github.com/pgg94a-afk/GHJLPT)

---

**JLPT N3 합격을 향해 함께 달려봅시다! 🎌📚**
