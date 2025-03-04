package com.example.unscramble.ui

import android.provider.UserDictionary.Words
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.unscramble.data.MAX_NO_OF_WORDS
import com.example.unscramble.data.SCORE_INCREASE
import com.example.unscramble.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel: ViewModel() {
    // Game UI state
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()  // Backing property to avoid state updates
                                                                // from other classes
    private lateinit var currentWord: String
    private var usedWords: MutableSet<String> = mutableSetOf()

    var userGuess by mutableStateOf("")
    private set

    init {
        resetGame()
    }

    private fun pickRandomWordAndShuffle():String{
        currentWord = allWords.random()
        Log.d("CHECK","The currentWord is $currentWord")
        if(usedWords.contains(currentWord)){
            return pickRandomWordAndShuffle()
        }else{
            usedWords.add(currentWord)
            return shuffleCurrentWord(currentWord)
        }
    }

    fun checkUserGuess(){
        if(userGuess.equals(currentWord, ignoreCase = true)){
            // User's guess is correct, increase the score
            // and call updateGameState() to prepare the game for next round

            val updatedScore = _uiState.value.score.plus(SCORE_INCREASE)
            updatedGameState(updatedScore)
        }else{
            //User's guess is wrong show an error
            _uiState.update { currentState->
                currentState.copy(isGuessedWordWrong = true)
            }
        }
        updateUserGuess("")
    }

    private fun updatedGameState(updatedScore:Int){

        if(usedWords.size == MAX_NO_OF_WORDS){
            _uiState.update {
                currentState->
                currentState.copy(
                    isGuessedWordWrong = false,
                    score = updatedScore,
                    isGameOver = true
                )
            }
        }else{
            _uiState.update {
                    currentState->
                currentState.copy(
                    isGuessedWordWrong = false,
                    currentScrambledWord = pickRandomWordAndShuffle(),
                    score = updatedScore,
                    currentWordCount = currentState.currentWordCount.inc()
                )
            }
        }

    }

    private fun shuffleCurrentWord(word:String):String{
        val tempWord = word.toCharArray()
        // Scramble the word
        tempWord.shuffle()
        while (String(tempWord) == word){
            tempWord.shuffle()
        }
        return String(tempWord)
    }



    fun resetGame(){
        usedWords.clear()
        _uiState.value = GameUiState(currentScrambledWord = pickRandomWordAndShuffle())
    }


    fun updateUserGuess(guessedWord: String) {
                    userGuess = guessedWord
    }

    fun skipWord() {
        updatedGameState(_uiState.value.score)
        updateUserGuess("")
    }


}