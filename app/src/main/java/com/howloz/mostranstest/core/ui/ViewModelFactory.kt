package com.howloz.mostranstest.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.howloz.mostranstest.character.CharacterViewModel
import com.howloz.mostranstest.characters.CharactersViewModel
import com.howloz.mostranstest.core.data.CharacterRepository
import com.howloz.mostranstest.core.di.Injection
import com.howloz.mostranstest.detailLocation.DetailLocationViewModel
import com.howloz.mostranstest.location.LocationViewModel

class ViewModelFactory private constructor(private val repository: CharacterRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharactersViewModel::class.java)) {
            return CharactersViewModel(repository) as T
        }
        else if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
            return CharacterViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(LocationViewModel::class.java)) {
            return LocationViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(DetailLocationViewModel::class.java)) {
            return DetailLocationViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}