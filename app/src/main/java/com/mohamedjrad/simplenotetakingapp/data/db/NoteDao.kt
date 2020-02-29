package com.mohamedjrad.simplenotetakingapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*

import com.mohamedjrad.simplenotetakingapp.data.model.Note


@Dao
interface NoteDao {

    @Query("SELECT * FROM Note ORDER BY lastModified DESC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM Note WHERE id = :id")
    fun getNoteById(id: Long): LiveData<Note>

    @Insert(onConflict= OnConflictStrategy.ABORT)
    fun insertNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Query("DELETE FROM Note WHERE id = :id")
    fun deleteNoteById(id: Long?)

    @Query("DELETE FROM Note")
    fun deleteAllNotes()

    @Query("SELECT COUNT(id) FROM note ")
    fun getRowCount(): LiveData<Int>


}