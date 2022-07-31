package com.vishnevskiypro.notesroommvvm2

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.vishnevskiypro.notesroommvvm2.database.room.AppRoomDatabase
import com.vishnevskiypro.notesroommvvm2.database.room.repository.RoomRepository
import com.vishnevskiypro.notesroommvvm2.model.Note
import com.vishnevskiypro.notesroommvvm2.utils.REPOSITORY
import com.vishnevskiypro.notesroommvvm2.utils.TYPE_FIREBASE
import com.vishnevskiypro.notesroommvvm2.utils.TYPE_ROOM

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val context = application

    fun initDatabase(type: String, onSuccess : () -> Unit) {
        Log.d("checkData", "MainViewModel with type: $type")

        when (type){
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context = context).getRoomDao()
                REPOSITORY = RoomRepository(dao)
                onSuccess()
        }
        }

    }
}
