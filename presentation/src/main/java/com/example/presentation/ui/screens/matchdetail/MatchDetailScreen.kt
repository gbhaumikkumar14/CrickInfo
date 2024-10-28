package com.example.presentation.ui.screens.matchdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.model.DomainMatchInfo
import com.example.presentation.theme.Typography
import com.example.presentation.ui.components.MatchStatus
import com.example.presentation.ui.components.NetworkErrorRetryView

@Composable
fun MatchDetailScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: MatchDetailViewModel = hiltViewModel()
    val viewState = viewModel.viewState.collectAsState()

    Box(modifier = modifier.fillMaxSize()) {
        when (viewState.value) {
            is MatchDetailMVIContract.MatchDetailViewState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is MatchDetailMVIContract.MatchDetailViewState.Success -> {
                MatchDetailView(matchInfo = (viewState.value as
                        MatchDetailMVIContract.MatchDetailViewState.Success).data)
            }

            is MatchDetailMVIContract.MatchDetailViewState.Failure -> {
                NetworkErrorRetryView {
                    viewModel.sendIntent(
                        MatchDetailMVIContract.MatchDetailViewIntent.LoadDetails(
                            viewModel.getMatchId()
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun MatchDetailView(
    matchInfo: DomainMatchInfo,
    modifier: Modifier = Modifier
) {
    with(matchInfo) {
        val teams = "${teams[0]} v/s ${teams[1]} - ${matchType.uppercase()}"
        Column(modifier = modifier.padding(all = 8.dp)) {
            Text(name, style = Typography.titleLarge, modifier = Modifier.padding(bottom = 4.dp))
            MatchStatus(matchStarted, matchEnded, modifier = Modifier.padding(bottom = 4.dp))
            Text(date, style = Typography.bodyMedium)
            Text(venue, style = Typography.bodySmall.copy(color = Color.Gray))
            Spacer(modifier = Modifier.height(16.dp))
            Text(status, style = Typography.titleLarge, modifier = Modifier.padding(bottom = 4.dp))
            Text(teams, style = Typography.bodyLarge)

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            Text(
                "Scorecard",
                style = Typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row {
                Text("Run - ", style = Typography.titleMedium)
                Text(score.first().r.toString(), style = Typography.bodyLarge)
            }
            Row {
                Text("Wicket - ", style = Typography.titleMedium)
                Text(score.first().w.toString(), style = Typography.bodyLarge)
            }
            Row {
                Text("Over - ", style = Typography.titleMedium)
                Text(score.first().o.toString(), style = Typography.bodyLarge)
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            Text(
                "Toss Information",
                style = Typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text("$tossWinner won the toss and choose to $tossChoice first")
        }
    }
}
