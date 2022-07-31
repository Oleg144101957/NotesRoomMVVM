package com.vishnevskiypro.notesroommvvm2.database.room.repository

import androidx.lifecycle.LiveData
import com.vishnevskiypro.notesroommvvm2.database.DatabaseRepository
import com.vishnevskiypro.notesroommvvm2.database.room.dao.NoteRoomDao
import com.vishnevskiypro.notesroommvvm2.model.Note

class RoomRepository(private val noteRoomDao: NoteRoomDao) : DatabaseRepository{
    override val readAll: LiveData<List<Note>>
        get() = noteRoomDao.getAllNotes()

    override suspend fun create(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.addNote(note)
    }

    override suspend fun update(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.updateNote(note)
    }

    override suspend fun delete(note: Note, onSuccess: () -> Unit) {
        noteRoomDao.deleteNote(note)
    }
}