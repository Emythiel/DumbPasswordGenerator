package com.example.dumbpasswordgenerator.ui.password

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

class PasswordViewModel: ViewModel() {
    var password by mutableStateOf("")
    var feedback by mutableStateOf("Feedback")

    var lengthFulfilled by mutableStateOf(false)
    var containsTwoNumbers by mutableStateOf(false)
    var containsTwoLetters by mutableStateOf(false)
    var containsTwoSpecials by mutableStateOf(false)

    fun updateFeedback() {

    }

    fun checkPassword() {
        // Minimum 6 characters
        lengthFulfilled = password.length >= 6

        // At least 2 numbers
        containsTwoNumbers = password.count { it.isDigit() } >= 2

        // At least 2 letters
        containsTwoLetters = password.count { it.isLetter() } >= 2

        // At least 2 special characters
        containsTwoSpecials = password.count { !it.isLetterOrDigit() } >= 2

        addUnfulfilledRule()
    }

    private fun addUnfulfilledRule() {

    }

    @Composable
    fun RuleItem(rule: String, isFulfilled: Boolean) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isFulfilled) {
                Text(text = "✓️", fontWeight = FontWeight.Bold, color = Color(0xFF2bb73a), modifier = Modifier.padding(end = 4.dp))
            } else {
                Text(text = "✗", fontWeight = FontWeight.Bold, color = Color.Red, modifier = Modifier.padding(end = 4.dp))
            }
            Text(text = rule)
        }
    }
}
