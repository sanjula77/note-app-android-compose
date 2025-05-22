package com.example.note_app_compose.data.remote

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.Response

interface NoteApi {
    @POST("api/notes")
    suspend fun createNote(@Body note: NoteDto)

    @GET("api/notes")
    suspend fun getAllNotes(): List<NoteDto>

    @PUT("api/notes/{id}")
    suspend fun updateNote(@Path("id") id: String, @Body noteDto: NoteDto)

    @DELETE("api/notes/{id}")
    suspend fun deleteNote(@Path("id") id: String): Response<Unit>
}
