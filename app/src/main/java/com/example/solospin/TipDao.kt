package com.example.solospin

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TipDao {
    @Query("SELECT * FROM tips WHERE exerciseId = :exerciseId")
    suspend fun getTipsForExercise(exerciseId: Long): List<Tip>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTip(tip: Tip): Long

    @Delete
    suspend fun deleteTip(tip: Tip)

    @Query("SELECT * from tips ORDER BY rating ASC")
    fun getTipsOrderedByRating(): Flow<List<Tip>>
}