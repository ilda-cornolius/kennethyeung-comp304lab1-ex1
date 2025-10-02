package com.example.kennethyeung_comp304lab1_ex1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kennethyeung_comp304lab1_ex1.ui.theme.Kennethyeung_COMP304Lab1_Ex1Theme



class ViewEditNoteActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        //Using the componentActivity onCreate on a savedInstanceState
        super.onCreate(savedInstanceState)
        //makes the app content extend behind the status and navigation bar
        enableEdgeToEdge()
        
        val noteIndex = intent.getIntExtra("noteIndex", -1)
        
        setContent {
            Kennethyeung_COMP304Lab1_Ex1Theme {
                ViewEditNoteScreen(
                    
                    noteIndex = noteIndex,
                    //callback function that closes the activity and returns to the previous screen
                    onNavigateBack = { finish() }
                )
            }
        }
    }
    
    //lifecycle function
    override fun onStart() {
        super.onStart()
    }
    //lifecycle function
    override fun onResume() {
        super.onResume()

    }

    //lifecycle function
    override fun onPause() {
        super.onPause()
    }

    //lifecycle function
    override fun onStop() {
        super.onStop()
    }
    //lifecycle function
    override fun onDestroy() {
        super.onDestroy()
    }
}
//Using experimental Matierial 3 features
@OptIn(ExperimentalMaterial3Api::class)
//Using a Jetpack Compose UI function
@Composable
//
fun ViewEditNoteScreen(
    noteIndex: Int,
    onNavigateBack: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(true) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var hasUnsavedChanges by remember { mutableStateOf(false) }
    

    LaunchedEffect(noteIndex) {
        if (noteIndex >= 0 && noteIndex < notes.size) {
            val note = notes[noteIndex]
            title = note.title
            content = note.content
            isLoading = false
        } else {
            onNavigateBack()
        }
        // Log.d("ViewEdit", "Loaded note at index $noteIndex")
    }

    fun saveChanges() {
        if ((title.isNotBlank() || content.isNotBlank()) && noteIndex >= 0 && noteIndex < notes.size) {
            val existingNote = notes[noteIndex]
            existingNote.title = title.ifBlank { "Untitled" }
            existingNote.content = content
            hasUnsavedChanges = false
        }
        onNavigateBack()
        // Could add a toast message here
    }

    fun deleteNote() {
        if (noteIndex >= 0 && noteIndex < notes.size) {
            notes.removeAt(noteIndex)
        }
        onNavigateBack()
    }

    fun handleBackNavigation() {
        // This could be improved with a dialog later
        onNavigateBack()
    }

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (hasUnsavedChanges) "Edit Note *" else "Edit Note",
                        style = MaterialTheme.typography.headlineSmall
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { handleBackNavigation() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { showDeleteDialog = true }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Note",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                    
                    TextButton(
                        onClick = { saveChanges() },
                        enabled = title.isNotBlank() || content.isNotBlank()
                    ) {
                        Text("Save")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { 
                    title = it
                    hasUnsavedChanges = true
                },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Enter note title...") },
                singleLine = true
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            OutlinedTextField(
                value = content,
                onValueChange = { 
                    content = it
                    hasUnsavedChanges = true
                },
                label = { Text("Content") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                placeholder = { Text("Start writing your note...") },
                minLines = 10
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Button(
                onClick = { saveChanges() },
                modifier = Modifier.fillMaxWidth(),
                enabled = title.isNotBlank() || content.isNotBlank()
            ) {
                Text("Save Changes")
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Button(
                onClick = { showDeleteDialog = true },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Delete Note")
            }
        }
    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = {
                Text("Delete Note")
            },
            text = {
                Text("Are you sure you want to delete this note? This action cannot be undone.")
            },
            confirmButton = {
                TextButton(
                    onClick = { 
                        showDeleteDialog = false
                        deleteNote()
                    }
                ) {
                    Text(
                        "Delete",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDeleteDialog = false }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ViewEditNoteScreenPreview() {
    Kennethyeung_COMP304Lab1_Ex1Theme {
        ViewEditNoteScreen(
            noteIndex = 0,
            onNavigateBack = {}
        )
    }
}
