package com.howloz.mostranstest.characters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.howloz.mostranstest.R
import com.howloz.mostranstest.core.data.local.Result
import com.howloz.mostranstest.core.ui.ViewModelFactory
import com.howloz.mostranstest.core.utils.CharsAdapter
import com.howloz.mostranstest.databinding.ActivityCharactersBinding
import com.howloz.mostranstest.location.LocationActivity

class CharactersActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCharactersBinding
    private lateinit var viewModel: CharactersViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(CharactersViewModel::class.java)


        binding.rvChars.adapter = CharsAdapter(listOf())
        binding.rvChars.layoutManager = LinearLayoutManager(this)
        binding.locations.setOnClickListener {
            startActivity(Intent(this@CharactersActivity,LocationActivity::class.java))
        }
        viewModel.getCharacters().observe(this){
            when (it) {
                is Result.Loading -> {

                }
                is Result.Success -> {

                    binding.rvChars.adapter = CharsAdapter(it.data)
                }

                else -> {}
            }
        }

    }
}