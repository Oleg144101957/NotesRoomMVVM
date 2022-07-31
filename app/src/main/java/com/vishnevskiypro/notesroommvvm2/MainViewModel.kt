package com.vishnevskiypro.notesroommvvm2

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vishnevskiypro.notesroommvvm2.database.room.AppRoomDatabase
import com.vishnevskiypro.notesroommvvm2.database.room.repository.RoomRepository
import com.vishnevskiypro.notesroommvvm2.model.Note
import com.vishnevskiypro.notesroommvvm2.utils.REPOSITORY
import com.vishnevskiypro.notesroommvvm2.utils.TYPE_FIREBASE
import com.vishnevskiypro.notesroommvvm2.utils.TYPE_ROOM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val context = application

    fun initDatabase(type: String, onSuccess: () -> Unit) {
        Log.d("checkData", "MainViewModel with type: $type")

        when (type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context = context).getRoomDao()
                REPOSITORY = RoomRepository(dao)
                onSuccess()
            }
        }
    }

    fun addNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.create(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun updateNote(note: Note, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.update(note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun deleteNote(note: Note, onSuccess: () -> Unit){
        viewModelScope.launch (Dispatchers.IO){
            REPOSITORY.delete(note) {
                viewModelScope.launch (Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }


    fun readAllNotes() = REPOSITORY.readAll


}
