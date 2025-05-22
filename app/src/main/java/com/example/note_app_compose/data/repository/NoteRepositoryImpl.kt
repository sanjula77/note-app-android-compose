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
}
