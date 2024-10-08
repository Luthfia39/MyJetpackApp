package com.example.myjetpackapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myjetpackapp.data.Repository
import com.example.myjetpackapp.model.Destinations
import com.example.myjetpackapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Destinations>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Destinations>>> = _uiState

    fun getAllDestinations() {
        viewModelScope.launch {
            repository.getAllDestinations()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}
