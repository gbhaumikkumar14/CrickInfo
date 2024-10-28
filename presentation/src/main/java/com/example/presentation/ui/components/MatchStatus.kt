package com.example.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.presentation.theme.Typography

@Composable
fun MatchStatus(
    matchStarted: Boolean,
    matchEnded: Boolean,
    modifier: Modifier = Modifier
) {
    if(matchStarted && !matchEnded) {
        Text("Live",
            style = Typography.bodySmall.copy(color = Color.White),
            modifier = modifier.background(shape = RoundedCornerShape(4.dp), color = Color.Red).padding(all = 4.dp))
    } else if(matchStarted) {
        Text("Ended", style = Typography.bodySmall.copy(color = Color.White),
            modifier = modifier.background(shape = RoundedCornerShape(4.dp), color = Color.Gray).padding(all = 4.dp))
    } else {
        Text("Upcoming", style = Typography.bodySmall.copy(color = Color.White),
            modifier = modifier.background(shape = RoundedCornerShape(4.dp), color = Color.Blue).padding(all = 4.dp))
    }
}