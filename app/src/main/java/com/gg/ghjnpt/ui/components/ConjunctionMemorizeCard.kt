package com.gg.ghjnpt.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gg.ghjnpt.R
import com.gg.ghjnpt.data.Conjunction
import com.gg.ghjnpt.ui.theme.YongdalBlue
import com.gg.ghjnpt.ui.theme.YongdalBlueAccent
import com.gg.ghjnpt.ui.theme.YongdalBlueBackground
import com.gg.ghjnpt.ui.theme.YongdalBlueDark
import com.gg.ghjnpt.ui.theme.YongdalBlueLight

@Composable
fun ConjunctionMemorizeCard(
    conjunction: Conjunction,
    isRevealed: Boolean,
    isDifficult: Boolean,
    onReveal: () -> Unit,
    onToggleDifficult: () -> Unit
) {
    val fontSize = when (conjunction.japanese.length) {
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
            Text(
                text = conjunction.japanese,
                fontSize = fontSize,
                fontWeight = FontWeight.Bold,
                color = YongdalBlueDark,
                modifier = Modifier.weight(1f)
            )

            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(60.dp)
                    .alpha(0.5f)
                    .background(YongdalBlueDark)
                    .padding(horizontal = 8.dp)
            )

            Box(
                modifier = Modifier
                    .weight(2f)
                    .padding(horizontal = 8.dp)
                    .height(100.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { onReveal() }
            ) {
                if (isRevealed) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Transparent)
                            .padding(4.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = conjunction.meaning,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = YongdalBlueDark
                        )
                        Text(
                            text = conjunction.description,
                            fontSize = 12.sp,
                            color = YongdalBlue,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        Text(
                            text = "분류: ${conjunction.category}",
                            fontSize = 11.sp,
                            color = YongdalBlueAccent,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }
                } else {
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

            IconButton(
                onClick = onToggleDifficult,
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_dizzy_face_fill
                    ),
                    contentDescription = "어려운 접속사",
                    tint = if (isDifficult) YongdalBlue else Color.Gray,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}
