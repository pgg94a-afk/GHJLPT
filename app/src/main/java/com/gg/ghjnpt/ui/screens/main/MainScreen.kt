package com.gg.ghjnpt.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.gg.ghjnpt.QuizViewModel
import com.gg.ghjnpt.ui.components.DaySection
import com.gg.ghjnpt.ui.components.GrammarCheckboxCard
import com.gg.ghjnpt.ui.components.ConjunctionCheckboxCard
import com.gg.ghjnpt.ui.theme.YongdalBlue
import com.gg.ghjnpt.ui.theme.YongdalBlueAccent
import com.gg.ghjnpt.ui.theme.YongdalBlueDark
import com.gg.ghjnpt.ui.theme.YongdalBlueSurface

@Composable
fun MainScreen(navController: NavHostController, viewModel: QuizViewModel) {
    var selectedTab by remember { mutableStateOf(0) }
    var selectedWordLevels by remember { mutableStateOf(List(17) { false }) }
    var selectedGrammarLevels by remember { mutableStateOf(List(18) { false }) }
    var selectedConjunctionGroups by remember { mutableStateOf(mapOf(
        "N3_순접추가" to false,
        "N3_역접대조" to false,
        "N3_이유원인" to false,
        "N3_전환조건" to false,
        "N4_순접추가" to false,
        "N4_역접" to false,
        "N4_이유원인" to false,
        "N4_전환조건" to false,
    )) }

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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { selectedTab = 0 },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedTab == 0) YongdalBlue else Color.LightGray
                    )
                ) {
                    Text("단어", color = Color.White)
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = { selectedTab = 1 },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedTab == 1) YongdalBlue else Color.LightGray
                    )
                ) {
                    Text("문법", color = Color.White)
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = { selectedTab = 2 },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedTab == 2) YongdalBlue else Color.LightGray
                    )
                ) {
                    Text("접속사", color = Color.White)
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = { selectedTab = 3 },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedTab == 3) YongdalBlue else Color.LightGray
                    )
                ) {
                    Text("경어", color = Color.White)
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
                            item {
                                DaySection(
                                    title = "6일차",
                                    levels = listOf(15, 16, 17),
                                    selectedLevels = selectedWordLevels,
                                    onLevelToggle = { level ->
                                        selectedWordLevels = selectedWordLevels.mapIndexed { i, selected ->
                                            if (i == level - 1) !selected else selected
                                        }
                                    }
                                )
                            }
                        }
                    } else if (selectedTab == 3) {
                        // 경어 탭
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            item {
                                Text(
                                    text = "경어 표현",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = YongdalBlueDark,
                                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                                )
                            }
                        }
                    } else if (selectedTab == 1) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(18) { index ->
                                val grammarLevel = index + 1
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
                    } else {
                        // 접속사 탭
                        val n3Groups = listOf(
                            "순접·추가" to "N3_순접추가",
                            "역접·대조" to "N3_역접대조",
                            "이유·원인" to "N3_이유원인",
                            "전환·조건" to "N3_전환조건",
                        )

                        val n4Groups = listOf(
                            "순접·추가" to "N4_순접추가",
                            "역접" to "N4_역접",
                            "이유·원인" to "N4_이유원인",
                            "전환·조건" to "N4_전환조건",
                        )

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            // N3 섹션
                            item {
                                Text(
                                    text = "N3 접속사",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = YongdalBlueDark,
                                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
                                )
                            }

                            items(
                                count = n3Groups.size,
                                key = { index -> n3Groups[index].second }
                            ) { index ->
                                val (groupName, groupKey) = n3Groups[index]
                                ConjunctionCheckboxCard(
                                    groupName = groupName,
                                    groupKey = groupKey,
                                    isSelected = selectedConjunctionGroups[groupKey] ?: false,
                                    onToggle = {
                                        selectedConjunctionGroups = selectedConjunctionGroups.toMutableMap().apply {
                                            this[groupKey] = !(selectedConjunctionGroups[groupKey] ?: false)
                                        }
                                    }
                                )
                            }

                            // N4 섹션
                            item {
                                Text(
                                    text = "N4 접속사",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = YongdalBlueDark,
                                    modifier = Modifier.padding(top = 16.dp, bottom = 4.dp)
                                )
                            }

                            items(
                                count = n4Groups.size,
                                key = { index -> n4Groups[index].second }
                            ) { index ->
                                val (groupName, groupKey) = n4Groups[index]
                                ConjunctionCheckboxCard(
                                    groupName = groupName,
                                    groupKey = groupKey,
                                    isSelected = selectedConjunctionGroups[groupKey] ?: false,
                                    onToggle = {
                                        selectedConjunctionGroups = selectedConjunctionGroups.toMutableMap().apply {
                                            this[groupKey] = !(selectedConjunctionGroups[groupKey] ?: false)
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (selectedTab == 0) {
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
                } else if (selectedTab == 1) {
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
                } else if (selectedTab == 2) {
                    Button(
                        onClick = {
                            val selected = selectedConjunctionGroups.filterValues { it }.keys.toList()
                            if (selected.isNotEmpty()) {
                                viewModel.loadSelectedConjunctions(selected)
                                navController.navigate("conjunctionMemorize")
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = YongdalBlueAccent
                        )
                    ) {
                        Text("암기하기")
                    }
                } else if (selectedTab == 3) {
                    Button(
                        onClick = {
                            viewModel.loadSelectedKeigos()
                            navController.navigate("keigoMemorize")
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
