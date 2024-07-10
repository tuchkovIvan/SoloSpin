package com.example.solospin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SoloSpinViewModel(private val repository: SoloSpinRepository) : ViewModel() {

    private val _techniques = MutableStateFlow<List<Technique>>(emptyList())
    val techniques: StateFlow<List<Technique>> = _techniques

    private val _tips = MutableStateFlow<List<Tip>>(emptyList())
    val tips: StateFlow<List<Tip>> = _tips

    private val _initialTip = MutableStateFlow<InitialTip?>(null)
    val initialTip: StateFlow<InitialTip?> = _initialTip

    fun loadExercises() {
        viewModelScope.launch {
            _techniques.value = repository.getAllTechniques()
        }
    }

    fun loadTipsForExercise(exerciseId: Long) {
        viewModelScope.launch {
            _tips.value = repository.getTipsForTechnique(exerciseId)
        }
    }

    fun loadInitialTipForExercise(exerciseId: Long) {
        viewModelScope.launch {
            _initialTip.value = repository.getInitialTipForExercise(exerciseId)
        }
    }

    fun addExercise(name: String) {
        viewModelScope.launch {
            val technique = Technique(name = name)
            repository.insertTechnique(technique = technique)
            loadExercises()
        }
    }

    fun addTip(exerciseId: Long, tipText: String) {
        viewModelScope.launch {
            val tip = Tip(exerciseId = exerciseId, tipText = tipText, rating = 1f)
            repository.insertTip(tip)
            loadTipsForExercise(exerciseId)
        }
    }

    fun addInitialTip(exerciseId: Long, tipText: String) {
        viewModelScope.launch {
            val initialTip = InitialTip(exerciseId = exerciseId, tipText = tipText)
            repository.insertInitialTip(initialTip)
            loadInitialTipForExercise(exerciseId)
        }
    }

}
