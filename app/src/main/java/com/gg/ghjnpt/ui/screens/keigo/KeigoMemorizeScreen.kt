package com.gg.ghjnpt.ui.screens.keigo

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gg.ghjnpt.QuizViewModel
import com.gg.ghjnpt.R
import com.gg.ghjnpt.ui.components.KeigoMemorizeCard
import com.gg.ghjnpt.ui.theme.YongdalBlue
import com.gg.ghjnpt.ui.theme.YongdalBlueDark
import com.gg.ghjnpt.ui.theme.YongdalBlueSurface

@Composable
fun KeigoMemorizeScreen(navController: NavHostController, viewModel: QuizViewModel) {
    var revealedItems by remember { mutableStateOf(setOf<Int>()) }
    var showOnlyDifficult by remember { mutableStateOf(false) }

    val displayKeigos = remember(showOnlyDifficult, viewModel.keigos, viewModel.difficultKeigos) {
        if (showOnlyDifficult) {
            viewModel.keigos.filter { viewModel.isDifficultKeigo(it) }
        } else {
            viewModel.keigos
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
                        "어려운 경어 (${displayKeigos.size}개)"
                    else
                        "경어 암기 (${displayKeigos.size}개)",
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
                            contentDescription = "어려운 경어 필터",
                            tint = if (showOnlyDifficult) YongdalBlue else Color.Gray,
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    if (viewModel.difficultKeigos.isNotEmpty()) {
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
                                text = "${viewModel.difficultKeigos.size}",
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

            if (displayKeigos.isEmpty()) {
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
                            text = "어려운 경어가 없습니다",
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
                        count = displayKeigos.size,
                        key = { index -> "${displayKeigos[index].japanese}_${displayKeigos[index].type}_$index" }
                    ) { index ->
                        val keigo = displayKeigos[index]
                        val originalIndex = viewModel.keigos.indexOf(keigo)

                        KeigoMemorizeCard(
                            keigo = keigo,
                            isRevealed = revealedItems.contains(originalIndex),
                            isDifficult = viewModel.isDifficultKeigo(keigo),
                            onReveal = {
                                revealedItems = if (revealedItems.contains(originalIndex)) {
                                    revealedItems - originalIndex
                                } else {
                                    revealedItems + originalIndex
                                }
                            },
                            onToggleDifficult = {
                                viewModel.toggleDifficultKeigo(keigo)
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}
