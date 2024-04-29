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

    private fun Char.isEmoji(): Boolean {
        return this.code in 0x1F300..0x1F6FF
    }

    val rules = mutableListOf(
        Rule("Must be at least 6 characters long") { password.length >= 6 },
        Rule("Cannot be longer than 16 characters") { password.length <= 16 },
        Rule("Must contain at least two numbers") { password.count { it.isDigit() } >= 2 },
        Rule("Must contain at least two letters") { password.count { it.isLetter() } >= 2 },
        Rule("Must contain at least two special characters") { password.count { !it.isLetterOrDigit() } >= 2 },
        Rule("Must have at least 1 uppercase letter") { password.any { it.isUpperCase() } },
        Rule("Must have at least 1 lowercase letter") { password.any { it.isLowerCase() } },
        Rule("Two of the same letters cannot be next to each other") { !Regex("([a-zA-Z])\\1").containsMatchIn(password) },
        Rule("Must start with a special character") { Regex("^[^a-zA-Z0-9]").containsMatchIn(password) },
        Rule("Must end on a letter") { Regex("[a-zA-Z]\$").containsMatchIn(password) },
        Rule("Must contain at least one emoji") { password.any { it.isEmoji() } }
    )

    fun updateFeedback() {

    }

    fun checkPassword() {
        rules.forEach { it.condition.invoke() }
        addUnfulfilledRule()
    }

    private fun addUnfulfilledRule() {
        val unfulfilledRules = rules.filter { !it.condition.invoke() }
        if (unfulfilledRules.isNotEmpty()) {
            rules.add(unfulfilledRules.first())
        }
    }



    @Composable
    fun RuleItem(rule: Rule) {
        val isFulfilled = rule.condition.invoke()
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isFulfilled) {
                Text(text = "✓️", fontWeight = FontWeight.Bold, color = Color(0xFF2bb73a), modifier = Modifier.padding(end = 4.dp))
            } else {
                Text(text = "✗", fontWeight = FontWeight.Bold, color = Color.Red, modifier = Modifier.padding(end = 4.dp))
            }
            Text(text = rule.name)
        }
    }
}
