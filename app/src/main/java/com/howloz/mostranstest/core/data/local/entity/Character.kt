package com.howloz.mostranstest.core.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "characters"
)
@Parcelize
data class Character(
    @PrimaryKey(true) val id: Long?,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("status") val status: String,
    @ColumnInfo("image") val image: String,
    @ColumnInfo("location_id") val location_id: Long?,
    ) : Parcelable

