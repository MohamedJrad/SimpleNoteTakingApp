package com.mohamedjrad.simplenotetakingapp.di


import com.mohamedjrad.simplenotetakingapp.ui.AddEditNote.AddEditNoteFragment
import com.mohamedjrad.simplenotetakingapp.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeEditNoteFragment(): AddEditNoteFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment
}