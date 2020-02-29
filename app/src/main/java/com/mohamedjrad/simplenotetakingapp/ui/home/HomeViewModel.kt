package com.mohamedjrad.simplenotetakingapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import com.mohamedjrad.simplenotetakingapp.data.model.Note
import com.mohamedjrad.simplenotetakingapp.data.repository.NotesRepositoryImp
import javax.inject.Inject

class HomeViewModel @Inject constructor (private val repository: NotesRepositoryImp) : ViewModel() {



    fun getAllNotes(): LiveData<List<Note>> = repository.getAllNotes()
    fun removeNote(id:Long?)=repository.deleteNoteById(id)

}