package com.example.presentation.ui.screens.matchdetail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.Result
import com.example.domain.usecase.GetMatchInfoUseCase
import com.example.presentation.di.IoDispatcher
import com.example.presentation.ui.base.SideEffect
import com.example.presentation.ui.base.ViewIntent
import com.example.presentation.ui.base.ViewState
import com.example.presentation.ui.navigation.ArgKeys
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
class MatchDetailViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val getMatchInfoUseCase: GetMatchInfoUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(), MatchDetailMVIContract {
    private val _matchId = mutableStateOf("")
    fun getMatchId(): String {
        return _matchId.value
    }

    init {
        savedStateHandle.get<String>(ArgKeys.KEY_DETAIL_MATCH_ID)?.let {
            _matchId.value = it
            sendIntent(MatchDetailMVIContract.MatchDetailViewIntent.LoadDetails(it))
        }
    }

    override fun createInitialState(): ViewState =
        MatchDetailMVIContract.MatchDetailViewState.Loading

    private val _viewState = MutableStateFlow(createInitialState())
    override val viewState: StateFlow<ViewState>
        get() = _viewState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<MatchDetailMVIContract.MatchDetailSideEffect>()
    override val sideEffect: Flow<SideEffect>
        get() = _sideEffect.asSharedFlow()

    override fun sendIntent(intent: ViewIntent) {
        when (intent) {
            is MatchDetailMVIContract.MatchDetailViewIntent.LoadDetails -> {
                fetchMatchDetails(intent.matchId)
            }
        }
    }

    private fun fetchMatchDetails(id: String) {
        viewModelScope.launch(dispatcher) {
            when (val result = getMatchInfoUseCase.getMatchInfo(id)) {
                is Result.Success -> _viewState.emit(
                    MatchDetailMVIContract.MatchDetailViewState.Success(
                        result.data
                    )
                )

                is Result.Failure -> {
                    _viewState.emit(
                        MatchDetailMVIContract.MatchDetailViewState.Failure(result.exception)
                    )
                }

                is Result.Loading -> {
                    _viewState.emit(MatchDetailMVIContract.MatchDetailViewState.Loading)
                }
            }
        }
    }
}