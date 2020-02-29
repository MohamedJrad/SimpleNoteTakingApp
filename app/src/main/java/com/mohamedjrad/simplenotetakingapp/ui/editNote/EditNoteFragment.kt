package com.mohamedjrad.simplenotetakingapp.ui.editNote

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mohamedjrad.simplenotetakingapp.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.edit_note_fragment.*
import javax.inject.Inject


class EditNoteFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val editNoteViewModel by lazy {
        ViewModelProvider(this,viewModelFactory)[EditNoteViewModel::class.java]
    }

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

     //   activity!!.supportFragmentManager.popBackStack()


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