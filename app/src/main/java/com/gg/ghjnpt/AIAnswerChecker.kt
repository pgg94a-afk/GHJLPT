package com.gg.ghjnpt

import java.net.HttpURLConnection
import java.net.URL

data class AnswerEvaluation(
    val accuracy: Int,
    val reason: String,
    val example: String
)

data class ExampleSentence(
    val sentence: String,
    val correctMeaning: String
)

object AIAnswerChecker {
    private val API_KEY = BuildConfig.GEMINI_API_KEY
    private const val API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent"





    fun evaluateAnswer(
        japaneseWord: String,
        correctMeaning: String,
        userAnswer: String
    ): AnswerEvaluation {
        return try {
            val prompt = """
                일본어 단어: $japaneseWord
                정답: $correctMeaning
                사용자 답변: $userAnswer

                위 일본어 단어의 정답과 사용자의 답변을 비교하여 다음 형식으로 평가해주세요:

                정확도: [0-100 사이의 숫자만]
                이유: [한 문장으로 간단히]
                예문: [$japaneseWord 를 사용한 JLPT N3 수준의 간단한 일본어 예문 한 개]
                한글발음: [위 예문의 한글 발음]
                해석: [위 예문의 한글 해석]

                형식을 정확히 지켜서 응답해주세요.
            """.trimIndent()

            val response = callGeminiAPI(prompt)
            parseEvaluation(response)
        } catch (e: Exception) {
            AnswerEvaluation(
                accuracy = 0,
                reason = "AI 평가 중 오류가 발생했습니다: ${e.message}",
                example = ""
            )
        }
    }

    fun generateExampleSentence(
        japaneseWord: String,
        correctMeaning: String
    ): ExampleSentence {
        return try {
            val prompt = """
                일본어 단어: $japaneseWord
                뜻: $correctMeaning

                위 일본어 단어를 사용하여 JLPT N3 수준의 간단한 일본어 예문을 하나 만들어주세요.
                예문은 일본어로만 작성하고, 한글 발음이나 해석은 포함하지 마세요.

                다음 형식으로 응답해주세요:
                예문: [일본어 예문]
                정답해석: [예문의 한글 해석]

                형식을 정확히 지켜서 응답해주세요.
            """.trimIndent()

            val response = callGeminiAPI(prompt)
            parseExampleSentence(response)
        } catch (e: Exception) {
            ExampleSentence(
                sentence = "예문 생성 중 오류가 발생했습니다: ${e.message}",
                correctMeaning = ""
            )
        }
    }

    fun evaluateExampleInterpretation(
        exampleSentence: String,
        correctMeaning: String,
        userInterpretation: String
    ): AnswerEvaluation {
        return try {
            val prompt = """
                예문: $exampleSentence
                정답 해석: $correctMeaning
                사용자 해석: $userInterpretation

                위 일본어 예문에 대한 사용자의 해석이 정답과 얼마나 일치하는지 평가해주세요.
                의미가 같으면 표현이 다르더라도 높은 점수를 주세요.

                다음 형식으로 평가해주세요:
                정확도: [0-100 사이의 숫자만]
                이유: [한 문장으로 간단히]

                형식을 정확히 지켜서 응답해주세요.
            """.trimIndent()

            val response = callGeminiAPI(prompt)
            parseInterpretationEvaluation(response)
        } catch (e: Exception) {
            AnswerEvaluation(
                accuracy = 0,
                reason = "AI 평가 중 오류가 발생했습니다: ${e.message}",
                example = ""
            )
        }
    }

    fun evaluateConjunctionAnswer(
        japaneseConjunction: String,
        correctMeaning: String,
        description: String,
        userAnswer: String
    ): AnswerEvaluation {
        return try {
            val prompt = """
                일본어 접속사: $japaneseConjunction
                정답: $correctMeaning
                설명: $description
                사용자 답변: $userAnswer

                위 일본어 접속사의 정답과 사용자의 답변을 비교하여 다음 형식으로 평가해주세요:

                정확도: [0-100 사이의 숫자만]
                이유: [한 문장으로 간단히]
                예문: [$japaneseConjunction 를 사용한 JLPT N3 수준의 간단한 일본어 예문 한 개 (앞뒤 문장 포함)]
                한글발음: [위 예문의 한글 발음]
                해석: [위 예문의 한글 해석]

                형식을 정확히 지켜서 응답해주세요.
            """.trimIndent()

            val response = callGeminiAPI(prompt)
            parseEvaluation(response)
        } catch (e: Exception) {
            AnswerEvaluation(
                accuracy = 0,
                reason = "AI 평가 중 오류가 발생했습니다: ${e.message}",
                example = ""
            )
        }
    }

