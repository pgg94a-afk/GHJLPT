package com.gg.ghjnpt

import java.net.HttpURLConnection
import java.net.URL

data class AnswerEvaluation(
    val accuracy: Int,
    val reason: String,
    val example: String
)

object AIAnswerChecker {
    private val API_KEY = BuildConfig.GEMINI_API_KEY
    private const val API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent"



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
                예문: [$japaneseWord 를 사용한 간단한 일본어 예문 한 개 (한글 해석 포함)]

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
