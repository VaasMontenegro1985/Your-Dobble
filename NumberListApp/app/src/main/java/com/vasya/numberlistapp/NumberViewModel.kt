package com.vasya.numberlistapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NumberViewModel : ViewModel() {

    private val _numbers = MutableLiveData<List<Int>>(emptyList())
    val numbers: LiveData<List<Int>> get() = _numbers

    fun addNumber() {
        val currentList = _numbers.value ?: emptyList()
        val newList = currentList + (currentList.size)
        _numbers.value = newList
    }

    fun getNumbersCount(): Int {
        return _numbers.value?.size ?: 0
    }
}