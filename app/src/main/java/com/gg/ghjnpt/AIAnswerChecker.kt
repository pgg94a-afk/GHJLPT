package com.gg.ghjnpt

import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.runBlocking

data class AnswerEvaluation(
    val accuracy: Int,
    val reason: String,
    val example: String
)

object AIAnswerChecker {
    private const val API_KEY = "AIzaSyCz_x5VItAymM1KK0vFDbF7rHcwJRGhNAM" // TODO: 실제 API 키로 교체 필요

    private val model = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = API_KEY
    )

    fun evaluateAnswer(
        japaneseWord: String,
        correctMeaning: String,
        userAnswer: String
    ): AnswerEvaluation {
        return runBlocking {
            try {
                val prompt = """
                    일본어 단어: $japaneseWord
                    정답: $correctMeaning
                    사용자 답변: $userAnswer

                    위 일본어 단어의 정답과 사용자의 답변을 비교하여 다음 형식으로 평가해주세요:

                    정확도: [0-100 사이의 숫자만]
                    이유: [한 문장으로 간단히]
                    예문: [$japaneseWord 를 사용한 간단한 일본어 예문 한 개 (한글 해석 포함)]

                    형식을 정확히 지켜서 응답해주세요.
                """.trimIndent()

                val response = model.generateContent(prompt)
                val text = response.text ?: ""

                parseEvaluation(text)
            } catch (e: Exception) {
                AnswerEvaluation(
                    accuracy = 0,
                    reason = "AI 평가 중 오류가 발생했습니다: ${e.message}",
                    example = ""
                )
            }
        }
    }

    private fun parseEvaluation(response: String): AnswerEvaluation {
        val lines = response.lines().map { it.trim() }

        var accuracy = 0
        var reason = ""
        var example = ""

        for (line in lines) {
            when {
                line.startsWith("정확도:") -> {
                    val accuracyStr = line.substringAfter("정확도:").trim()
                    accuracy = accuracyStr.replace(Regex("[^0-9]"), "").toIntOrNull() ?: 0
                }
                line.startsWith("이유:") -> {
                    reason = line.substringAfter("이유:").trim()
                }
                line.startsWith("예문:") -> {
                    example = line.substringAfter("예문:").trim()
                }
            }
        }

        return AnswerEvaluation(
            accuracy = accuracy.coerceIn(0, 100),
            reason = reason.ifEmpty { "평가를 가져올 수 없습니다." },
            example = example.ifEmpty { "예문을 가져올 수 없습니다." }
        )
    }
}
