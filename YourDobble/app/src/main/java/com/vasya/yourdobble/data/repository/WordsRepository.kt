package com.vasya.yourdobble.data.repository

import android.content.Context
import com.vasya.yourdobble.R

class WordsRepository(
    private val context: Context
) {
    fun getWords(): List<String> {
        return context.resources
            .getStringArray(R.array.names)
            .toList()
    }
}