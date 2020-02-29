package com.mohamedjrad.simplenotetakingapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime




@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val heading: String = "",
    val content: String = "",
    val tags: List<String> = listOf(),
    val lastModified: LocalDateTime = LocalDateTime.now(),
    val isTrashed: Boolean = false
)