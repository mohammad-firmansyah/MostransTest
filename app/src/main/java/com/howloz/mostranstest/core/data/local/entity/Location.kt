package com.howloz.mostranstest.core.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "locations",
    indices = [Index(value= ["name"], unique = true)]
)
@Parcelize
data class Location (
    @PrimaryKey(true) val id : Long?,
    @ColumnInfo("name") val name : String,

) : Parcelable

data class LocationAndCharacter(
    @Embedded
    val location: Location,

    @Relation(
        parentColumn = "id",
        entityColumn = "location_id"
    ) val character: Character? = null
)