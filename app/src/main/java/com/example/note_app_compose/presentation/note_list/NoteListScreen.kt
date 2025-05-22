package com.example.note_app_compose.presentation.note_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.note_app_compose.domain.model.Note
import androidx.compose.runtime.*

@Composable
fun NoteListScreen(viewModel: NoteListViewModel = hiltViewModel()) {
    val notes = viewModel.notes

    var editingNote by remember { mutableStateOf<Note?>(null) }
    var titleInput by remember { mutableStateOf("") }
    var contentInput by remember { mutableStateOf("") }
    var isDialogOpen by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    editingNote = null // It's a new note
                    titleInput = ""
                    contentInput = ""
                    isDialogOpen = true
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Note")
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier
            .padding(paddingValues)
            .padding(16.dp)) {
            items(notes) { note ->
                NoteItem(
                    note = note,
                    onUpdate = {
                        editingNote = note
                        titleInput = note.title
                        contentInput = note.content
                        isDialogOpen = true
                    },
                    onDelete = {
                        viewModel.deleteNote(note.id ?: "")
                    }
                )
            }
        }

        if (isDialogOpen) {
            AlertDialog(
                onDismissRequest = { isDialogOpen = false },
                title = { Text(if (editingNote == null) "Add Note" else "Update Note") },
                text = {
                    Column {
                        OutlinedTextField(
                            value = titleInput,
                            onValueChange = { titleInput = it },
                            label = { Text("Title") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = contentInput,
                            onValueChange = { contentInput = it },
                            label = { Text("Content") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        if (editingNote == null) {
                            viewModel.addNote(titleInput, contentInput)
                        } else {
                            viewModel.updateNote(
                                editingNote!!.copy(title = titleInput, content = contentInput)
                            )
                        }
                        isDialogOpen = false
                    }) {
                        Text("Save")
                    }
                },
                dismissButton = {
                    Button(onClick = { isDialogOpen = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}

//@Composable
//fun NoteListScreen(viewModel: NoteListViewModel = hiltViewModel()) {
//    val notes = viewModel.notes
//
//    // State to hold which note is being edited (null if none)
//    var noteBeingEdited by remember { mutableStateOf<Note?>(null) }
//    var updatedTitle by remember { mutableStateOf("") }
//    var updatedContent by remember { mutableStateOf("") }
//
//    LazyColumn(modifier = Modifier.padding(16.dp)) {
//        items(notes) { note ->
//            NoteItem(
//                note = note,
//                onUpdate = {
//                    // Open dialog with current values filled
//                    noteBeingEdited = note
//                    updatedTitle = note.title
//                    updatedContent = note.content
//                },
//                onDelete = {
//                    viewModel.deleteNote(note.id ?: "")
//                }
//            )
//        }
//    }
//
//    // Show update dialog if a note is selected for editing
//    if (noteBeingEdited != null) {
//        AlertDialog(
//            onDismissRequest = { noteBeingEdited = null },
//            title = { Text("Update Note") },
//            text = {
//                Column {
//                    OutlinedTextField(
//                        value = updatedTitle,
//                        onValueChange = { updatedTitle = it },
//                        label = { Text("Title") }
//                    )
//                    OutlinedTextField(
//                        value = updatedContent,
//                        onValueChange = { updatedContent = it },
//                        label = { Text("Content") },
//                        modifier = Modifier.padding(top = 8.dp)
//                    )
//                }
//            },
//            confirmButton = {
//                Button(onClick = {
//                    // Call update on viewModel with new values
//                    noteBeingEdited?.let {
//                        viewModel.updateNote(it.copy(title = updatedTitle, content = updatedContent))
//                    }
//                    noteBeingEdited = null
//                }) {
//                    Text("Save")
//                }
//            },
//            dismissButton = {
//                Button(onClick = { noteBeingEdited = null }) {
//                    Text("Cancel")
//                }
//            }
//        )
//    }
//}

@Composable
fun NoteItem(
    note: Note,
    onUpdate: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = note.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = note.content, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Button(onClick = onUpdate) {
                    Text("Update")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = onDelete, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)) {
                    Text("Delete")
                }
            }
        }
    }
}
