package com.example.solospin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "tips",
    foreignKeys = [ForeignKey(
        entity = Technique::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("exerciseId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Tip(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(index = true)
    val exerciseId: Long,
    val tipText: String,
    val rating: Float = 0f
)
