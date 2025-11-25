package com.gg.ghjnpt.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gg.ghjnpt.ui.theme.YongdalBlue
import com.gg.ghjnpt.ui.theme.YongdalBlueAccent
import com.gg.ghjnpt.ui.theme.YongdalBlueBackground
import com.gg.ghjnpt.ui.theme.YongdalBlueDark
import com.gg.ghjnpt.ui.theme.YongdalBlueLight

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
