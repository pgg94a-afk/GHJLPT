package com.gg.ghjnpt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.*
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import com.airbnb.lottie.compose.*
import com.gg.ghjnpt.data.Grammar
import kotlinx.coroutines.delay

import com.gg.ghjnpt.ui.theme.GHJNPTTheme
import com.gg.ghjnpt.data.JPWord
import com.gg.ghjnpt.ui.theme.YongdalBlue
import com.gg.ghjnpt.ui.theme.YongdalBlueAccent
import com.gg.ghjnpt.ui.theme.YongdalBlueBackground
import com.gg.ghjnpt.ui.theme.YongdalBlueDark
import com.gg.ghjnpt.ui.theme.YongdalBlueLight
import com.gg.ghjnpt.ui.theme.YongdalBlueSurface

class MainActivity : ComponentActivity() {

    private val quizViewModel = QuizViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GHJNPTTheme {
                val navController = rememberNavController()

                NavHost(navController, startDestination = "main") {
                    composable("main") { MainScreen(navController, quizViewModel) }
                    composable("quiz") { QuizScreen(navController, quizViewModel) }
                    composable("memorize") { MemorizeScreen(navController, quizViewModel) } // ✅ 암기 화면 추가
                    composable("grammarMemorize") { GrammarMemorizeScreen(navController, quizViewModel) } // ✅ 문법 암기 추가
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavHostController, viewModel: QuizViewModel) {
    var selectedTab by remember { mutableStateOf(0) } // 0: 단어, 1: 문법
    var selectedWordLevels by remember { mutableStateOf(List(14) { false }) }
    var selectedGrammarLevels by remember { mutableStateOf(List(10) { false }) } // 6~10 레벨

    val scrollBackgroundColor = YongdalBlueSurface

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = YongdalBlueSurface
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "학습 메뉴",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = YongdalBlueDark,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // ✅ 탭 UI (단어 | 문법)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                // 단어 탭
                Button(
                    onClick = { selectedTab = 0 },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedTab == 0) YongdalBlue else Color.LightGray
                    )
                ) {
                    Text("단어", color = Color.White)
                }

                Spacer(modifier = Modifier.width(12.dp))

