package com.mohamedjrad.simplenotetakingapp.ui.editNote

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.view.*
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.mohamedjrad.simplenotetakingapp.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.edit_note_fragment.*
import javax.inject.Inject


class EditNoteFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val editNoteViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[EditNoteViewModel::class.java]
    }
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        //   activity!!.supportFragmentManager.popBackStack()
        initNote()
        initDeleteBtn()
        initBackBtn()

    }

    private fun initBackBtn() {
        backBTN.setOnClickListener {
            editNoteViewModel.saveNote()
            navController.navigate(R.id.action_editNoteFragment_to_homeFragment)
        }

    }

    private fun initDeleteBtn() {

        if (editNoteViewModel.noteId != 0L) {
            deleteBTN.visibility = View.VISIBLE

            deleteBTN.setOnClickListener {
                editNoteViewModel.deleteNote()
                navController.navigate(R.id.action_editNoteFragment_to_homeFragment)
            }
        }
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


}

