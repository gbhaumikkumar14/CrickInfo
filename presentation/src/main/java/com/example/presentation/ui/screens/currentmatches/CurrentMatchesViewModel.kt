package com.example.presentation.ui.screens.currentmatches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.Result
import com.example.domain.usecase.GetCurrentMatchesUseCase
import com.example.presentation.di.IoDispatcher
import com.example.presentation.ui.base.ViewIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentMatchesViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val getCurrentMatchesUseCase: GetCurrentMatchesUseCase,
) : ViewModel(), CurrentMatchesMVIContract {
    init {
        sendIntent(CurrentMatchesMVIContract.CurrentMatchesViewIntent.LoadData)
    }

    private val _viewState = MutableStateFlow(createInitialState())
    override val viewState: StateFlow<CurrentMatchesMVIContract.CurrentMatchesViewState>
        get() = _viewState.asStateFlow()

    private val _sideEffect =
        MutableSharedFlow<CurrentMatchesMVIContract.CurrentMatchesSideEffect>()
    override val sideEffect: Flow<CurrentMatchesMVIContract.CurrentMatchesSideEffect>
        get() = _sideEffect.asSharedFlow()

    override fun sendIntent(intent: ViewIntent) {
        when (intent) {
            is CurrentMatchesMVIContract.CurrentMatchesViewIntent.LoadData -> {
                fetchCurrentMatches()
            }

            is CurrentMatchesMVIContract.CurrentMatchesViewIntent.OnMatchClicked -> {
                viewModelScope.launch {
                    _sideEffect.emit(
                        CurrentMatchesMVIContract.CurrentMatchesSideEffect.NavigateToMatchDetailsScreen(
                            intent.matchId,
                            intent.matchName
                        )
                    )
                }
            }
        }
    }

    override fun createInitialState(): CurrentMatchesMVIContract.CurrentMatchesViewState =
        CurrentMatchesMVIContract.CurrentMatchesViewState.Loading

    private fun fetchCurrentMatches() {
        viewModelScope.launch(dispatcher) {
            when (val result = getCurrentMatchesUseCase.getCurrentMatches()) {
                is Result.Success -> _viewState.emit(
                    CurrentMatchesMVIContract.CurrentMatchesViewState.Success(
                        result.data
                    )
                )

                is Result.Failure -> _viewState.emit(
                    CurrentMatchesMVIContract.CurrentMatchesViewState.Failure(result.exception)
                )

                is Result.Loading -> _viewState.emit(CurrentMatchesMVIContract.CurrentMatchesViewState.Loading)
            }
        }
    }
}