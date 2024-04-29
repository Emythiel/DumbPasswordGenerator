package com.example.dumbpasswordgenerator.ui.start

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dumbpasswordgenerator.ui.Screen

@Composable
fun StartScreen(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Super Password Maker",
            fontSize = 34.sp,
            modifier = Modifier
                .padding(bottom = 8.dp)
        )
        Text(
            text = "Get help making a strong password",
            modifier = Modifier
                .padding(bottom = 12.dp)
        )

        Button(onClick = {
            navController.navigate(route = Screen.PasswordScreen.route)
        }) {
            Text(text = "Get started!")
        }
    }
}
