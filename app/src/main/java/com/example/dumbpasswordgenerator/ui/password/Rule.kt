package com.example.dumbpasswordgenerator.ui.password

data class Rule(val name: String, val condition: () -> Boolean) {

}
