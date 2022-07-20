package com.example.learningcompose.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.learningcompose.data.Book
import com.example.learningcompose.data.BookViewModel
import com.example.learningcompose.data.Page

@Composable
fun ExamplesViewModelScreen(
    bookViewModel: BookViewModel = viewModel()
) {
    val books = bookViewModel.getBooks().observeAsState(emptyList())
    val pages = bookViewModel.getPages().observeAsState(emptyList())

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Surface(
            modifier = Modifier
                .weight(.5f)
                .fillMaxWidth(),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier.padding(10.dp),
                    onClick = { bookViewModel.clearBooks() }) {
                    Text(text = "Clear List")
                }

                Button(onClick = { bookViewModel.loadBooks() }) {
                    Text(text = "Reinsert List")
                }
            }
        }
        Surface(
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth(),
        ) {
            ListBooks(books = books.value, onSelectBook = { book -> bookViewModel.loadPages(book) })
        }
        Surface(
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth(),
        ) {
            ListPages(pages = pages.value, onSelectPage = {})
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListBooks(books: List<Book>, onSelectBook: (Book) -> Unit) {
    LazyColumn() {
        items(books) {
            Column {
                ListItem(
                    headlineText = { Text(it.name) },
                    modifier = Modifier.clickable {
                        onSelectBook(it)
                    }
                )
                Divider()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListPages(pages: List<Page>, onSelectPage: (Page) -> Unit) {
    LazyColumn() {
        items(pages) {
            Column {
                ListItem(
                    overlineText = { Text(it.numberPage.toString()) },
                    headlineText = { Text(it.text) },
                    modifier = Modifier.clickable {
                        onSelectPage(it)
                    }
                )
                Divider()
            }
        }
    }
}

