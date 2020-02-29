package com.mohamedjrad.simplenotetakingapp.ui.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mohamedjrad.simplenotetakingapp.data.model.Note
import com.mohamedjrad.simplenotetakingapp.databinding.NoteListItemBinding


class NoteListAdapter(val clickListener: Listener) :
    ListAdapter<Note, NoteListAdapter.NoteViewHolder>(DiffCallback()) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        //val inflater = LayoutInflater.from(parent.context)
        return NoteViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        holder.bind(getItem(position)!!, clickListener)
    }


    class NoteViewHolder private constructor(val binding: NoteListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            note: Note,
            clickListener: Listener
        ) {
            binding.note = note
            binding.clickListener = clickListener
            binding.executePendingBindings()

        }



        companion object {
            fun from(parent: ViewGroup): NoteViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = NoteListItemBinding.inflate(layoutInflater, parent, false)
                return NoteViewHolder(binding)
            }
        }


    }
}

class DiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}

class Listener(val clickListener: (noteId: Long?) -> Unit) {
    fun onClick(note: Note) = clickListener(note.id)
}

