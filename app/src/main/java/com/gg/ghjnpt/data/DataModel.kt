package com.gg.ghjnpt.data

data class JPWord(
    val word: String,           // 일본어 단어
    val kana: String,           // 히라가나
    val meaning: String,        // 한국어 뜻
    val koreanPronounce: String // 한글 발음
)

data class Grammar(
    val japaneseGrammar: String,  // '~일본어' (문법 패턴)
    val connection: String,       // 접속형태
    val meaning: String,          // 뜻
    val hiragana: String,         // 히라가나
    val koreanPronounce: String   // 한글발음
)

data class Conjunction(
    val japanese: String,      // 일본어 접속사
    val meaning: String,       // 한국어 뜻
    val description: String,   // 설명
    val category: String       // 카테고리 (순접, 역접 등)
)

data class KeigoExpression(
    val japanese: String,       // 일본어 (예: 参ります)
    val meaning: String,        // 뜻 (예: 가겠습니다)
    val hiragana: String,       // 히라가나 (예: まいります)
    val koreanPronounce: String,// 한글 발음 (예: 마이리마스)
    val type: String            // 형태 (예: 겸양어, 존경어, 수혜형, 기본형)
)