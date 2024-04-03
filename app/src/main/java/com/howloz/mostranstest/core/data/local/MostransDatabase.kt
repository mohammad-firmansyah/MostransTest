package com.howloz.mostranstest.core.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.howloz.mostranstest.core.data.local.entity.Character
import com.howloz.mostranstest.core.data.local.entity.Location

@Database(entities = [Character::class, Location::class], version = 4)
abstract class MostransDatabase : RoomDatabase() {
    abstract fun characterDao():CharacterDao

    companion object{
        private var INSTANCE : MostransDatabase? = null

        fun getDatabaseInstance(context: Context) : MostransDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MostransDatabase::class.java,
                    "mostrans_db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}