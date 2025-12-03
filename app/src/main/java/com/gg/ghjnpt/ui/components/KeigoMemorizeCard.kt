package com.gg.ghjnpt.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gg.ghjnpt.R
import com.gg.ghjnpt.data.KeigoExpression
import com.gg.ghjnpt.ui.theme.YongdalBlue
import com.gg.ghjnpt.ui.theme.YongdalBlueAccent
import com.gg.ghjnpt.ui.theme.YongdalBlueBackground
import com.gg.ghjnpt.ui.theme.YongdalBlueDark
import com.gg.ghjnpt.ui.theme.YongdalBlueLight

@Composable
fun KeigoMemorizeCard(
    keigo: KeigoExpression,
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = keigo.japanese,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = YongdalBlueDark
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = keigo.hiragana,
                        fontSize = 14.sp,
                        color = YongdalBlue.copy(alpha = 0.7f)
                    )
                }

                IconButton(
                    onClick = onToggleDifficult,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (isDifficult) R.drawable.ic_dizzy_face_fill else R.drawable.ic_dizzy_face_fill
                        ),
                        contentDescription = "어려운 경어",
                        tint = if (isDifficult) YongdalBlue else Color.Gray,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { onReveal() }
            ) {
                if (isRevealed) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = keigo.type,
                            fontSize = 12.sp,
                            color = YongdalBlueAccent,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = keigo.meaning,
                            fontSize = 18.sp,
                            color = YongdalBlueDark,
                            fontWeight = FontWeight.Medium,
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            keigo.koreanPronounce,
                            fontSize = 14.sp,
                            color = YongdalBlue
                        )
                    }
                } else {
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
