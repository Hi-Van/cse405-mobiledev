package com.example.lab4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel : ViewModel() {

    private var showxquery: MutableLiveData<String> = MutableLiveData("https://collectionapi.metmuseum.org/public/collection/v1/objects/436176")

    fun lastQuery() : LiveData<String> {
        return showxquery
    }

    fun getRequest() {
        showxquery.value = "https://collectionapi.metmuseum.org/public/collection/v1/objects/436176"
    }

    fun reset() {
        showxquery.value = ""
    }
}