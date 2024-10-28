package com.example.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.presentation.R
import com.example.presentation.ui.screens.currentmatches.CurrentMatchesScreen
import com.example.presentation.ui.screens.matchdetail.MatchDetailScreen

/**
 * App Navigation Routes
 * List all your screens constant here
 */
object Routes {
    const val LIST_SCREEN = "match_list"
    const val DETAIL_SCREEN = "match_detail"
}

/**
 * Navigation Arguments keys
 */
object ArgKeys {
    const val KEY_DETAIL_MATCH_ID = "key_match_id"
    const val KEY_DETAIL_MATCH_NAME = "key_match_name"
}

val LocalNavController = compositionLocalOf<NavHostController> { error("Error in Navigation") }

@Composable
fun NavGraph(modifier: Modifier, onToolbarTitleChanged: (String) -> Unit) {
    val navController = LocalNavController.current
    NavHost(
        modifier = modifier, navController = navController, startDestination = Routes.LIST_SCREEN
    ) {
        // List Screen
        composable(route = Routes.LIST_SCREEN) {
            onToolbarTitleChanged(stringResource(id = R.string.app_name))
            CurrentMatchesScreen { matchId, matchName ->
                navController.navigate("${Routes.DETAIL_SCREEN}/${matchId}/${matchName}")
            }
        }
        // Details Screen
        composable(route = "${Routes.DETAIL_SCREEN}/{${ArgKeys.KEY_DETAIL_MATCH_ID}}/{${ArgKeys.KEY_DETAIL_MATCH_NAME}}",
            arguments = listOf(
                navArgument(ArgKeys.KEY_DETAIL_MATCH_ID) {
                    type = NavType.StringType
                },
                navArgument(ArgKeys.KEY_DETAIL_MATCH_NAME) {
                    type = NavType.StringType
                }
            )
        ) {
            val charName = it.arguments?.getString(ArgKeys.KEY_DETAIL_MATCH_NAME)
                ?: stringResource(id = R.string.app_name)
            onToolbarTitleChanged(charName)
            MatchDetailScreen()
        }
    }
}