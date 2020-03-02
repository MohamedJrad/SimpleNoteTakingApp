package com.mohamedjrad.simplenotetakingapp.ui.AddEditNote

import android.opengl.Visibility
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.view.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.mohamedjrad.simplenotetakingapp.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.edit_note_fragment.*
import javax.inject.Inject


class AddEditNoteFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val editNoteViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[AddEditNoteViewModel::class.java]
    }
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        editNoteViewModel.noteId = arguments!!.getLong("noteId")


    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.edit_note_fragment, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        toolbar?.setupWithNavController(findNavController())

        initNote()


    }


    private fun initNote() {

        if (editNoteViewModel.noteId != 0L) {

            editNoteViewModel.getEditNote(editNoteViewModel.noteId)
                .observe(viewLifecycleOwner, Observer { note ->
                    val heading: Editable = SpannableStringBuilder(note.heading)
                    headingTXT.text = heading
                    val content: Editable = SpannableStringBuilder(note.content)
                    contentTXT.text = content


                })


        }

        headingTXT.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                editNoteViewModel.onUpdateHeadingAction(s.toString())
            }


            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        contentTXT.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                editNoteViewModel.onUpdateContentAction(s.toString())
            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.edit_note_fragment_menu, menu)
        if (editNoteViewModel.noteId != 0L) {
            menu.findItem(R.id.action_delete).isVisible=true
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> {
                editNoteViewModel.deleteNote()
                navController.navigate(R.id.action_editNoteFragment_to_homeFragment)
            }
            R.id.action_cancel->{
                navController.navigate(R.id.action_editNoteFragment_to_homeFragment)
            }
            R.id.action_save ->{
                editNoteViewModel.saveNote()
                navController.navigate(R.id.action_editNoteFragment_to_homeFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

