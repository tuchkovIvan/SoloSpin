package com.example.solospin

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface InitialTipDao {
    @Query("SELECT * FROM initial_tips WHERE exerciseId = :exerciseId")
    suspend fun getInitialTipForExercise(exerciseId: Long): InitialTip

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInitialTip(tip: InitialTip): Long

    @Delete
    suspend fun deleteInitialTip(tip: InitialTip)
}