package com.sajjad.githubeventsapp.navigation

sealed class Destinations(val route: String) {
    data object List : Destinations("list")
    data object Detail : Destinations("detail")
}
