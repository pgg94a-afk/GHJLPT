import com.gg.ghjnpt.AIAnswerChecker
import com.gg.ghjnpt.data.Conjunction
import com.gg.ghjnpt.data.ConjunctionData
import com.gg.ghjnpt.data.Grammar
import com.gg.ghjnpt.data.GrammarData
import com.gg.ghjnpt.data.JPWord
import com.gg.ghjnpt.data.JPWordData

fun main() {
    println("=".repeat(50))
    println("일본어 학습 프로그램")
    println("=".repeat(50))

    println("\n학습 유형을 선택하세요:")
    println("1. 문법 학습")
    println("2. 단어 학습")
    println("3. 접속사 학습")  // 추가
    print("선택 (1, 2 또는 3): ")

    val studyType = readLine()?.trim()

    when (studyType) {
        "1" -> grammarStudy()
        "2" -> wordStudy()
        "3" -> conjunctionStudy()  // 추가
        else -> {
            println("잘못된 입력입니다. 프로그램을 종료합니다.")
            return
        }
    }
}


fun grammarStudy() {
    // 모드 선택
    println("\n모드를 선택하세요:")
    println("1. 암기 모드")
    println("2. 퀴즈 모드")
    print("선택 (1 또는 2): ")

    val mode = readLine()?.trim()

    when (mode) {
        "1" -> grammarMemorizeMode()
        "2" -> grammarQuizMode()
        else -> {
            println("잘못된 입력입니다.")
            return
        }
    }
}

fun wordStudy() {
    // 모드 선택
    println("\n모드를 선택하세요:")
    println("1. 암기 모드")
    println("2. 퀴즈 모드")
    print("선택 (1 또는 2): ")

    val mode = readLine()?.trim()

    when (mode) {
        "1" -> wordMemorizeMode()
        "2" -> wordQuizMode()
        else -> {
            println("잘못된 입력입니다.")
            return
        }
    }
}

