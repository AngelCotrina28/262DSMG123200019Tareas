package com.aynimascotas.unidad4.ruta1.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aynimascotas.unidad4.ruta1.data.MAX_NO_OF_WORDS

@Composable
fun UnscrambleScreen(
    gameViewModel: GameViewModel = viewModel(),
) {
    val gameUiState by gameViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Unscramble",
            style = MaterialTheme.typography.titleLarge,
            fontSize = 28.sp,
            modifier = Modifier.padding(bottom = 16.dp),
        )

        GameLayout(
            onUserGuessChanged = { gameViewModel.updateUserGuess(it) },
            onKeyboardDone = { gameViewModel.checkUserGuess() },
            userGuess = gameViewModel.userGuess,
            isGuessWrong = gameUiState.isGuessedWordWrong,
            currentScrambledWord = gameUiState.currentScrambledWord,
            wordCount = gameUiState.currentWordCount,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { gameViewModel.checkUserGuess() },
            ) {
                Text(
                    text = "Enviar",
                    fontSize = 16.sp,
                )
            }

            OutlinedButton(
                onClick = { gameViewModel.skipWord() },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Omitir",
                    fontSize = 16.sp,
                )
            }
        }

        GameStatusLayout(
            score = gameUiState.score,
            modifier = Modifier.padding(20.dp),
        )

        if (gameUiState.isGameOver) {
            FinalScoreDialog(
                score = gameUiState.score,
                onPlayAgain = { gameViewModel.resetGame() },
            )
        }
    }
}

@Composable
fun GameStatusLayout(score: Int, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Text(
            text = "Puntaje: $score",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(12.dp),
        )
    }
}

@Composable
fun GameLayout(
    currentScrambledWord: String,
    wordCount: Int,
    isGuessWrong: Boolean,
    userGuess: String,
    onUserGuessChanged: (String) -> Unit,
    onKeyboardDone: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                Text(
                    text = "$wordCount de $MAX_NO_OF_WORDS",
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(horizontal = 10.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            Text(
                text = currentScrambledWord,
                style = MaterialTheme.typography.displayMedium,
            )

            Text(
                text = "Descifra la palabra usando todas las letras.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
            )

            OutlinedTextField(
                value = userGuess,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = onUserGuessChanged,
                label = {
                    if (isGuessWrong) {
                        Text("Respuesta incorrecta")
                    } else {
                        Text("Ingresa tu palabra")
                    }
                },
                isError = isGuessWrong,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(
                    onDone = { onKeyboardDone() },
                ),
            )
        }
    }
}

@Composable
private fun FinalScoreDialog(
    score: Int,
    onPlayAgain: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = "¡Felicidades!") },
        text = { Text(text = "Obtuviste $score puntos") },
        modifier = modifier,
        confirmButton = {
            TextButton(onClick = onPlayAgain) {
                Text(text = "Jugar de nuevo")
            }
        },
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UnscrambleScreenPreview() {
    UnscrambleScreen()
}
