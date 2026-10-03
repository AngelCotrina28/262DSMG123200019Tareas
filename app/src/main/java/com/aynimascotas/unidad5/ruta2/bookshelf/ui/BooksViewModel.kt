package com.aynimascotas.unidad5.ruta2.bookshelf.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aynimascotas.unidad5.ruta2.bookshelf.model.BookDoc
import com.aynimascotas.unidad5.ruta2.bookshelf.network.BooksApi
import kotlinx.coroutines.launch

object LocalBooksDataProvider {
    val defaultBooks = listOf(
        BookDoc(key = "1", title = "Kotlin in Action", authorName = listOf("Dmitry Jemerov"), coverI = 8231996),
        BookDoc(key = "2", title = "Atomic Kotlin", authorName = listOf("Bruce Eckel"), coverI = 10522108),
        BookDoc(key = "3", title = "Head First Kotlin", authorName = listOf("Dawn Griffiths"), coverI = 9255288),
        BookDoc(key = "4", title = "Kotlin Programming", authorName = listOf("Josh Skeen"), coverI = 8387224),
        BookDoc(key = "5", title = "Hands-On Data Structures with Kotlin", authorName = listOf("Rivaan"), coverI = 10421230),
        BookDoc(key = "6", title = "Android Programming with Kotlin", authorName = listOf("Bill Phillips"), coverI = 9122340),
    )
}

sealed interface BooksUiState {
    data class Success(val books: List<BookDoc>) : BooksUiState
    data class Error(val message: String = "") : BooksUiState
    object Loading : BooksUiState
}

class BooksViewModel : ViewModel() {
    var booksUiState: BooksUiState by mutableStateOf(BooksUiState.Loading)
        private set

    init {
        getBooks()
    }

    fun getBooks() {
        viewModelScope.launch {
            booksUiState = BooksUiState.Loading
            booksUiState = try {
                val response = BooksApi.retrofitService.searchBooks("kotlin", 20)
                val books = response.docs?.filter { !it.title.isNullOrEmpty() } ?: emptyList()
                if (books.isEmpty()) {
                    BooksUiState.Success(LocalBooksDataProvider.defaultBooks)
                } else {
                    BooksUiState.Success(books)
                }
            } catch (e: Throwable) {
                BooksUiState.Success(LocalBooksDataProvider.defaultBooks)
            }
        }
    }
}
