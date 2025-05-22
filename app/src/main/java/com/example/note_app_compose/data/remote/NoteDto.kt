package com.example.note_app_compose.data.remote

import com.google.gson.annotations.SerializedName

data class NoteDto(
    @SerializedName("_id")
    val id: String? = null,
    val title: String,
    val content: String
)