fun selectGrammarGroups(): Map<Int, List<Grammar>> {
    val allGrammars = mapOf(
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

    println("\n문법 그룹을 선택하세요:")
    println("0. 전체 선택 (1~10)")
    for (i in 1..10) {
        println("$i. Grammar $i")
    }
    print("선택 (0 또는 1-10, 여러 개는 쉼표로 구분): ")

    val input = readLine()?.trim() ?: "0"

    return if (input == "0") {
        allGrammars
    } else {
        val selectedIndices = input.split(",").mapNotNull {
            it.trim().toIntOrNull()?.takeIf { index -> index in 1..10 }
        }
        allGrammars.filterKeys { it in selectedIndices }
    }
}

fun grammarMemorizeMode() {
    println("\n📚 문법 암기 모드를 시작합니다.")

    val selectedGrammars = selectGrammarGroups()

    if (selectedGrammars.isEmpty()) {
        println("선택된 문법이 없습니다.")
        return
    }

    val totalCount = selectedGrammars.values.sumOf { it.size }
    println("\n총 ${totalCount}개의 문법을 표시합니다.")
    println("=".repeat(50))

    selectedGrammars.toSortedMap().forEach { (grammarNum, grammars) ->
        println("\n${grammarNum}과 ${"―".repeat(43)}")

        grammars.forEachIndexed { index, grammar ->
            println("\n${(index + 1).toString().padStart(3, ' ')}. ${grammar.japaneseGrammar}")
            println("     접속: ${grammar.connection}")
            println("     의미: ${grammar.meaning}")
            println("     히라가나: ${grammar.hiragana}")
            println("     한글발음: ${grammar.koreanPronounce}")
            println("     " + "-".repeat(45))
        }
    }

    println("\n" + "=".repeat(50))
    println("암기 모드를 종료합니다.")
}

fun grammarQuizMode() {
    println("\n✏️ 문법 퀴즈 모드를 시작합니다.")

    val selectedGrammars = selectGrammarGroups()

    if (selectedGrammars.isEmpty()) {
        println("선택된 문법이 없습니다.")
        return
    }

    val grammars = selectedGrammars.values.flatten()
    val corrects = mutableListOf<Grammar>()
    val wrongs = mutableListOf<Grammar>()
    val randomGrammars = grammars.shuffled()

    println("\n총 ${randomGrammars.size}개의 문제가 출제됩니다.")
    println("=".repeat(50))

    randomGrammars.forEachIndexed { id, grammar ->
        val index = (id + 1).toString().padStart(2, '0')
        println("\n[$index] ${grammar.japaneseGrammar}")
        print("의미를 입력하세요: ")

        val answer = readLine()?.trim()
        if (answer == grammar.meaning) {
            println("✅ 정답! (${grammar.connection} : ${grammar.meaning}: ${grammar.hiragana}, ${grammar.koreanPronounce})")
            corrects.add(grammar)
        } else {
            println("❌ 오답! 정답은 (${grammar.connection} : ${grammar.meaning}: ${grammar.hiragana}, ${grammar.koreanPronounce})")
            wrongs.add(grammar)
        }
    }

    println("\n" + "=".repeat(50))
    println("📊 퀴즈 결과")
    println("=".repeat(50))
    println("총 문제 수: ${randomGrammars.size}")
    println("정답 수: ${corrects.size}")
    println("오답 수: ${wrongs.size}")
    println("정답률: ${String.format("%.1f", (corrects.size.toFloat() / randomGrammars.size.toFloat()) * 100)}%")

    if (wrongs.isNotEmpty()) {
        println("\n👻 오답노트 👻")
        println("-".repeat(50))
        wrongs.forEach {
            println("${it.japaneseGrammar}")
            println("  ➜ ${it.connection} : ${it.meaning} : ${it.hiragana}, ${it.koreanPronounce}")
        }
    }

    println("\n" + "=".repeat(50))
}

fun selectWordGroups(): Map<Int, List<JPWord>> {
    val allWords = mapOf(
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
    )

    println("\n단어 그룹을 선택하세요:")
    println("0. 전체 선택 (1~14)")
    for (i in 1..14) {
        println("$i. JPWord $i")
    }
    print("선택 (0 또는 1-14, 여러 개는 쉼표로 구분): ")

    val input = readLine()?.trim() ?: "0"

    return if (input == "0") {
        allWords
    } else {
        val selectedIndices = input.split(",").mapNotNull {
            it.trim().toIntOrNull()?.takeIf { index -> index in 1..14 }
        }
        allWords.filterKeys { it in selectedIndices }
    }
}

fun wordMemorizeMode() {
    println("\n📚 단어 암기 모드를 시작합니다.")

    val selectedWords = selectWordGroups()

    if (selectedWords.isEmpty()) {
        println("선택된 단어 그룹이 없습니다.")
        return
    }

    val totalCount = selectedWords.values.sumOf { it.size }
    println("\n총 ${totalCount}개의 단어를 표시합니다.")
    println("=".repeat(50))

    selectedWords.toSortedMap().forEach { (wordNum, words) ->
        println("\n${wordNum}과 ${"―".repeat(43)}")

        words.forEachIndexed { index, word ->
            println("\n${(index + 1).toString().padStart(3, ' ')}. ${word.word}")
            println("     히라가나: ${word.kana}")
            println("     뜻: ${word.meaning}")
            println("     한글발음: ${word.koreanPronounce}")
            println("     " + "-".repeat(45))
        }
    }

    println("\n" + "=".repeat(50))
    println("암기 모드를 종료합니다.")
}

fun wordQuizMode() {
    println("\n✏️ 단어 퀴즈 모드를 시작합니다.")

    val selectedWords = selectWordGroups()

    if (selectedWords.isEmpty()) {
        println("선택된 단어 그룹이 없습니다.")
        return
    }

    // 정답 체크 방식 선택
    println("\n정답 체크 방식을 선택하세요:")
    println("1. AI 정답 체크 (유사한 답변도 인정)")
    println("2. 표준 형식 (히라가나 한글발음 - 뜻)")
    print("선택 (1 또는 2): ")

    val answerCheckMode = readLine()?.trim() ?: "1"
    val useAI = answerCheckMode == "1"

    val words = selectedWords.values.flatten()
    val corrects = mutableListOf<JPWord>()
    val wrongs = mutableListOf<JPWord>()
    val randomWords = words.shuffled()

    println("\n총 ${randomWords.size}개의 문제가 출제됩니다.")
    println("=".repeat(50))

    randomWords.forEachIndexed { id, word ->
        val index = (id + 1).toString().padStart(2, '0')
        println("\n[$index] ${word.word}")
        print("뜻을 입력하세요: ")

        val answer = readLine()?.trim() ?: ""

        if (useAI) {
            // AI를 이용한 답변 평가
            println("\n🤖 AI가 답변을 평가하는 중...")
            val evaluation = AIAnswerChecker.evaluateAnswer(
                japaneseWord = word.word,
                correctMeaning = word.meaning,
                userAnswer = answer
            )

            println("\n📊 평가 결과:")
            println("  정확도: ${evaluation.accuracy}%")
            println("  이유: ${evaluation.reason}")
            println("  예문: ${evaluation.example}")
            println("  정답: ${word.kana} ${word.koreanPronounce} - ${word.meaning}")

            // 80% 이상이면 정답으로 인정
            if (evaluation.accuracy >= 80) {
                println("✅ 정답으로 인정합니다!")
                corrects.add(word)
            } else {
                println("❌ 오답입니다.")
                wrongs.add(word)
            }
        } else {
            // 표준 형식: "히라가나 한글발음 - 뜻"
            val correctAnswer = "${word.koreanPronounce} - ${word.meaning}"
            if (answer == correctAnswer) {
                println("✅ 정답! :: ${word.word}: ${word.meaning} ${word.kana} ${word.koreanPronounce}")
                corrects.add(word)
            } else {
                println("❌ 오답! :: ${word.word}: ${word.meaning} ${word.kana} ${word.koreanPronounce}")
                wrongs.add(word)
            }
        }
    }

    println("\n" + "=".repeat(50))
    println("📊 퀴즈 결과")
    println("=".repeat(50))
    println("총 문제 수: ${randomWords.size}")
    println("정답 수: ${corrects.size}")
    println("오답 수: ${wrongs.size}")
    println("정답률: ${String.format("%.1f", (corrects.size.toFloat() / randomWords.size.toFloat()) * 100)}%")

    if (wrongs.isNotEmpty()) {
        println("\n👻 오답노트 👻")
        println("-".repeat(50))
        wrongs.forEach {
            println("${it.word}")
            println("  ➜ ${it.kana} : ${it.koreanPronounce} : ${it.meaning}")
        }
    }

    println("\n" + "=".repeat(50))
}

fun conjunctionStudy() {
    println("\n모드를 선택하세요:")
    println("1. 암기 모드")
    println("2. 퀴즈 모드")
    print("선택 (1 또는 2): ")

    val mode = readLine()?.trim()

    when (mode) {
        "1" -> conjunctionMemorizeMode()
        "2" -> conjunctionQuizMode()
        else -> {
            println("잘못된 입력입니다.")
            return
        }
    }
}

fun selectConjunctionGroups(): Map<String, List<Conjunction>> {
    val allConjunctions = mapOf(
        "N3_순접추가" to ConjunctionData.N3_Sequential,
        "N3_역접대조" to ConjunctionData.N3_Contradictory,
        "N3_이유원인" to ConjunctionData.N3_Reason,
        "N3_전환조건" to ConjunctionData.N3_Transition,
        "N4_순접추가" to ConjunctionData.N4_Sequential,
        "N4_역접" to ConjunctionData.N4_Contradictory,
        "N4_이유원인" to ConjunctionData.N4_Reason,
        "N4_전환조건" to ConjunctionData.N4_Transition,
    )

    println("\n접속사 그룹을 선택하세요:")
    println("0. 전체 선택")
    println("1. N3 전체")
    println("2. N4 전체")
    println("3. N3 순접·추가")
    println("4. N3 역접·대조")
    println("5. N3 이유·원인")
    println("6. N3 전환·조건")
    println("7. N4 순접·추가")
    println("8. N4 역접")
    println("9. N4 이유·원인")
    println("10. N4 전환·조건")
    print("선택 (0-10, 여러 개는 쉼표로 구분): ")

    val input = readLine()?.trim() ?: "0"

    val selectionMap = mapOf(
        "0" to allConjunctions.keys.toList(),
        "1" to listOf("N3_순접추가", "N3_역접대조", "N3_이유원인", "N3_전환조건"),
        "2" to listOf("N4_순접추가", "N4_역접", "N4_이유원인", "N4_전환조건"),
        "3" to listOf("N3_순접추가"),
        "4" to listOf("N3_역접대조"),
        "5" to listOf("N3_이유원인"),
        "6" to listOf("N3_전환조건"),
        "7" to listOf("N4_순접추가"),
        "8" to listOf("N4_역접"),
        "9" to listOf("N4_이유원인"),
        "10" to listOf("N4_전환조건"),
    )

    val selectedKeys = input.split(",")
        .flatMap { selectionMap[it.trim()] ?: emptyList() }
        .distinct()

    return allConjunctions.filterKeys { it in selectedKeys }
}

fun conjunctionMemorizeMode() {
    println("\n📚 접속사 암기 모드를 시작합니다.")

    val selectedConjunctions = selectConjunctionGroups()

    if (selectedConjunctions.isEmpty()) {
        println("선택된 접속사가 없습니다.")
        return
    }

    val totalCount = selectedConjunctions.values.sumOf { it.size }
    println("\n총 ${totalCount}개의 접속사를 표시합니다.")
    println("=".repeat(50))

    selectedConjunctions.forEach { (groupName, conjunctions) ->
        println("\n【$groupName】 ${"―".repeat(35)}")

        conjunctions.forEachIndexed { index, conj ->
            println("\n${(index + 1).toString().padStart(3, ' ')}. ${conj.japanese}")
            println("     뜻: ${conj.meaning}")
            println("     설명: ${conj.description}")
            println("     분류: ${conj.category}")
            println("     " + "-".repeat(45))
        }
    }

    println("\n" + "=".repeat(50))
    println("암기 모드를 종료합니다.")
}

fun conjunctionQuizMode() {
    println("\n✏️ 접속사 퀴즈 모드를 시작합니다.")

    val selectedConjunctions = selectConjunctionGroups()

    if (selectedConjunctions.isEmpty()) {
        println("선택된 접속사가 없습니다.")
        return
    }

    // 퀴즈 유형 선택
    println("\n퀴즈 유형을 선택하세요:")
    println("1. 일본어 → 한국어 뜻")
    println("2. 한국어 뜻 → 일본어")
    print("선택 (1 또는 2): ")

    val quizType = readLine()?.trim() ?: "1"

    val conjunctions = selectedConjunctions.values.flatten()
    val corrects = mutableListOf<Conjunction>()
    val wrongs = mutableListOf<Conjunction>()
    val randomConjunctions = conjunctions.shuffled()

    println("\n총 ${randomConjunctions.size}개의 문제가 출제됩니다.")
    println("=".repeat(50))

    randomConjunctions.forEachIndexed { id, conj ->
        val index = (id + 1).toString().padStart(2, '0')

        val (question, correctAnswer) = when (quizType) {
            "2" -> conj.meaning to conj.japanese
            else -> conj.japanese to conj.meaning
        }

        println("\n[$index] $question")
        print("정답을 입력하세요: ")

        val answer = readLine()?.trim() ?: ""

        // AI를 이용한 답변 평가
        println("\n🤖 AI가 답변을 평가하는 중...")
        val evaluation = AIAnswerChecker.evaluateConjunctionAnswer(
            japaneseConjunction = conj.japanese,
            correctMeaning = conj.meaning,
            description = conj.description,
            userAnswer = answer
        )

        println("\n📊 평가 결과:")
        println("  정확도: ${evaluation.accuracy}%")
        println("  이유: ${evaluation.reason}")
        println("  정답: ${conj.japanese} : ${conj.meaning}")
        println("  💡 ${conj.description}")
        println("\n📖 예문:")
        println("  ${evaluation.example}")

        // 80% 이상이면 정답으로 인정
        if (evaluation.accuracy >= 80) {
            println("\n✅ 정답으로 인정합니다!")
            corrects.add(conj)
        } else {
            println("\n❌ 오답입니다.")
            wrongs.add(conj)
        }
    }

    println("\n" + "=".repeat(50))
    println("📊 퀴즈 결과")
    println("=".repeat(50))
    println("총 문제 수: ${randomConjunctions.size}")
    println("정답 수: ${corrects.size}")
    println("오답 수: ${wrongs.size}")
    println("정답률: ${String.format("%.1f", (corrects.size.toFloat() / randomConjunctions.size.toFloat()) * 100)}%")

    if (wrongs.isNotEmpty()) {
        println("\n👻 오답노트 👻")
        println("-".repeat(50))
        wrongs.forEach {
            println("${it.japanese}")
            println("  ➜ ${it.meaning}")
            println("  💡 ${it.description}")
        }
    }

    println("\n" + "=".repeat(50))
}