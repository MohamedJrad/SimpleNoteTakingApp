package com.mohamedjrad.simplenotetakingapp.ui.home

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.mohamedjrad.simplenotetakingapp.data.model.Note

@BindingAdapter("heading")
fun TextView.setHeading(item: Note?){
    item?.let {
        text=item.heading
    }
}

@BindingAdapter("content")
fun TextView.setContent(item:Note?){
    item?.let {
        text=item.content
    }
}