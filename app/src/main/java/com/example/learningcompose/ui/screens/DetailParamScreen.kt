package com.example.learningcompose.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learningcompose.ui.utils.TextDefault

@Composable
fun DetailParamScreen(navController: NavController, id: Int?) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TextDefault(text = "Detail Param $id", MaterialTheme.colorScheme.primary)
            Button(onClick = { navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Home.route) { inclusive = true }
            } }) {
                Text(text = "To Back")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DetailParamScreenPreview() {
    DetailParamScreen(rememberNavController(), 12)
}