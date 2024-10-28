package com.example.presentation.ui.screens.currentmatches

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.model.DomainCurrentMatch
import com.example.presentation.theme.LocalDimens
import com.example.presentation.theme.Typography
import com.example.presentation.ui.components.MatchStatus
import com.example.presentation.ui.components.NetworkErrorRetryView
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CurrentMatchesScreen(
    modifier: Modifier = Modifier,
    onItemClick: (String, String) -> Unit
) {
    val viewModel: CurrentMatchesViewModel = hiltViewModel()
    val data = viewModel.viewState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.sideEffect.collectLatest {
            if (it is CurrentMatchesMVIContract.CurrentMatchesSideEffect.NavigateToMatchDetailsScreen) {
                onItemClick(it.matchId, it.matchName)
            }
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        when (data.value) {
            is CurrentMatchesMVIContract.CurrentMatchesViewState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is CurrentMatchesMVIContract.CurrentMatchesViewState.Success -> {
                MatchList(data = (data.value as
                        CurrentMatchesMVIContract.CurrentMatchesViewState.Success).matchList,
                    onClick = { id, name ->
                        viewModel.sendIntent(
                            CurrentMatchesMVIContract.CurrentMatchesViewIntent.OnMatchClicked(
                                id,
                                name
                            )
                        )
                    })
            }

            is CurrentMatchesMVIContract.CurrentMatchesViewState.Failure -> {
                NetworkErrorRetryView {
                    viewModel.sendIntent(CurrentMatchesMVIContract.CurrentMatchesViewIntent.LoadData)
                }
            }

        }
    }
}

@Composable
private fun MatchList(
    data: List<DomainCurrentMatch>,
    onClick: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val dimen = LocalDimens.current
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(data) {
            MatchListItem(match = it, onClick)
            HorizontalDivider(modifier = Modifier.padding(horizontal = dimen.medium))
        }
    }
}

@Composable
private fun MatchListItem(
    match: DomainCurrentMatch,
    onClick: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val dimen = LocalDimens.current
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(all = dimen.medium)
        .background(color = MaterialTheme.colorScheme.background)
        .clickable {
            onClick(match.id, match.name)
        }
    ) {
        val teams = "${match.teams[0]} v/s ${match.teams[1]} - ${match.matchType.uppercase()}"
        MatchStatus(match.matchStarted, match.matchEnded)
        Text(match.name, style = Typography.titleLarge)
        if (!(!match.matchStarted && !match.matchEnded)) {
            Text(match.status, style = Typography.bodyLarge)
        }
        Text(teams, style = Typography.bodyLarge)
        Text(match.venue, style = Typography.bodySmall.copy(color = Color.Gray))
    }
}