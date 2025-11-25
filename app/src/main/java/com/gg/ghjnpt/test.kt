import com.gg.ghjnpt.AIAnswerChecker
import com.gg.ghjnpt.data.Grammar
import com.gg.ghjnpt.data.GrammarData
import com.gg.ghjnpt.data.JPWord
import com.gg.ghjnpt.data.JPWordData

fun main() {
    println("=".repeat(50))
    println("일본어 학습 프로그램")
    println("=".repeat(50))

    // 학습 유형 선택
    println("\n학습 유형을 선택하세요:")
    println("1. 문법 학습")
    println("2. 단어 학습")
    print("선택 (1 또는 2): ")

    val studyType = readLine()?.trim()

    when (studyType) {
        "1" -> grammarStudy()
        "2" -> wordStudy()
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
        println("  정답: ${word.kana} : ${word.meaning} : ${word.koreanPronounce}")

        // 80% 이상이면 정답으로 인정
        if (evaluation.accuracy >= 80) {
            println("✅ 정답으로 인정합니다!")
            corrects.add(word)
        } else {
            println("❌ 오답입니다.")
            wrongs.add(word)
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
            println("  ➜ ${it.kana} : ${it.meaning} : ${it.koreanPronounce}")
        }
    }

    println("\n" + "=".repeat(50))
}