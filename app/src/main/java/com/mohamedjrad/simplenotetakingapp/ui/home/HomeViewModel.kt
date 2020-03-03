package com.mohamedjrad.simplenotetakingapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mohamedjrad.simplenotetakingapp.R

import com.mohamedjrad.simplenotetakingapp.data.model.Note
import com.mohamedjrad.simplenotetakingapp.data.repository.NotesRepositoryImp
import java.util.function.BiFunction
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: NotesRepositoryImp) : ViewModel() {


    fun getAllNotes(): LiveData<List<Note>> = repository.getAllNotes()

    fun removeNote(id: Long?) = repository.deleteNoteById(id)


    fun getFilteredList(s: String): LiveData<List<Note>> {

        return Transformations.map(getAllNotes()) {
            it.filter {
                it.heading.contains(s.toLowerCase())

            }
        }
    }


}