    private fun callGeminiAPI(prompt: String): String {
        val url = URL("$API_URL?key=$API_KEY")
        val connection = url.openConnection() as HttpURLConnection

        try {
            connection.requestMethod = "POST"
            connection.setRequestProperty("Content-Type", "application/json")
            connection.doOutput = true

            // Create request body manually (no JSON library needed)
            val escapedPrompt = prompt.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t")

            val requestBody = """
                {
                    "contents": [{
                        "parts": [{
                            "text": "$escapedPrompt"
                        }]
                    }]
                }
            """.trimIndent()

            // Send request
            connection.outputStream.use { os ->
                os.write(requestBody.toByteArray(Charsets.UTF_8))
            }

            // Read response
            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val responseText = connection.inputStream.bufferedReader().use { it.readText() }

                // Simple JSON parsing to extract text
                return extractTextFromResponse(responseText)
            } else {
                val errorText = connection.errorStream?.bufferedReader()?.use { it.readText() } ?: "Unknown error"
                throw Exception("API call failed with response code: $responseCode, error: $errorText")
            }
        } finally {
            connection.disconnect()
        }
    }

    private fun extractTextFromResponse(json: String): String {
        // Simple regex-based extraction (works without JSON library)
        val textPattern = """"text"\s*:\s*"((?:[^"\\]|\\.)*)"""".toRegex()
        val match = textPattern.find(json)

        return match?.groupValues?.get(1)?.let { text ->
            // Unescape the JSON string
            text.replace("\\n", "\n")
                .replace("\\r", "\r")
                .replace("\\t", "\t")
                .replace("\\\"", "\"")
                .replace("\\\\", "\\")
        } ?: ""
    }

    private fun parseEvaluation(response: String): AnswerEvaluation {
        val lines = response.lines().map { it.trim() }

        var accuracy = 0
        var reason = ""
        var example = ""
        var pronunciation = ""
        var translation = ""

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
                line.startsWith("한글발음:") -> {
                    pronunciation = line.substringAfter("한글발음:").trim()
                }
                line.startsWith("해석:") -> {
                    translation = line.substringAfter("해석:").trim()
                }
            }
        }

        // 예문, 한글발음, 해석을 합쳐서 하나의 문자열로 만듭니다
        val fullExample = buildString {
            if (example.isNotEmpty()) {
                append(example)
            }
            if (pronunciation.isNotEmpty()) {
                append("\n")
                append(pronunciation)
            }
            if (translation.isNotEmpty()) {
                append("\n")
                append("(")
                append(translation)
                append(")")
            }
        }

        return AnswerEvaluation(
            accuracy = accuracy.coerceIn(0, 100),
            reason = reason.ifEmpty { "평가를 가져올 수 없습니다." },
            example = fullExample.ifEmpty { "예문을 가져올 수 없습니다." }
        )
    }

    private fun parseExampleSentence(response: String): ExampleSentence {
        val lines = response.lines().map { it.trim() }

        var sentence = ""
        var correctMeaning = ""

        for (line in lines) {
            when {
                line.startsWith("예문:") -> {
                    sentence = line.substringAfter("예문:").trim()
                }
                line.startsWith("정답해석:") -> {
                    correctMeaning = line.substringAfter("정답해석:").trim()
                }
            }
        }

        return ExampleSentence(
            sentence = sentence.ifEmpty { "예문을 가져올 수 없습니다." },
            correctMeaning = correctMeaning.ifEmpty { "해석을 가져올 수 없습니다." }
        )
    }

    private fun parseInterpretationEvaluation(response: String): AnswerEvaluation {
        val lines = response.lines().map { it.trim() }

        var accuracy = 0
        var reason = ""

        for (line in lines) {
            when {
                line.startsWith("정확도:") -> {
                    val accuracyStr = line.substringAfter("정확도:").trim()
                    accuracy = accuracyStr.replace(Regex("[^0-9]"), "").toIntOrNull() ?: 0
                }
                line.startsWith("이유:") -> {
                    reason = line.substringAfter("이유:").trim()
                }
            }
        }

        return AnswerEvaluation(
            accuracy = accuracy.coerceIn(0, 100),
            reason = reason.ifEmpty { "평가를 가져올 수 없습니다." },
            example = ""
        )
    }
}
