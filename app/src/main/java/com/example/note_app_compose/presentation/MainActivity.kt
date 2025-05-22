package com.example.note_app_compose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.note_app_compose.presentation.add_note.AddNoteScreen
import com.example.note_app_compose.presentation.note_list.NoteListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //AddNoteScreen()
            NoteListScreen()
        }
    }
}
