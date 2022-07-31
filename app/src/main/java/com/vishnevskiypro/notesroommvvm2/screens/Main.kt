package com.vishnevskiypro.notesroommvvm2.screens

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {

    val notes = viewModel.readAllNotes().observeAsState(listOf()).value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(NavRoute.Add.route)
            }) {
                Icon(imageVector = Icons.Filled.Add,
                    contentDescription = "Add Button",
                    tint = Color.White)
            }
        }
    ) {
        LazyColumn{
            items(notes){ note ->
                NoteItem(note = note, navController = navController)

            }
        }

    }

}

@Composable
fun NoteItem(note: Note, navController: NavController){
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
            horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = note.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = note.subtitle
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun prevMainScreen() {
    NotesRoomMVVM2Theme() {
        val context = LocalContext.current
        val mViewModel : MainViewModel =
            viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
        MainScreen(navController = rememberNavController(), viewModel = mViewModel)
    }
}

