package com.howloz.mostranstest.character

import android.app.Dialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.howloz.mostranstest.R
import com.howloz.mostranstest.core.data.local.entity.Character
import com.howloz.mostranstest.core.data.local.entity.Location
import com.howloz.mostranstest.core.ui.ViewModelFactory
import com.howloz.mostranstest.databinding.ActivityCharacterBinding

class CharacterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCharacterBinding
    private lateinit var viewModel: CharacterViewModel
    private lateinit var char : Character
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val intent = intent

        char = intent.getParcelableExtra<Character>("CHAR_EXTRA")!!
        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this,factory).get(CharacterViewModel::class.java)

        Glide.with(this)
            .load(char?.image)
            .into(binding.photo)

        binding.title.text = char?.name
        binding.status.text = char?.status

        binding.button.setOnClickListener {
            showDialog()
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun showDialog(){
        val dialog = Dialog(this)

        dialog.setContentView(R.layout.dialog_location)
        dialog.requireViewById<Button>(R.id.assignButton).setOnClickListener {

            val loc = Location(
                id = null,
                name = dialog.requireViewById<EditText>(R.id.location_name).text.toString()
            )

            viewModel.addNewLocation(loc,char.id!!)

            dialog.dismiss()
        }

        dialog.show()
    }
}