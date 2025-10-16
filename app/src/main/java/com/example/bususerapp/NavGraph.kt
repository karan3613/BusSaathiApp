package com.example.bususerapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.bususerapp.Screen.BusDetailScreen
import com.example.bususerapp.Screen.HomeScreen
import com.example.bususerapp.Screen.LoginScreen
import com.example.bususerapp.Screen.RegisterScreen

@Composable
fun NavGraph(navController : NavHostController){
    NavHost(navController,startDestination = "auth_graph"){
        navigation(
            startDestination = "LoginScreen"  , route = "auth_graph"
        ){
            composable("LoginScreen"){
                LoginScreen(navController)
            }
            composable("RegisterScreen"){
                RegisterScreen(navController)
            }
        }
        navigation(
            startDestination = "HomeScreen" , route = "main_graph"
        ){
            composable("HomeScreen"){
                HomeScreen(navController)
            }
            composable("FollowScreen"){

            }
            composable("BusDetailScreen"){
                BusDetailScreen(navController)
            }
        }
    }

}