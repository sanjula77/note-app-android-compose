package com.example.note_app_compose.domain.repository

import com.example.note_app_compose.domain.model.Note

interface NoteRepository {
    suspend fun addNote(note: Note)
    suspend fun getAllNotes(): List<Note>
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(id: String)
}


