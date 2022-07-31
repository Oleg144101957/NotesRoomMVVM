package com.vishnevskiypro.notesroommvvm2.screens

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.vishnevskiypro.notesroommvvm2.MainViewModel
import com.vishnevskiypro.notesroommvvm2.MainViewModelFactory
import com.vishnevskiypro.notesroommvvm2.model.Note
import com.vishnevskiypro.notesroommvvm2.navigation.NavRoute
import com.vishnevskiypro.notesroommvvm2.ui.theme.NotesRoomMVVM2Theme
import com.vishnevskiypro.notesroommvvm2.utils.Constants

@Composable
fun AddScreen(navController: NavController, viewModel: MainViewModel) {


    var title by remember { mutableStateOf("") }
    var subtitle by remember { mutableStateOf("") }
    var isButtonAddEnabled by remember { mutableStateOf(false) }

    Scaffold() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = Constants.Keys.ADD_NEW_NOTE,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )


            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                    isButtonAddEnabled = title.isNotEmpty() && subtitle.isNotEmpty()
                },
                label = { Text(text = Constants.Keys.NOTE_TITLE) },
                isError = title.isEmpty()
            )

            OutlinedTextField(
                value = subtitle,
                onValueChange = {
                    subtitle = it
                    isButtonAddEnabled = title.isNotEmpty() && subtitle.isNotEmpty()
                },
                label = { Text(text = Constants.Keys.NOTE_SUBTITLE) },
                isError = subtitle.isEmpty()
            )
            
            Button(
                modifier = Modifier.padding(top = 16.dp),
                enabled = isButtonAddEnabled,
                onClick = {
                    viewModel.addNote(note = Note(title = title, subtitle = subtitle)){
                        navController.navigate(NavRoute.Main.route)
                    }
                }
            ) {
                Text(text = Constants.Keys.ADD_NEW_NOTE)
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun prevAddScreen(){
    NotesRoomMVVM2Theme() {
        val context = LocalContext.current
        val mViewModel : MainViewModel =
            viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
        AddScreen(navController = rememberNavController(), viewModel = mViewModel)
    }
}