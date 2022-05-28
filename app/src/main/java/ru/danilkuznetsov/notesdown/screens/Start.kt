package ru.danilkuznetsov.notesdown.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.danilkuznetsov.notesdown.navig.NavRoute
import ru.danilkuznetsov.notesdown.ui.theme.NotesDownTheme

@Composable
fun StartScreen(navController: NavHostController) {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Use")
            Button(
                onClick = {
                          navController.navigate(route = NavRoute.Main.route)
                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)

            ) {
                Text(text = "Room")
            }
            Button(
                onClick = {
                    navController.navigate(route = NavRoute.Main.route)
                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)

            ) {
                Text(text = "Firebase")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun prevStartScreen(){
    NotesDownTheme {
        StartScreen(navController = rememberNavController())
    }
}