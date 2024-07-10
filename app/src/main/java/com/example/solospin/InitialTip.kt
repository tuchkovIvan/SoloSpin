package com.example.solospin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "initial_tips",
    foreignKeys = [ForeignKey(
        entity = Technique::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("exerciseId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class InitialTip(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(index = true)
    val exerciseId: Long,
    val tipText: String,
)
