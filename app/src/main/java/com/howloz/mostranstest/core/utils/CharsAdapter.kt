package com.howloz.mostranstest.core.utils

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.howloz.mostranstest.R
import com.howloz.mostranstest.character.CharacterActivity
import com.howloz.mostranstest.core.data.local.entity.Character

class CharsAdapter(private val data : List<Character>) : RecyclerView.Adapter<CharsAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.title_char)
        val statusTextView: TextView = itemView.findViewById(R.id.status)
        val image: ImageView = itemView.findViewById(R.id.imageView)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.char_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = data[position]

        holder.nameTextView.text =currentItem.name
        holder.statusTextView.text =currentItem.status
        Glide.with(holder.itemView)
            .load(currentItem.image)
            .into(holder.image)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, CharacterActivity::class.java).let {
                it.putExtra("CHAR_EXTRA",currentItem)
            }

            holder.itemView.context.startActivity(intent)
        }

    }
}