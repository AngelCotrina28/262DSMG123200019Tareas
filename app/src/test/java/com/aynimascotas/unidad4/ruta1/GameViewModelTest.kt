package com.aynimascotas.unidad4.ruta1

import com.aynimascotas.unidad4.ruta1.data.MAX_NO_OF_WORDS
import com.aynimascotas.unidad4.ruta1.data.SCORE_INCREASE
import com.aynimascotas.unidad4.ruta1.data.allWords
import com.aynimascotas.unidad4.ruta1.ui.GameViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class GameViewModelTest {
    private val viewModel = GameViewModel()

    @Test
    fun gameViewModel_correctWordGuessed_scoreUpdatedAndErrorFlagUnset() {
        var currentGameUiState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)

        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()

        currentGameUiState = viewModel.uiState.value
        assertFalse(currentGameUiState.isGuessedWordWrong)
        assertEquals(SCORE_INCREASE, currentGameUiState.score)
    }

    @Test
    fun gameViewModel_incorrectGuess_errorFlagSet() {
        val incorrectPlayerWord = "incorrecto"
        viewModel.updateUserGuess(incorrectPlayerWord)
        viewModel.checkUserGuess()

        val currentGameUiState = viewModel.uiState.value
        assertEquals(0, currentGameUiState.score)
        assertTrue(currentGameUiState.isGuessedWordWrong)
    }

    @Test
    fun gameViewModel_initialState_gameInitialized() {
        val currentGameUiState = viewModel.uiState.value
        val unScrambledWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)

        assertFalse(currentGameUiState.isGuessedWordWrong)
        assertEquals(0, currentGameUiState.score)
        assertEquals(1, currentGameUiState.currentWordCount)
        assertFalse(currentGameUiState.isGameOver)
        assertTrue(unScrambledWord.isNotEmpty())
    }

    @Test
    fun gameViewModel_allWordsGuessed_scoreUpdatedAndGameOverSet() {
        var expectedScore = 0
        var currentGameUiState = viewModel.uiState.value
        var correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)

        repeat(MAX_NO_OF_WORDS) {
            expectedScore += SCORE_INCREASE
            viewModel.updateUserGuess(correctPlayerWord)
            viewModel.checkUserGuess()
            currentGameUiState = viewModel.uiState.value
            if (it < MAX_NO_OF_WORDS - 1) {
                correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)
            }
        }

        assertEquals(expectedScore, currentGameUiState.score)
        assertTrue(currentGameUiState.isGameOver)
    }

    @Test
    fun gameViewModel_wordSkipped_scoreUnchangedAndWordCountIncreased() {
        var currentGameUiState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)

        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()

        currentGameUiState = viewModel.uiState.value
        val lastWordCount = currentGameUiState.currentWordCount

        viewModel.skipWord()
        currentGameUiState = viewModel.uiState.value

        assertEquals(SCORE_INCREASE, currentGameUiState.score)
        assertEquals(lastWordCount + 1, currentGameUiState.currentWordCount)
    }

    private fun getUnscrambledWord(scrambledWord: String): String {
        return allWords.first { word ->
            word.toCharArray().sorted() == scrambledWord.toCharArray().sorted()
        }
    }
}
