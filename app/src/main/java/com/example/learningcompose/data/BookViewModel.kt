package com.example.learningcompose.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Book(
    val id: Int,
    val name: String,
    val totalPages: Int
)

data class Page(
    val numberPage: Int,
    val text: String
)

fun getRandomString(length: Int): String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}

fun getPages(book: Book) = List(book.totalPages) { i -> Page(i + 1, getRandomString(25)) }

class BookViewModel : ViewModel() {
    private val books: MutableLiveData<List<Book>> = MutableLiveData()
    private val pages: MutableLiveData<List<Page>> = MutableLiveData()

    init {
        loadBooks()
    }

    fun getBooks(): LiveData<List<Book>> {
        return books
    }

    fun getPages(): LiveData<List<Page>> {
        return pages
    }

    fun clearBooks() {
        books.value = emptyList()
    }

    fun loadBooks() {
        books.value = (listOf(
            Book(1, "A Sociedade do Anel", 3),
            Book(2, "As Duas Torres", 4),
            Book(3, "O Retorno do Rei", 5),
            Book(4, "Os Sillmarilion", 10),
            Book(5, "O Hobbit", 2),
        ))
    }

    fun loadPages(book: Book) {
        pages.value = getPages(book)
    }
}