package com.mohamedjrad.simplenotetakingapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohamedjrad.simplenotetakingapp.ui.AddEditNote.AddEditNoteViewModel
import com.mohamedjrad.simplenotetakingapp.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap



@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AddEditNoteViewModel::class)
    abstract fun bindEditNoteViewModel(viewModel: AddEditNoteViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}