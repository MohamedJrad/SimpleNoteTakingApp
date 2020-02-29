package com.mohamedjrad.simplenotetakingapp.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation

import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.leinardi.android.speeddial.SpeedDialActionItem
import com.leinardi.android.speeddial.SpeedDialView
import com.mohamedjrad.simplenotetakingapp.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


class HomeFragment : DaggerFragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val homeViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
    }

    private lateinit var navController: NavController


    private var columnCount = 1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        navController = Navigation.findNavController(view)
        // initSpeedDial(savedInstanceState == null)
        fab.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_editNoteFragment)
        }



        setupRecyclerView()


    }


    private fun setupRecyclerView() {
        val adapter = NoteListAdapter(Listener {
            val bundle = bundleOf("noteId" to it)
            navController.navigate(R.id.action_homeFragment_to_editNoteFragment, bundle)
        })

        recycler_view.layoutManager = when {
            columnCount <= 1 -> LinearLayoutManager(context)
            else -> GridLayoutManager(context, columnCount)
        }


        homeViewModel.getAllNotes().observe(viewLifecycleOwner, Observer {
            it.let {
                adapter.submitList(it)
            }
        })
        recycler_view.adapter = adapter

        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(
                positionStart: Int,
                itemCount: Int
            ) {
                recycler_view.scrollToPosition(0)
            }

            override fun onItemRangeRemoved(
                positionStart: Int,
                itemCount: Int
            ) {
                recycler_view.smoothScrollToPosition(itemCount)
            }
        })


    }

/*
    private fun initSpeedDial(addActionItems: Boolean) {
        if (addActionItems) {
            speedDial.addActionItem(
                SpeedDialActionItem.Builder(
                    R.id.fab_no_label,
                    R.drawable.ic_border_color_white_24dp
                ).create()
            )
        }

        speedDial.setOnActionSelectedListener(SpeedDialView.OnActionSelectedListener { actionItem ->
            when (actionItem.id) {
                R.id.fab_no_label -> {


                    navController.navigate(R.id.action_homeFragment_to_editNoteFragment)

                    speedDial.close() // To close the Speed Dial with animation
                    return@OnActionSelectedListener true // false will close it without animation
                }
            }
            false
        })

    }

 */
}