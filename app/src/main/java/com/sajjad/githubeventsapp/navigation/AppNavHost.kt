package com.sajjad.githubeventsapp.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sajjad.feature_events.presentation.EventsViewModel
import com.sajjad.feature_events.presentation.detail.EventDetailScreen
import com.sajjad.feature_events.presentation.list.EventsScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = Destinations.List.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Destinations.List.route) { backStackEntry ->
            val vm: EventsViewModel = hiltViewModel(backStackEntry)
            EventsScreen(
                vm = vm,
                onEventClick = { id ->
                    vm.selectEventById(id)
                    navController.navigate(Destinations.Detail.route)
                }
            )
        }

        composable(Destinations.Detail.route) {
            val parentEntry = navController.getBackStackEntry(Destinations.List.route)
            val vm: EventsViewModel = hiltViewModel(parentEntry)

            val event = vm.selectedEvent.value
            if (event != null) {
                EventDetailScreen(event)
            } else {
                Text("Event not found")
            }
        }
    }
}
