package io.github.devlcc.ota.ui.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.devlcc.core.model.ChallengeActivity
import io.github.devlcc.ota.R
import io.github.devlcc.ota.ui.presentation.home.HomeScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainNavigationView(
    modifier: Modifier,
) {
    val mainNavController = rememberNavController()

    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = mainNavController,
        startDestination = MainDestination.Home.path
    ) {

        // "/home"
        composable(
            route = MainDestination.Home.path,
        ) {
            HomeScreen(
                modifier = Modifier.fillMaxSize(),
                viewModel = koinViewModel(),
                onNavigateToActivity = { which: ChallengeActivity ->
                    val activityId = which.id ?: return@HomeScreen
                    mainNavController.navigate(
                        route = MainDestination.ActivityDetail(id = activityId).argPath,
                    ) {
                        popUpTo(MainDestination.Home.path) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToJourney = {
                    mainNavController.navigate(
                        route = MainDestination.Journey.path,
                    ) {
                        popUpTo(MainDestination.Home.path) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                onNavigateToMyAccount = {
                    mainNavController.navigate(
                        route = MainDestination.MyAccount.path,
                    ) {
                        popUpTo(MainDestination.Home.path) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        // "/myaccount"
        composable(
            route = MainDestination.MyAccount.path,
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.weight(1f))
                Image(
                    modifier = Modifier.size(128.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_myaccount_round_thumbnail),
                    contentDescription = null,
                )

                Text(
                    modifier = Modifier,
                    fontSize = 18.sp,
                    text = "My Account Screen",
                )
                Spacer(Modifier.weight(1f))
            }
        }

        // "/journey"
        composable(
            route = MainDestination.Journey.path,
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.weight(1f))
                Image(
                    modifier = Modifier.size(128.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_journey_status),
                    contentDescription = null,
                )

                Text(
                    modifier = Modifier,
                    fontSize = 18.sp,
                    text = "Journey Screen",
                )
                Spacer(Modifier.weight(1f))
            }
        }

        // "/activity/{id}"
        composable(
            route = MainDestination.ActivityDetail.path,
        ) { navBackstackEntry ->
            val activityId = navBackstackEntry.arguments?.getString(MainDestination.ActivityDetail.Args.Id) ?: "---"

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.weight(1f))
                Image(
                    modifier = Modifier.size(128.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_journey_status),
                    contentDescription = null,
                )

                Text(
                    modifier = Modifier,
                    fontSize = 18.sp,
                    text = "Activity Detail Screen",
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 32.dp),
                    fontSize = 14.sp,
                    text = "ID: $activityId",
                )
                Spacer(Modifier.weight(1f))
            }
        }


    }

}