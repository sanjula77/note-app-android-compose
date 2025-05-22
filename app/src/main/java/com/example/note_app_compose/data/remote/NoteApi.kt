package com.example.note_app_compose.data.remote

import retrofit2.http.Body
import retrofit2.http.POST

interface NoteApi {
    @POST("api/notes")
    suspend fun createNote(@Body note: NoteDto)
}

