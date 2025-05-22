package com.example.note_app_compose.presentation.add_note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note_app_compose.domain.model.Note
import com.example.note_app_compose.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val repository: NoteRepository
): ViewModel() {

    fun addNote(title: String, content: String) {
        viewModelScope.launch {
            repository.addNote(Note(id = null, title = title, content = content))
        }
    }
}

