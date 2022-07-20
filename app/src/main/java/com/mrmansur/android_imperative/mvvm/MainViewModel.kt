package com.mrmansur.android_imperative.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mrmansur.android_imperative.model.TVShow
import com.mrmansur.android_imperative.model.TVShowDetails
import com.mrmansur.android_imperative.model.TVShowPopular
import com.mrmansur.android_imperative.repository.TVShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val tvShowRepository: TVShowRepository) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val tvShowsFromApi = MutableLiveData<ArrayList<TVShow>>()

    val tvShowPopular = MutableLiveData<TVShowPopular>()
    val tvShowDetails = MutableLiveData<TVShowDetails>()

    /**
     * Retrofit Related
     */
    fun apiTVShowPopular(page : Int){

    }

    fun apiTVShowDetails(q : Int){

    }

    /**
     * Room Related
     */
}