package com.example.solospin

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.Voice
import android.util.Log
import java.util.*

object Voiceover : TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null

    fun getInstance(context: Context) {
        if (tts == null) {
            tts = TextToSpeech(context, this)
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts?.let { setLanguageAndVoice(it) }
        } else {
            Log.e("TTS", "Initialization Failed!")
        }
    }

    fun convertTextToSpeech(text: String) {
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    private fun setLanguageAndVoice(tts: TextToSpeech) {
        val result = tts.setLanguage(Locale.US)
        if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
            Log.e("TTS", "The Language specified is not supported!")
        } else {
            val voices: Set<Voice> = tts.voices
            val voiceList: List<Voice> = ArrayList(voices)
            if (voiceList.isNotEmpty()) {
                val selectedVoice = voiceList[2] // Change the index as needed
                tts.voice = selectedVoice
            }
        }
    }

    fun shutdown() {
        tts?.let {
            it.stop()
            it.shutdown()
        }
        tts = null
    }
}
