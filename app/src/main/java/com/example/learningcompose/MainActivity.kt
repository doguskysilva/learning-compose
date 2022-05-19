package com.example.learningcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.learningcompose.ui.SetupNavGraph
import com.example.learningcompose.ui.screens.MainScreen
import com.example.learningcompose.ui.theme.LearningComposeTheme

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearningComposeTheme {
                navController = rememberNavController()
                SetupNavGraph(navHostController = navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LearningComposeTheme {
        SetupNavGraph(navHostController = rememberNavController())
    }
}