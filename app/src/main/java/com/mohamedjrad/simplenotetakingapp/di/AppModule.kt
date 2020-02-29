package com.mohamedjrad.simplenotetakingapp.di

import android.app.Application
import androidx.room.Room
import com.mohamedjrad.simplenotetakingapp.data.db.NoteDao
import com.mohamedjrad.simplenotetakingapp.data.db.NotesDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(app: Application)
            : NotesDatabase =
        Room.databaseBuilder(app, NotesDatabase::class.java, "notes.db")
            .fallbackToDestructiveMigration()
            .build()
    @Singleton
    @Provides
    fun provideNotesDao(notesDatabase: NotesDatabase): NoteDao = notesDatabase.notDao()




}