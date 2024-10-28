package com.example.presentation.ui.screens.currentmatches

import com.example.domain.model.DomainCurrentMatch
import com.example.presentation.ui.base.MVIBaseContract
import com.example.presentation.ui.base.SideEffect
import com.example.presentation.ui.base.ViewIntent
import com.example.presentation.ui.base.ViewState

interface CurrentMatchesMVIContract: MVIBaseContract<ViewState, ViewIntent, SideEffect> {
    sealed interface CurrentMatchesViewState: ViewState {
        data object Loading : CurrentMatchesViewState

        class Success(val matchList: List<DomainCurrentMatch>): CurrentMatchesViewState

        class Failure(val throwable: Throwable): CurrentMatchesViewState
    }

    sealed interface CurrentMatchesViewIntent: ViewIntent {
        data object LoadData: CurrentMatchesViewIntent

        class OnMatchClicked(val matchId: String, val matchName: String): CurrentMatchesViewIntent
    }

    sealed interface CurrentMatchesSideEffect : SideEffect {
        class NavigateToMatchDetailsScreen(val matchId: String, val matchName: String): CurrentMatchesSideEffect
    }
}