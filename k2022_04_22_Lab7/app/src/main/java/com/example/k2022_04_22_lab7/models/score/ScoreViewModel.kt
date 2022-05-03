package com.example.k2022_04_22_lab7.models.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel : ViewModel() {

    private var _score: MutableLiveData<Int> = MutableLiveData(0)

    fun incremenet() {
        _score.value = _score.value?.plus(1)
    }

    fun decrement() {

        if (_score.value!! >= 1) {
            _score.value = _score.value?.minus(1)
        }

    }

    fun zero() {
        _score.value = 0
    }

    fun getScore(): Int {
        return _score.value!!
    }

}