package com.howloz.mostranstest.core.utils


import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.howloz.mostranstest.R
import com.howloz.mostranstest.character.CharacterActivity
import com.howloz.mostranstest.core.data.local.entity.Location
import com.howloz.mostranstest.detailLocation.DetailLocationActivity
import com.howloz.mostranstest.location.LocationActivity

class LocationAdapter(private val context: Context,private val data : List<Location>) : RecyclerView.Adapter<LocationAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.title)
        val imageButton: ImageButton = itemView.findViewById(R.id.delete)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.location_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = data[position]

        holder.nameTextView.text =currentItem.name

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailLocationActivity::class.java).let {
                it.putExtra("ID",currentItem.id.toString())
            }
            Log.d("id-app-adap",currentItem.id.toString())

            holder.itemView.context.startActivity(intent)
        }

        holder.imageButton.setOnClickListener {
            showDialogDelete(currentItem.id!!)
        }

    }

    private fun showDialogDelete(id:Long) {
        AlertDialog.Builder(context)
            .setTitle("Apakah anda yakin hapus data ini ?")
            .setPositiveButton("Yes"){_,_ ->
                val parent = context as LocationActivity
                parent.viewModel.deleteLocation(id)
            }.setNegativeButton("No"){_,_ ->

            }.show()
    }
}