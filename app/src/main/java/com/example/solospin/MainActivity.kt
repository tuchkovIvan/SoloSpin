package com.example.solospin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.solospin.ui.components.ExerciseGrid
import com.example.solospin.ui.theme.SoloSpinTheme


val LIST_OF_EXERCISES: List<String> = listOf("Hello, my name is Roman, I am senior video editor and UI/UX designer", "forehand-topspin-default", "forehand-topspin-over-topspin", "forehand-topspin-over-backspin", "forehand-drive", "forehand-flick",
    "backhand-topspin-default", "backhand-topspin-over-topspin", "backhand-topspin-over-backspin", "backhand-drive", "backhand-flick")


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Voiceover.getInstance(this)

        setContent {
            SoloSpinTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ExerciseGrid(LIST_OF_EXERCISES)
                }
            }
        }
    }

    override fun onDestroy() {
        Voiceover.shutdown()
        super.onDestroy()
    }
}
