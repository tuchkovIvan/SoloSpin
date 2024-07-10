package com.example.solospin

class SoloSpinRepository(
    private val exerciseDao: TechniqueDao,
    private val tipDao: TipDao,
    private val initialTipDao: InitialTipDao) {

    suspend fun getAllTechniques(): List<Technique> = exerciseDao.getAllTechniques()

    suspend fun getTipsForTechnique(exerciseId: Long): List<Tip> = tipDao.getTipsForExercise(exerciseId)

    suspend fun getInitialTipForExercise(exerciseId: Long): InitialTip = initialTipDao.getInitialTipForExercise(exerciseId)

    suspend fun insertTechnique(technique: Technique): Long = exerciseDao.insertTechnique(technique)

    suspend fun isDatabaseEmpty(): Boolean = exerciseDao.getExerciseCount() == 0

    suspend fun insertTip(tip: Tip): Long = tipDao.insertTip(tip)

    suspend fun insertInitialTip(initialTip: InitialTip): Long = initialTipDao.insertInitialTip(initialTip)

    suspend fun deleteTechnique(technique: Technique) = exerciseDao.deleteTechnique(technique)

    suspend fun deleteTip(tip: Tip) = tipDao.deleteTip(tip)

    suspend fun deleteInitialTip(initialTip: InitialTip) = initialTipDao.deleteInitialTip(initialTip)
}