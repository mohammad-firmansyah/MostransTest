package com.howloz.mostranstest.characters

import androidx.lifecycle.ViewModel
import com.howloz.mostranstest.core.data.CharacterRepository

class CharactersViewModel(private val characterRepository: CharacterRepository):ViewModel() {
    init {
        getCharacters()
    }

    fun getCharacters() = characterRepository.getAllChars()
}