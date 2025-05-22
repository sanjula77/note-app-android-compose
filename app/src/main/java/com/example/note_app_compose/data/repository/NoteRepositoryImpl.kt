package com.example.note_app_compose.data.repository

import com.example.note_app_compose.data.remote.NoteApi
import com.example.note_app_compose.data.remote.NoteDto
import com.example.note_app_compose.domain.model.Note
import com.example.note_app_compose.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val api: NoteApi
): NoteRepository {
    override suspend fun addNote(note: Note) {
        api.createNote(NoteDto(title = note.title, content = note.content))
    }

    override suspend fun getAllNotes(): List<Note> {
        return api.getAllNotes().map { dto ->
            Note(
                id = dto.id,  // ✅ Map id from DTO
                title = dto.title,
                content = dto.content
            )
        }
    }

    override suspend fun updateNote(note: Note) {
        val id = note.id ?: throw IllegalArgumentException("Note ID is required for update")
        // Pass only title and content in DTO body
        api.updateNote(id, NoteDto(title = note.title, content = note.content))
    }

    override suspend fun deleteNote(id: String) {
        val response = api.deleteNote(id)
        if (!response.isSuccessful) {
            throw Exception("Delete failed with code: ${response.code()}")
        }
    }

}
