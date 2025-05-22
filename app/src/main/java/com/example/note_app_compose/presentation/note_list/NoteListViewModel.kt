package com.example.note_app_compose.presentation.note_list

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note_app_compose.domain.model.Note
import com.example.note_app_compose.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    private val _notes = mutableStateListOf<Note>()
    val notes: List<Note> = _notes

    init {
        fetchNotes()
    }

    private fun fetchNotes() {
        viewModelScope.launch {
            _notes.clear()
            _notes.addAll(repository.getAllNotes())
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            repository.updateNote(note)
            fetchNotes() // Refresh list
        }
    }

    fun deleteNote(id: String) {
        viewModelScope.launch {
            repository.deleteNote(id)
            fetchNotes() // Refresh list
        }
    }

    fun addNote(title: String, content: String) {
        val newNote = Note(
            id = null, // or UUID if needed
            title = title,
            content = content
        )
        viewModelScope.launch {
            repository.addNote(newNote) // Fixed variable name
            fetchNotes() // Fixed method name
        }
    }

}
