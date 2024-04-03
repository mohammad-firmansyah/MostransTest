package com.howloz.mostranstest.location

import androidx.lifecycle.ViewModel
import com.howloz.mostranstest.core.data.CharacterRepository

class LocationViewModel(private val characterRepository: CharacterRepository):ViewModel() {

    init {
        getAllLocations()
    }

    fun getAllLocations() = characterRepository.getLocations()

    fun deleteLocation(id: Long) {
        characterRepository.deleteLocation(id)
    }
}