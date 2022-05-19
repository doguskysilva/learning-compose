package com.example.learningcompose.ui.screens

const val DETAIL_PARAM_SCREEN_ARG_ID = "id"

sealed class Screen(val route: String) {
    object Home: Screen(route = "home_screen")
    object Detail: Screen(route = "detail_screen")
    object DetailParam: Screen(route = "detail_param_screen/{$DETAIL_PARAM_SCREEN_ARG_ID}") {
        fun passId(id: Int): String {
            return this.route.replace(
                oldValue = "{$DETAIL_PARAM_SCREEN_ARG_ID}",
                newValue = id.toString()
            )
        }
    }
}
