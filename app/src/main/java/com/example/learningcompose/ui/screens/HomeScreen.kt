package com.example.learningcompose.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.learningcompose.ui.utils.TextDefault
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun HomeScreen() {
    var name by rememberSaveable { mutableStateOf("Douglas") }
    var chapter by rememberSaveable { mutableStateOf(1) }
    val limitChapter = 5

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TextDefault(text = chapter.toString(), color = Color.Blue)
            NavigationChapters(currentChapter = chapter, limitChapter = limitChapter, onChapterChange = { chapter = it })
        }
    }
}

@Composable
fun NavigationChapters(currentChapter: Int, limitChapter: Int, onChapterChange: (Int) -> Unit) {
    Row() {
        Button(onClick = {
            if (currentChapter > 1)
                onChapterChange(currentChapter-1)
        }) {
            Text(text = "Previous")
        }
        Button(onClick = {
            if (currentChapter < limitChapter)
                onChapterChange(currentChapter+1)
        }) {
            Text(text = "Next")
        }
    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen()
}