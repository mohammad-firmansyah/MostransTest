package com.howloz.mostranstest.location

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.howloz.mostranstest.R
import com.howloz.mostranstest.core.data.local.Result
import com.howloz.mostranstest.core.ui.ViewModelFactory
import com.howloz.mostranstest.core.utils.LocationAdapter
import com.howloz.mostranstest.databinding.ActivityLocationBinding

class LocationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLocationBinding
    lateinit var viewModel : LocationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this,factory).get(LocationViewModel::class.java)


        binding.rvLocations.adapter = LocationAdapter(this, listOf())
        binding.rvLocations.layoutManager = LinearLayoutManager(this)
        viewModel.getAllLocations().observe(this){
            when(it){
                is Result.Success -> {
                    binding.rvLocations.adapter = LocationAdapter(this, it.data)
                }

                else -> {}
            }
        }

    }
}