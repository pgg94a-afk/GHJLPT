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