package ru.danilkuznetsov.notesdown.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.danilkuznetsov.notesdown.navig.NavRoute
import ru.danilkuznetsov.notesdown.ui.theme.NotesDownTheme

@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(NavRoute.Add.route)
                }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Icons", tint = Color.White)
            }
        }
    ) {
        Column() {
            NoteItem(title = "1", subtitle = "1 a",navController = navController)
            NoteItem(title = "2", subtitle = "2 a",navController = navController)
            NoteItem(title = "3", subtitle = "3 a",navController = navController)
            NoteItem(title = "4", subtitle = "4 a",navController = navController)
        }
    }
}

@Composable
fun NoteItem(title: String, subtitle: String, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 24.dp)
            .clickable {
                navController.navigate(NavRoute.Note.route)
            },
        elevation = 6.dp
    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = subtitle)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun prevMaintScreen(){
    NotesDownTheme {
        MainScreen(navController = rememberNavController())
    }
}
