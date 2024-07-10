package com.example.solospin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.example.solospin.ui.components.ExerciseGrid
import com.example.solospin.ui.theme.SoloSpinTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    lateinit var database: SoloSpinDatabase
    lateinit var repository: SoloSpinRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Voiceover.getInstance(this)
        database = Room.databaseBuilder(applicationContext, SoloSpinDatabase::class.java, "solospin_database").build()
        repository = SoloSpinRepository(database.techniqueDao(), database.tipDao(), database.initialTipDao())

        CoroutineScope(Dispatchers.IO).launch {
            initializeDatabase(repository = repository)
        }

        setContent {
            SoloSpinTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ExerciseGrid(repository)
                }
            }
        }
    }

    override fun onDestroy() {
        Voiceover.shutdown()
        super.onDestroy()
    }
}
