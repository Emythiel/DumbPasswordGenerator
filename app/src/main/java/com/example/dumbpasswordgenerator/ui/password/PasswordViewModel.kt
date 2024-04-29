package com.example.dumbpasswordgenerator.ui.password

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class PasswordViewModel: ViewModel() {

    var password by mutableStateOf("")
    var feedback by mutableStateOf("Feedback")
    var checkButtonClicked by mutableStateOf(false)

    private val targetSum = Random.nextInt(10, 21)
    private val numbersSum: Int
        get() = password.filter { it.isDigit() }.sumBy { it.toString().toInt()}

    private fun calculateNumbersSum(): Int {
        return password.filter { it.isDigit() }.sumBy { it.toString().toInt() }
    }

    fun extractNumbers(): Int {
        val regex = Regex("\\d") // Matches individual digits
        val matches = regex.findAll(password)
        val digits = mutableSetOf<Char>()

        for (match in matches) {
            digits.add(match.value[0])
        }
        return digits.size
    }

    // Rules
    val rules = mutableListOf(
        Rule("Must be at least 6 characters long") { password.length >= 6 },
        Rule("Cannot be longer than 16 characters") { password.length <= 16 },
        Rule("Must contain at least two numbers") { password.count { it.isDigit() } >= 2 },
        Rule("Must contain at least two letters") { password.count { it.isLetter() } >= 2 },
        Rule("Must contain at least two special characters") { password.count { !it.isLetterOrDigit() } >= 2 },
        Rule("Must have at least 1 uppercase letter") { password.any { it.isUpperCase() } },
        Rule("Must have at least 1 lowercase letter") { password.any { it.isLowerCase() } },
        Rule("Numbers must have a total sum of $targetSum") { numbersSum == targetSum },
        Rule("Two of the same letters cannot be next to each other") { !Regex("([a-zA-Z])\\1").containsMatchIn(password) },
        Rule("Must start with a special character") { Regex("^[^a-zA-Z0-9]").containsMatchIn(password) },
        Rule("Must end on a letter") { Regex("[a-zA-Z]\$").containsMatchIn(password) },
        Rule("Must contain at least one emoji") { Regex("[\\p{So}]").containsMatchIn(password) },
        Rule("Must have at least five different digits") {extractNumbers() >= 5 },
        Rule("Must contain at least one whitespace") {password.contains(" ")},
        Rule("Two of the same digits cannot be next to each other") {!Regex("(\\d)\\1").containsMatchIn(password)}
    )

    private var currentRuleIndex = 0

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
