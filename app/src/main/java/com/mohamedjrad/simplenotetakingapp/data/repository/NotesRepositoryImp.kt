package com.mohamedjrad.simplenotetakingapp.data.repository

import androidx.lifecycle.LiveData
import com.mohamedjrad.simplenotetakingapp.data.db.NoteDao
import com.mohamedjrad.simplenotetakingapp.data.model.Note

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject


class NotesRepositoryImp @Inject constructor (private val dao: NoteDao):NotesRepository  {



   override  fun insertNote(note: Note) {

        GlobalScope.launch { dao.insertNote(note) }
    }

    override  fun updateNote(note: Note) {
        GlobalScope.launch {dao.updateNote(note) }
    }

    override  fun deleteNoteById(id: Long?) {
        GlobalScope.launch { dao.deleteNoteById(id) }
    }

    override  fun getAllNotes(): LiveData<List<Note>> = dao.getAllNotes()

    override fun getNoteById(id: Long): LiveData<Note> = dao.getNoteById(id)

    override fun deleteAllNotes() {
        GlobalScope.launch {
            dao.deleteAllNotes()
        }
    }

    override fun getRowCount(): LiveData<Int> = dao.getRowCount()
}





