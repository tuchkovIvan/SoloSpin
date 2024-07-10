package com.example.solospin.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.solospin.SoloSpinRepository
import com.example.solospin.Tip
import com.example.solospin.Voiceover
import kotlinx.coroutines.*


@Composable
fun ExerciseTypeButton(repository: SoloSpinRepository, techniqueId: Long){
    var tips by remember { mutableStateOf(listOf<Tip>()) }
    var technique by remember { mutableStateOf("") }
    var isPlaying by remember { mutableStateOf(false) }
    var playbackJob by remember { mutableStateOf<Job?>(null) }
    val coroutineScope = rememberCoroutineScope()
    var buttonColor by remember { mutableStateOf(Color.Gray) }

    LaunchedEffect(Unit){
        tips = repository.getTipsForTechnique(techniqueId+1)
        technique = repository.getAllTechniques()[techniqueId.toInt()].name
    }

    fun startPlayback() {
        playbackJob = coroutineScope.launch {
            while (isActive) {
                for (tip in tips) {
                    Voiceover.convertTextToSpeech(tip.tipText)
                    delay(10000) // Wait for 20 seconds before playing the next tip
                }
            }
        }
        buttonColor = Color.Green
    }

    fun stopPlayback() {
        playbackJob?.cancel()
        playbackJob = null
        coroutineScope.launch {
            buttonColor = Color.Red
            delay(1000) // Change to red for one second
            buttonColor = Color.Gray
        }
    }

    Button(
        modifier = Modifier.fillMaxSize(),
        colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
        onClick = {
            if (isPlaying) {
                stopPlayback()
            } else {
                startPlayback()
            }
            isPlaying = !isPlaying
        }
    ) {
        Text(text = technique, color = Color.White)
    }
}

@Composable
fun ExerciseGrid(repository: SoloSpinRepository, spacing: Dp = 8.dp) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val itemSize = (screenWidth / 3) - (spacing * 2 / 3) // 1/3 of screen width minus spacing adjustment
    var techniquesCount by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        techniquesCount = repository.getAllTechniques().size
    }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        modifier= Modifier
            .fillMaxSize()
            .padding(spacing / 2),
        contentPadding = PaddingValues(spacing / 2),
        verticalArrangement = Arrangement.spacedBy(spacing),
        horizontalArrangement = Arrangement.spacedBy(spacing)

    ) {
        items(techniquesCount) { technique_id ->
            Box(
                modifier = Modifier
                    .size(itemSize)
            ) {
            ExerciseTypeButton(repository, technique_id.toLong())
            }
        }
    }
}