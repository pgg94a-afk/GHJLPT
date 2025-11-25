package com.gg.ghjnpt

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.gg.ghjnpt.data.JPWord
import com.gg.ghjnpt.data.JPWordData
import com.gg.ghjnpt.data.Grammar
import com.gg.ghjnpt.data.GrammarData

class QuizViewModel : ViewModel() {
    var words by mutableStateOf(listOf<JPWord>())
    var currentIndex by mutableStateOf(0)
    var userAnswer by mutableStateOf("")
    var isCorrect by mutableStateOf<Boolean?>(null)

    // ✅ 어려운 단어 리스트
    var difficultWords by mutableStateOf(setOf<JPWord>())
        private set

    // ✅ 문법 관련 데이터
    var grammars by mutableStateOf(listOf<Grammar>())
    var difficultGrammars by mutableStateOf(setOf<Grammar>())
        private set

    val total get() = words.size
    val currentWord get() = words.getOrNull(currentIndex)

    val grammarTotal get() = grammars.size
    val currentGrammar get() = grammars.getOrNull(currentIndex)

    private val dayLevelMap = mapOf(
        1 to listOf(1, 2),
        2 to listOf(3, 4, 5),
        3 to listOf(6, 7, 8),
        4 to listOf(9, 10, 11),
        5 to listOf(12, 13, 14),
        6 to listOf(15, 16, 17)
    )

    private val levelWordMap = mapOf(
        1 to JPWordData.JPWords,
        2 to JPWordData.JPWords2,
        3 to JPWordData.JPWords3,
        4 to JPWordData.JPWords4,
        5 to JPWordData.JPWords5,
        6 to JPWordData.JPWords6,
        7 to JPWordData.JPWords7,
        8 to JPWordData.JPWords8,
        9 to JPWordData.JPWords9,
        10 to JPWordData.JPWords10,
        11 to JPWordData.JPWords11,
        12 to JPWordData.JPWords12,
        13 to JPWordData.JPWords13,
        14 to JPWordData.JPWords14,
        15 to JPWordData.JPWords15,
        16 to JPWordData.JPWords16,
        17 to JPWordData.JPWords17,
    )

    // ✅ 문법 레벨 매핑
    private val levelGrammarMap = mapOf(
        1 to GrammarData.Grammars1,
        2 to GrammarData.Grammars2,
        3 to GrammarData.Grammars3,
        4 to GrammarData.Grammars4,
        5 to GrammarData.Grammars5,
        6 to GrammarData.Grammars6,
        7 to GrammarData.Grammars7,
        8 to GrammarData.Grammars8,
        9 to GrammarData.Grammars9,
        10 to GrammarData.Grammars10,
        11 to GrammarData.Grammars11,
        12 to GrammarData.Grammars12,
        13 to GrammarData.Grammars13,
        14 to GrammarData.Grammars14,
        16 to GrammarData.Grammars16,
        17 to GrammarData.Grammars17,
        18 to GrammarData.Grammars18,


    )

    fun loadSelectedWords(selectedLevels: List<Int>) {
        val selected = mutableListOf<JPWord>()
        selectedLevels.forEach { level ->
            levelWordMap[level]?.let { selected += it }
        }
        words = selected.shuffled()
        currentIndex = 0
        userAnswer = ""
        isCorrect = null
    }

    fun loadSelectedDays(selectedDays: List<Int>) {
        val selectedLevels = mutableListOf<Int>()
        selectedDays.forEach { day ->
            dayLevelMap[day]?.let { selectedLevels += it }
        }
        loadSelectedWords(selectedLevels)
    }

    // ✅ 문법 로드
    fun loadSelectedGrammars(selectedLevels: List<Int>) {
        val selected = mutableListOf<Grammar>()
        selectedLevels.forEach { level ->
            levelGrammarMap[level]?.let { selected += it }
        }
        grammars = selected.shuffled()
        currentIndex = 0
        userAnswer = ""
        isCorrect = null
    }

    fun checkAnswer() {
        val correct = currentWord?.koreanPronounce?.trim() == userAnswer.trim()
        isCorrect = correct
    }

    // ✅ 문법 답안 체크
    fun checkGrammarAnswer() {
        val correct = currentGrammar?.koreanPronounce?.trim() == userAnswer.trim()
        isCorrect = correct
    }

    fun nextQuestion() {
        if (currentIndex < total - 1) {
            currentIndex++
            userAnswer = ""
            isCorrect = null
        }
    }

    // ✅ 어려운 단어 추가/제거
    fun toggleDifficultWord(word: JPWord) {
        difficultWords = if (word in difficultWords) {
            difficultWords - word
        } else {
            difficultWords + word
        }
    }

    // ✅ 어려운 단어인지 확인
    fun isDifficult(word: JPWord): Boolean {
        return word in difficultWords
    }

    // ✅ 어려운 문법 추가/제거
    fun toggleDifficultGrammar(grammar: Grammar) {
        difficultGrammars = if (grammar in difficultGrammars) {
            difficultGrammars - grammar
        } else {
            difficultGrammars + grammar
        }
    }

    // ✅ 어려운 문법인지 확인
    fun isDifficultGrammar(grammar: Grammar): Boolean {
        return grammar in difficultGrammars
    }
}