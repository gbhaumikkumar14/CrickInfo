package com.example.presentation.ui.screens.matchdetail

import com.example.domain.model.DomainMatchInfo
import com.example.presentation.ui.base.MVIBaseContract
import com.example.presentation.ui.base.SideEffect
import com.example.presentation.ui.base.ViewIntent
import com.example.presentation.ui.base.ViewState

interface MatchDetailMVIContract: MVIBaseContract<ViewState, ViewIntent, SideEffect> {
    sealed interface MatchDetailViewState: ViewState {
        data object Loading : MatchDetailViewState
        class Success(val data: DomainMatchInfo): MatchDetailViewState
        class Failure(val throwable: Throwable): MatchDetailViewState
    }

    sealed interface MatchDetailViewIntent: ViewIntent {
        data class LoadDetails(val matchId: String) : MatchDetailViewIntent
    }

    sealed interface MatchDetailSideEffect : SideEffect
}