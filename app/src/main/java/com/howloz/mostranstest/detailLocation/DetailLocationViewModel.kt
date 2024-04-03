package com.howloz.mostranstest.detailLocation

import androidx.lifecycle.ViewModel
import com.howloz.mostranstest.core.data.CharacterRepository

class DetailLocationViewModel(private val characterRepository: CharacterRepository): ViewModel() {

    fun getAllCharacterByLocation(loc_id:Long) = characterRepository.getAllCharByLocation(loc_id)
}