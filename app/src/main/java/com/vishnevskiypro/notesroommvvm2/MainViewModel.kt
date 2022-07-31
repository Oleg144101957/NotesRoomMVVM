package com.vishnevskiypro.notesroommvvm2

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.vishnevskiypro.notesroommvvm2.model.Note
import com.vishnevskiypro.notesroommvvm2.utils.TYPE_FIREBASE
import com.vishnevskiypro.notesroommvvm2.utils.TYPE_ROOM

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val readTest: MutableLiveData<List<Note>> by lazy {
        MutableLiveData<List<Note>>()
    }

    val dbType: MutableLiveData<String> by lazy {
        MutableLiveData<String>(TYPE_ROOM)
    }

    init {
        readTest.value = when(dbType.value){
            TYPE_ROOM -> {
                listOf<Note>(
                    Note(title = "Note1", subtitle = "Subtitle1"),
                    Note(title = "Note2", subtitle = "Subtitle2"),
                    Note(title = "Note3", subtitle = "Subtitle3"),
                    Note(title = "Note4", subtitle = "Subtitle4"),
                )
            }
            TYPE_FIREBASE -> listOf()
            else -> listOf()
        }
    }

    fun initDatabase(type: String) {
        dbType.value = type
        Log.d("checkData", "MainViewModel with type: $type")
    }
}
