package com.example.shopifieshop.ui.AppNav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.studentcrud.ui.screens.ProductScreen
import com.example.studentcrud.ui.screens.FavouriteScreen
import com.example.shopifieshop.ui.cart.CartScreen

sealed class Screen(val route: String, val label: String) {
    object Home : Screen("home", "Home")
    object Cart : Screen("cart", "Cart")
    object Favorites : Screen("favorites", "Favorites")
}

@Composable
fun ProductApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) { ProductScreen(navController) }
        composable(Screen.Cart.route) { CartScreen(navController) }
        composable(Screen.Favorites.route) { FavouriteScreen(navController) }
    }
}

@Composable
fun ProductBottomBar(navController: NavController) {
    val items = listOf(Screen.Home, Screen.Cart, Screen.Favorites)
    val icons = mapOf(
        Screen.Home.route to (Icons.Default.Search to Icons.Default.Search),
        Screen.Cart.route to (Icons.Default.ShoppingCart to Icons.Default.ShoppingCart),
        Screen.Favorites.route to (Icons.Default.FavoriteBorder to Icons.Default.Favorite)
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { screen ->
            val selected = currentRoute == screen.route
            val (unselectedIcon, selectedIcon) = icons[screen.route]!!

            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        if (selected) selectedIcon else unselectedIcon,
                        contentDescription = screen.label
                    )
                },
                label = { Text(screen.label) }
            )
        }
    }
}