                // 문법 탭
                Button(
                    onClick = { selectedTab = 1 },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedTab == 1) YongdalBlue else Color.LightGray
                    )
                ) {
                    Text("문법", color = Color.White)
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Card(
                    modifier = Modifier.fillMaxSize(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = scrollBackgroundColor
                    )
                ) {
                    if (selectedTab == 0) {
                        // ✅ 단어 선택 UI (기존)
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            item {
                                DaySection(
                                    title = "1일차",
                                    levels = listOf(1, 2),
                                    selectedLevels = selectedWordLevels,
                                    onLevelToggle = { level ->
                                        selectedWordLevels = selectedWordLevels.mapIndexed { i, selected ->
                                            if (i == level - 1) !selected else selected
                                        }
                                    }
                                )
                            }
                            item {
                                DaySection(
                                    title = "2일차",
                                    levels = listOf(3, 4, 5),
                                    selectedLevels = selectedWordLevels,
                                    onLevelToggle = { level ->
                                        selectedWordLevels = selectedWordLevels.mapIndexed { i, selected ->
                                            if (i == level - 1) !selected else selected
                                        }
                                    }
                                )
                            }
                            item {
                                DaySection(
                                    title = "3일차",
                                    levels = listOf(6, 7, 8),
                                    selectedLevels = selectedWordLevels,
                                    onLevelToggle = { level ->
                                        selectedWordLevels = selectedWordLevels.mapIndexed { i, selected ->
                                            if (i == level - 1) !selected else selected
                                        }
                                    }
                                )
                            }
                            item {
                                DaySection(
                                    title = "4일차",
                                    levels = listOf(9, 10, 11),
                                    selectedLevels = selectedWordLevels,
                                    onLevelToggle = { level ->
                                        selectedWordLevels = selectedWordLevels.mapIndexed { i, selected ->
                                            if (i == level - 1) !selected else selected
                                        }
                                    }
                                )
                            }
                            item {
                                DaySection(
                                    title = "5일차",
                                    levels = listOf(12, 13, 14),
                                    selectedLevels = selectedWordLevels,
                                    onLevelToggle = { level ->
                                        selectedWordLevels = selectedWordLevels.mapIndexed { i, selected ->
                                            if (i == level - 1) !selected else selected
                                        }
                                    }
                                )
                            }
                        }
                    } else {
                        // ✅ 문법 선택 UI
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(10) { index ->
                                val grammarLevel = index + 1 // 1~10
                                GrammarCheckboxCard(
                                    level = grammarLevel,
                                    isSelected = selectedGrammarLevels[index],
                                    onToggle = {
                                        selectedGrammarLevels = selectedGrammarLevels.mapIndexed { i, selected ->
                                            if (i == index) !selected else selected
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            // ✅ 하단 버튼
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (selectedTab == 0) {
                    // 단어: 문제풀기 + 암기모드
                    Button(
                        onClick = {
                            val selected = selectedWordLevels.mapIndexedNotNull { index, isSelected ->
                                if (isSelected) index + 1 else null
                            }
                            if (selected.isNotEmpty()) {
                                viewModel.loadSelectedWords(selected)
                                navController.navigate("quiz")
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = YongdalBlue
                        )
                    ) {
                        Text("문제 풀기 시작")
                    }

                    Button(
                        onClick = {
                            val selected = selectedWordLevels.mapIndexedNotNull { index, isSelected ->
                                if (isSelected) index + 1 else null
                            }
                            if (selected.isNotEmpty()) {
                                viewModel.loadSelectedWords(selected)
                                navController.navigate("memorize")
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = YongdalBlueAccent
                        )
                    ) {
                        Text("암기 모드")
                    }
                } else {
                    // 문법: 암기하기만
                    Button(
                        onClick = {
                            val selected = selectedGrammarLevels.mapIndexedNotNull { index, isSelected ->
                                if (isSelected) index + 1 else null
                            }
                            if (selected.isNotEmpty()) {
                                viewModel.loadSelectedGrammars(selected)
                                navController.navigate("grammarMemorize")
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = YongdalBlueAccent
                        )
                    ) {
                        Text("암기하기")
                    }
                }
            }
        }
    }
}

// ✅ 일차별 섹션 - 크기 고정
@Composable
fun DaySection(
    title: String,
    levels: List<Int>,
    selectedLevels: List<Boolean>,
    onLevelToggle: (Int) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = YongdalBlue,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                levels.forEach { level ->
                    LevelCheckbox(
                        level = level,
                        isSelected = selectedLevels[level - 1],
                        onToggle = { onLevelToggle(level) }
                    )
                }

                repeat(3 - levels.size) {
                    Spacer(modifier = Modifier.size(80.dp))
                }
            }
        }
    }
}

// ✅ 레벨 체크박스 - 고정 크기
@Composable
fun LevelCheckbox(
    level: Int,
    isSelected: Boolean,
    onToggle: () -> Unit
) {
    Card(
        modifier = Modifier
            .size(80.dp)
            .clickable { onToggle() },
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected)
                YongdalBlueLight
            else
                YongdalBlueBackground
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) 6.dp else 2.dp
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Checkbox(
                    checked = isSelected,
                    onCheckedChange = { onToggle() },
                    colors = CheckboxDefaults.colors(
                        checkedColor = YongdalBlue,
                        uncheckedColor = YongdalBlueAccent
                    )
                )
                Text(
                    text = "$level",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isSelected)
                        YongdalBlueDark
                    else
                        YongdalBlue
                )
            }
        }
    }
}


