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
    val lengthFulfilledString: String = "Must be at least 6 characters long"
    var containsTwoNumbers by mutableStateOf(false)
    val containsTwoNumbersString: String = "Must contain at least two numbers"
    var containsTwoLetters by mutableStateOf(false)
    val containsTwoLettersString: String = "Must contain at least two letters"
    var containsTwoSpecials by mutableStateOf(false)
    val containsTwoSpecialsString: String = "Must contain at least two special characters"

    val rules = mutableListOf<String>(
        lengthFulfilledString
    )

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
        val unfulfilledRules = mutableListOf<String>()

        // Check each rule and add unfulfilled ones to the list
        if (!lengthFulfilled && !rules.contains(lengthFulfilledString)) unfulfilledRules.add(lengthFulfilledString)
        if (!containsTwoNumbers && lengthFulfilled && !rules.contains(containsTwoNumbersString)) unfulfilledRules.add(containsTwoNumbersString)
        if (!containsTwoLetters && lengthFulfilled && !rules.contains(containsTwoLettersString)) unfulfilledRules.add(containsTwoLettersString)
        if (!containsTwoSpecials && lengthFulfilled && !rules.contains(containsTwoSpecialsString)) unfulfilledRules.add(containsTwoSpecialsString)

        // If there are unfulfilled rules, add the first one to the rules list
        if (unfulfilledRules.isNotEmpty()) {
            rules.add(unfulfilledRules.first())
        }
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
