package com.howloz.mostranstest.detailLocation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.howloz.mostranstest.R
import com.howloz.mostranstest.core.data.local.Result
import com.howloz.mostranstest.core.data.local.entity.LocationAndCharacter
import com.howloz.mostranstest.core.ui.ViewModelFactory
import com.howloz.mostranstest.core.utils.CharsAdapter
import com.howloz.mostranstest.databinding.ActivityDetailLocationBinding

class DetailLocationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailLocationBinding
    private lateinit var viewModel : DetailLocationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this,factory).get(DetailLocationViewModel::class.java)

        val id = intent.getStringExtra("ID")
        Log.d("id-app",id.toString())
        binding.rvChars.layoutManager = LinearLayoutManager(this)
        binding.rvChars.adapter = CharsAdapter(listOf())
        viewModel.getAllCharacterByLocation(id!!.toLong()).observe(this){
            when(it){
                is Result.Success -> {
                    binding.rvChars.adapter = CharsAdapter(it.data)
                    Log.d("data", it.data.size.toString())
                }

                else -> {}
            }
        }
    }
}