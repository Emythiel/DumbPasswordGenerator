package com.example.dumbpasswordgenerator.ui.password

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RulesBox(viewModel: PasswordViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFffe972), shape = RoundedCornerShape(16.dp))
            .border(width = 2.dp, color = Color.DarkGray, shape = RoundedCornerShape(16.dp))
            .padding(12.dp)
    ) {
        Column {
            viewModel.rules.forEach { rule ->
                viewModel.RuleItem(rule)
            }
        }
    }
}
