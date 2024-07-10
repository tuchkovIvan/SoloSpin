package com.example.solospin

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Tip::class, InitialTip::class, Technique::class],
    version = 1
)
abstract class SoloSpinDatabase: RoomDatabase() {
    abstract fun techniqueDao(): TechniqueDao
    abstract fun tipDao(): TipDao
    abstract fun initialTipDao(): InitialTipDao
}

suspend fun initializeDatabase(repository: SoloSpinRepository) {
    if (repository.isDatabaseEmpty()) {
        val techniques = mapOf(
            "forehand-topspin-default" to listOf("Keep your arm straight when hitting topspin.", "amogus"),
            "forehand-topspin-over-topspin" to listOf("Focus on the wrist movement."),
            "forehand-topspin-over-backspin" to listOf("Bend your knees slightly."),
            "forehand-drive" to listOf("Use a circular motion."),
            "forehand-flick" to listOf("Maintain a low center of gravity."),
            "backhand-topspin-default" to listOf("Stay relaxed and avoid tension."),
            "backhand-topspin-over-topspin" to listOf("Use your legs for power."),
            "backhand-topspin-over-backspin" to listOf("Keep your eyes on the ball."),
            "backhand-drive" to listOf("Follow through with your stroke."),
            "backhand-flick" to listOf("Keep your grip firm but relaxed.")
        )

        for (technique in techniques.keys) {
            val exerciseId = repository.insertTechnique(Technique(name = technique))

            for (tipText in techniques[technique]!!) {
                repository.insertTip(Tip(exerciseId = exerciseId, tipText = tipText))
            }
        }
    }
}