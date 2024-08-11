package com.example.myjetpackapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myjetpackapp.data.Repository
import com.example.myjetpackapp.model.Destinations
import com.example.myjetpackapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: Repository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Destinations>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Destinations>> = _uiState

    fun getDetail(id: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getDestinationById(id))
        }
    }
}