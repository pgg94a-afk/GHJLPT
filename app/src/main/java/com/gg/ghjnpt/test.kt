import com.gg.ghjnpt.data.Grammar
import com.gg.ghjnpt.data.GrammarData

data class JPWord(
    var word: String,
    var hiragana: String,
    var mean: String,
    var hangeul: String,
) {
    override fun toString(): String {
        return "$word [$mean] $hangeul $hiragana"
    }
}

fun main() {
    println("=".repeat(50))
    println("일본어 문법 학습 프로그램")
    println("=".repeat(50))

    // 모드 선택
    println("\n모드를 선택하세요:")
    println("1. 암기 모드")
    println("2. 퀴즈 모드")
    print("선택 (1 또는 2): ")

    val mode = readLine()?.trim()

    when (mode) {
        "1" -> memorizeMode()
        "2" -> quizMode()
        else -> {
            println("잘못된 입력입니다. 프로그램을 종료합니다.")
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

fun memorizeMode() {
    println("\n📚 암기 모드를 시작합니다.")

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

fun quizMode() {
    println("\n✏️ 퀴즈 모드를 시작합니다.")

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