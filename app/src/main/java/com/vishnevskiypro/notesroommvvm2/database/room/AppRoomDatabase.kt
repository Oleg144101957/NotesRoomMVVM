package com.vishnevskiypro.notesroommvvm2.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vishnevskiypro.notesroommvvm2.database.room.dao.NoteRoomDao
import com.vishnevskiypro.notesroommvvm2.model.Note
import com.vishnevskiypro.notesroommvvm2.utils.Constants.Keys.NOTE_DATABASE


@Database(entities = [Note::class], version = 1)
abstract class AppRoomDatabase : RoomDatabase(){

    abstract fun getRoomDao() : NoteRoomDao

    companion object{

        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getInstance(context: Context) : AppRoomDatabase {
            return if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppRoomDatabase::class.java,
                    NOTE_DATABASE
                ).build()
                INSTANCE as AppRoomDatabase
            } else INSTANCE as AppRoomDatabase
        }


    }

}