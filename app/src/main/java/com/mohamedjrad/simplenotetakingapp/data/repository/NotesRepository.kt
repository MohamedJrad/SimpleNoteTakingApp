package com.mohamedjrad.simplenotetakingapp.data.repository
import androidx.lifecycle.LiveData
import com.mohamedjrad.simplenotetakingapp.data.model.Note

import kotlinx.coroutines.flow.Flow

interface NotesRepository {



    fun insertNote(note: Note)

    fun updateNote(note: Note)

    fun deleteNoteById(id:Long?)

    fun getAllNotes():LiveData<List<Note>>

    fun getNoteById(id: Long):LiveData<Note>

    fun deleteAllNotes()

    fun getRowCount():LiveData<Int>




}