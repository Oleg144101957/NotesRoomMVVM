package com.vishnevskiypro.notesroommvvm2.screens

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.vishnevskiypro.notesroommvvm2.MainViewModel
import com.vishnevskiypro.notesroommvvm2.MainViewModelFactory
import com.vishnevskiypro.notesroommvvm2.navigation.NavRoute
import com.vishnevskiypro.notesroommvvm2.ui.theme.NotesRoomMVVM2Theme
import com.vishnevskiypro.notesroommvvm2.utils.TYPE_FIREBASE
import com.vishnevskiypro.notesroommvvm2.utils.TYPE_ROOM


@Composable
fun StartScreen(navController: NavController) {

    val context = LocalContext.current
    val mViewModel : MainViewModel =
        viewModel(factory = MainViewModelFactory(context.applicationContext as Application))


    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "What wil we use ?")
            Button(
                onClick = {
                    mViewModel.initDatabase(TYPE_ROOM)
                    navController.navigate(route = NavRoute.Main.route)
                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "Room Database")
            }

            Button(
                onClick = {
                    mViewModel.initDatabase(TYPE_FIREBASE)
                    navController.navigate(route = NavRoute.Main.route)
                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "Firebase Database")
            }

        }

    }

}


@Preview(showBackground = true)
@Composable
fun prevStartScreen() {
    NotesRoomMVVM2Theme {
        StartScreen(navController = rememberNavController())
    }

}