package com.example.solospin

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TechniqueDao {
    @Query("SELECT COUNT(*) FROM techniques")
    suspend fun getExerciseCount(): Int

    @Query("SELECT * FROM techniques")
    suspend fun getAllTechniques(): List<Technique>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTechnique(tip: Technique): Long

    @Delete
    suspend fun deleteTechnique(tip: Technique)
}