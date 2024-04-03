package com.howloz.mostranstest.character

import androidx.lifecycle.ViewModel
import com.howloz.mostranstest.core.data.CharacterRepository
import com.howloz.mostranstest.core.data.local.entity.Location

class CharacterViewModel(private val characterRepository: CharacterRepository): ViewModel() {

    fun addNewLocation(loc:Location,charId : Long){
        characterRepository.insertLocation(loc = loc,charId = charId)
    }
}