package com.vasya.yourdobble.data.repository

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi

class ProgressRepository (
    context: Context
) {
    val prefs = context.getSharedPreferences("game_progress", Context.MODE_PRIVATE)

    fun getBestEndlessScore(): Int {
        return prefs.getInt("best_endless_score", 0)
    }

    fun getBestTimerScore(): Int {
        return prefs.getInt("best_timer_score", 0)
    }

    fun getBestSprintTime(): Int {
        return prefs.getInt("best_sprint_time", 0)
    }

    fun saveBestEndlessScore(score: Int){
        prefs.edit()
            .putInt("best_endless_score", score)
            .apply()
    }

    fun saveBestTimerScore(score: Int) {
        prefs.edit()
            .putInt("best_timer_score", score)
            .apply()
    }

    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    fun saveBestSprintTime(score: Int) {
        prefs.edit()
            .putInt("best_sprint_time", score)
            .apply()
    }

}