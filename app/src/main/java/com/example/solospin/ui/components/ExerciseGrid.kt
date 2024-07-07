package com.example.solospin.ui.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.solospin.Voiceover


@Composable
fun ExerciseTypeButton(exercise_id: String){
       Button(modifier= Modifier.fillMaxSize(),onClick = {
           Log.d("lazy_grid", "button pressed")
           Voiceover.convertTextToSpeech(exercise_id)
       }) {
            Text(text = exercise_id, color= Color.White)
        }
}

@Composable
fun ExerciseGrid(buttons: List<String>, spacing: Dp = 8.dp) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val itemSize = (screenWidth / 3) - (spacing * 2 / 3) // 1/3 of screen width minus spacing adjustment

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        modifier= Modifier
            .fillMaxSize()
            .padding(spacing / 2),
        contentPadding = PaddingValues(spacing / 2),
        verticalArrangement = Arrangement.spacedBy(spacing),
        horizontalArrangement = Arrangement.spacedBy(spacing)

    ) {
        items(buttons.size) { button_id ->
            Box(
                modifier = Modifier
                    .size(itemSize)
            ) {
            ExerciseTypeButton(buttons[button_id])
            }
        }
    }
}