@Composable
fun QuizScreen(navController: NavHostController, viewModel: QuizViewModel) {
    val word = viewModel.currentWord
    val progress =
        if (viewModel.total == 0) 0f else (viewModel.currentIndex + 1).toFloat() / viewModel.total

    val correctComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.correct))
    val wrongComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.wrong))

    // isCorrect 상태 변경 시 애니메이션 후 다음 문제로 이동
    LaunchedEffect(viewModel.isCorrect) {
        if (viewModel.isCorrect == false) {
            delay(3500)
            viewModel.nextQuestion()
        } else if (viewModel.isCorrect == true) {
            delay(2000)
            viewModel.nextQuestion()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .imePadding()
                    .padding(24.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // ✅ 상단 영역 (홈 버튼 + 진행률)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // ✅ 홈 버튼
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "홈으로"
                        )
                    }

                    // 진행률
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        LinearProgressIndicator(
                            progress = progress,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = "${viewModel.currentIndex + 1} / ${viewModel.total}",
                            modifier = Modifier.align(Alignment.End),
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 문제 영역 (중앙에 고정)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, fill = true),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = word?.word ?: "",
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 입력 영역
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = viewModel.userAnswer,
                        onValueChange = { viewModel.userAnswer = it },
                        label = { Text("한글 발음을 입력하세요") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        enabled = viewModel.isCorrect == null
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { viewModel.checkAnswer() },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = viewModel.isCorrect == null
                    ) {
                        Text("확인")
                    }
                }
            }

            // 정답 애니메이션 (기존 코드 동일)
            AnimatedVisibility(
                visible = viewModel.isCorrect == true,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        LottieAnimation(
                            composition = correctComposition,
                            iterations = 1,
                            modifier = Modifier.fillMaxWidth().height(200.dp)
                        )

                        word?.let {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            ) {
                                Text(
                                    text = it.word,
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF4CAF50)
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = it.koreanPronounce,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = it.kana,
                                    fontSize = 20.sp,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = it.meaning,
                                    fontSize = 18.sp,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }

            // 오답 애니메이션 (기존 코드 동일)
            AnimatedVisibility(
                visible = viewModel.isCorrect == false,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        LottieAnimation(
                            composition = wrongComposition,
                            iterations = 1,
                            modifier = Modifier.fillMaxWidth().height(200.dp)
                        )

                        word?.let {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            ) {
                                Text(
                                    text = it.word,
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Red
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = it.koreanPronounce,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = it.kana,
                                    fontSize = 20.sp,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = it.meaning,
                                    fontSize = 18.sp,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MemorizeScreen(navController: NavHostController, viewModel: QuizViewModel) {
    var revealedItems by remember { mutableStateOf(setOf<Int>()) } // ✅ 빈 Set으로 시작
    var showOnlyDifficult by remember { mutableStateOf(false) }

    val displayWords = remember(showOnlyDifficult, viewModel.words, viewModel.difficultWords) {
        if (showOnlyDifficult) {
            viewModel.words.filter { viewModel.isDifficult(it) }
        } else {
            viewModel.words
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = YongdalBlueSurface
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "홈으로",
                        tint = YongdalBlue
                    )
                }

                Text(
                    text = if (showOnlyDifficult)
                        "어려운 단어 (${displayWords.size}개)"
                    else
                        "암기 모드 (${displayWords.size}개)",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = YongdalBlueDark,
                    modifier = Modifier.weight(1f).padding(start = 8.dp)
                )

                Box {
                    IconButton(
                        onClick = { showOnlyDifficult = !showOnlyDifficult }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_dizzy_face_fill),
                            contentDescription = "어려운 단어 필터",
                            tint = if (showOnlyDifficult) YongdalBlue else Color.Gray,
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    if (viewModel.difficultWords.isNotEmpty()) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .offset(x = 4.dp, y = 4.dp)
                                .size(20.dp)
                                .background(
                                    if (showOnlyDifficult) YongdalBlue else Color.Gray,
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${viewModel.difficultWords.size}",
                                color = Color.White,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            if (revealedItems.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    OutlinedButton(
                        onClick = { revealedItems = setOf() },
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = YongdalBlue
                        ),
                        border = BorderStroke(1.dp, YongdalBlue)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("모두 가리기", fontSize = 14.sp)
                    }
                }
            }

            if (displayWords.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_dizzy_face_fill),
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(64.dp)
                        )
                        Text(
                            text = "어려운 단어가 없습니다",
                            fontSize = 16.sp,
                            color = Color.Gray
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {
                    items(
                        count = displayWords.size,
                        key = { index -> displayWords[index].word } // ✅ key 추가
                    ) { index ->
                        val word = displayWords[index]
                        val originalIndex = viewModel.words.indexOf(word)

                        MemorizeCard(
                            word = word,
                            isRevealed = revealedItems.contains(originalIndex), // ✅ 명확히 전달
                            isDifficult = viewModel.isDifficult(word),
                            onReveal = {
                                revealedItems = if (revealedItems.contains(originalIndex)) {
                                    revealedItems - originalIndex // 제거
                                } else {
                                    revealedItems + originalIndex // 추가
                                }
                            },
                            onToggleDifficult = {
                                viewModel.toggleDifficultWord(word)
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun MemorizeCard(
    word: JPWord,
    isRevealed: Boolean,
    isDifficult: Boolean,
    onReveal: () -> Unit,
    onToggleDifficult: () -> Unit
) {

    // ✅ 글자 수에 따른 폰트 크기 계산
    val fontSize = when (word.word.length) {
        1 -> 24.sp
        2 -> 24.sp
        3 -> 24.sp
        4 -> 20.sp
        else -> 18.sp
    }


    Card(
        colors = CardDefaults.cardColors(containerColor = YongdalBlueBackground),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 한자 (항상 표시)
            Text(
                text = word.word,
                fontSize = fontSize,
                fontWeight = FontWeight.Bold,
                color = YongdalBlueDark,
                modifier = Modifier.weight(1f)
            )

            // 구분선
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(60.dp)
                    .alpha(0.5f)
                    .background(YongdalBlueDark)
                    .padding(horizontal = 8.dp)
            )

            // ✅ 중앙 영역 (가림막 또는 내용)
            Box(
                modifier = Modifier
                    .weight(2f)
                    .padding(horizontal = 8.dp)
                    .height(80.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { onReveal() } // ✅ 전체 영역 클릭
            ) {
                if (isRevealed) {
                    // ✅ 내용 표시
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Transparent)
                            .padding(4.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = word.kana,
                            fontSize = 18.sp,
                            color = YongdalBlueDark
                        )
                        Text(
                            text = word.koreanPronounce,
                            fontSize = 14.sp,
                            color = YongdalBlue
                        )
                        Text(
                            text = word.meaning,
                            fontSize = 14.sp,
                            color = YongdalBlue
                        )
                    }
                } else {
                    // ✅ 가림막 표시
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(YongdalBlueLight.copy(alpha = 0.5f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "터치하여 확인",
                            fontSize = 14.sp,
                            color = YongdalBlueAccent,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            // ✅ 어려움 아이콘
            IconButton(
                onClick = onToggleDifficult,
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    painter = painterResource(
                        id = if (isDifficult) R.drawable.ic_dizzy_face_fill else R.drawable.ic_dizzy_face_fill
                    ),
                    contentDescription = "어려운 단어",
                    tint = if (isDifficult) YongdalBlue else Color.Gray,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

@Composable
fun GrammarMemorizeScreen(navController: NavHostController, viewModel: QuizViewModel) {
    var revealedItems by remember { mutableStateOf(setOf<Int>()) }
    var showOnlyDifficult by remember { mutableStateOf(false) }

    val displayGrammars = remember(showOnlyDifficult, viewModel.grammars, viewModel.difficultGrammars) {
        if (showOnlyDifficult) {
            viewModel.grammars.filter { viewModel.isDifficultGrammar(it) }
        } else {
            viewModel.grammars
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = YongdalBlueSurface
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "홈으로",
                        tint = YongdalBlue
                    )
                }

                Text(
                    text = if (showOnlyDifficult)
                        "어려운 문법 (${displayGrammars.size}개)"
                    else
                        "문법 암기 (${displayGrammars.size}개)",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = YongdalBlueDark,
                    modifier = Modifier.weight(1f).padding(start = 8.dp)
                )

                Box {
                    IconButton(
                        onClick = { showOnlyDifficult = !showOnlyDifficult }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_dizzy_face_fill),
                            contentDescription = "어려운 문법 필터",
                            tint = if (showOnlyDifficult) YongdalBlue else Color.Gray,
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    if (viewModel.difficultGrammars.isNotEmpty()) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .offset(x = 4.dp, y = 4.dp)
                                .size(20.dp)
                                .background(
                                    if (showOnlyDifficult) YongdalBlue else Color.Gray,
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${viewModel.difficultGrammars.size}",
                                color = Color.White,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            if (revealedItems.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    OutlinedButton(
                        onClick = { revealedItems = setOf() },
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = YongdalBlue
                        ),
                        border = BorderStroke(1.dp, YongdalBlue)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("모두 가리기", fontSize = 14.sp)
                    }
                }
            }

            if (displayGrammars.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_dizzy_face_fill),
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(64.dp)
                        )
                        Text(
                            text = "어려운 문법이 없습니다",
                            fontSize = 16.sp,
                            color = Color.Gray
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {
                    items(
                        count = displayGrammars.size,
                        key = { index -> displayGrammars[index].japaneseGrammar }
                    ) { index ->
                        val grammar = displayGrammars[index]
                        val originalIndex = viewModel.grammars.indexOf(grammar)

                        GrammarMemorizeCard(
                            grammar = grammar,
                            isRevealed = revealedItems.contains(originalIndex),
                            isDifficult = viewModel.isDifficultGrammar(grammar),
                            onReveal = {
                                revealedItems = if (revealedItems.contains(originalIndex)) {
                                    revealedItems - originalIndex
                                } else {
                                    revealedItems + originalIndex
                                }
                            },
                            onToggleDifficult = {
                                viewModel.toggleDifficultGrammar(grammar)
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun GrammarCheckboxCard(
    level: Int,
    isSelected: Boolean,
    onToggle: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggle() },
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected)
                YongdalBlueLight
            else
                Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) 4.dp else 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "문법 Level $level",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) YongdalBlueDark else YongdalBlue
            )

            Checkbox(
                checked = isSelected,
                onCheckedChange = { onToggle() },
                colors = CheckboxDefaults.colors(
                    checkedColor = YongdalBlue,
                    uncheckedColor = YongdalBlueAccent
                )
            )
        }
    }
}

@Composable
fun GrammarMemorizeCard(
    grammar: Grammar,
    isRevealed: Boolean,
    isDifficult: Boolean,
    onReveal: () -> Unit,
    onToggleDifficult: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = YongdalBlueBackground),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // ✅ 상단: 일본어 문법 (항상 표시)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = grammar.japaneseGrammar,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = YongdalBlueDark,
                    modifier = Modifier.weight(1f)
                )

                // ✅ 어려움 아이콘
                IconButton(
                    onClick = onToggleDifficult,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (isDifficult) R.drawable.ic_dizzy_face_fill else R.drawable.ic_dizzy_face_fill
                        ),
                        contentDescription = "어려운 문법",
                        tint = if (isDifficult) YongdalBlue else Color.Gray,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // ✅ 중단: 접속형태 | 뜻 (가림막)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { onReveal() }
            ) {
                if (isRevealed) {
                    // 내용 표시
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        // ✅ 하단: 히라가나 + 한글 발음 (작은 글자)
                        Text(
                            grammar.koreanPronounce,
                            fontSize = 14.sp,
                            color = YongdalBlue
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = grammar.connection,
                            fontSize = 16.sp,
                            color = YongdalBlueDark,
                            fontWeight = FontWeight.Medium,
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = grammar.meaning,
                            fontSize = 16.sp,
                            color = YongdalBlueDark,
                            fontWeight = FontWeight.Medium,
                        )
                    }
                } else {
                    // 가림막 표시
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .background(YongdalBlueLight.copy(alpha = 0.5f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "터치하여 확인",
                            fontSize = 14.sp,
                            color = YongdalBlueAccent,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}