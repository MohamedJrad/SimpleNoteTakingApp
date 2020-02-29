package com.mohamedjrad.simplenotetakingapp.ui.editNote

import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mohamedjrad.simplenotetakingapp.data.model.Note

import com.mohamedjrad.simplenotetakingapp.data.repository.NotesRepositoryImp
import java.time.LocalDateTime
import javax.inject.Inject

class EditNoteViewModel @Inject constructor(private val repository: NotesRepositoryImp) :
    ViewModel() {


    private var note = Note()
    var noteId: Long = 0L


    fun onUpdateHeadingAction(heading: String) {

        note = note.copy(heading = heading)

    }

    fun onUpdateContentAction(content: String) {
        note = note.copy(content = content)

    }

    fun getEditNote(id: Long): LiveData<Note> =
        repository.getNoteById(id)

    fun saveNote() {


        // Don't allow saving note with nothing within it.
        if (note.heading.isBlank() && note.content.isBlank()) {
            return
        }

        // Blank headings are replaced by 'Untitled' and the last modified time is set to the time when saving occurs.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            note = note.copy(
                heading = if (note.heading.isBlank()) {
                    "Untitled"
                } else {
                    note.heading
                }, lastModified = LocalDateTime.now()
            )
        }
        // The actual inserting/updating part.
        when (noteId) {
            0L -> repository.insertNote(note)
            else -> repository.updateNote(note.copy(noteId))
        }


    }

    fun deleteNote() {
        val id = this.noteId
        repository.deleteNoteById(id)
    }

    override fun onCleared() {
        super.onCleared()
        saveNote()
    }

}



