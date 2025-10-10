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
        // Using the componentActivity onCreate on a savedInstanceState
        super.onCreate(savedInstanceState)
        // makes the app content extend behind the status and navigation bar
        enableEdgeToEdge()
        
        val noteIndex = intent.getIntExtra("noteIndex", -1)
        
        setContent {
            Kennethyeung_COMP304Lab1_Ex1Theme {
                ViewEditNoteScreen(
                    noteIndex = noteIndex,
                    // callback function that closes the activity and returns to the previous screen
                    onNavigateBack = { finish() }
                )
            }
        }
    }
    
    // lifecycle function when the user starts the activity  
    override fun onStart() {
        super.onStart()
    }
    
    // lifecycle function when the activity is active to the user
    override fun onResume() {
        super.onResume()
    }

    // lifecycle function when the user leaves the activity
    override fun onPause() {
        super.onPause()
    }

    // lifecycle function when the activity is hidden from the user 
    override fun onStop() {
        super.onStop()
    }
    
    // lifecycle function when the activity is destroyed and the app is closed
    override fun onDestroy() {
        super.onDestroy()
    }
}

// Using experimental Material 3 features
@OptIn(ExperimentalMaterial3Api::class)
// Using a Jetpack Compose UI function
@Composable
// A UI function for the edit note screen 
// every time a user clicks the screen this function is ran again


fun ViewEditNoteScreen(
    // using a int variable called noteIndex
    noteIndex: Int,
    // using a function that returns void (Unit)
    onNavigateBack: () -> Unit
) {

    // everytime the component recomposes the title's state will be saved before continuing
    var title by remember { mutableStateOf("") }

    // everytime the component recomposes the content's state will be saved before continuing
    var content by remember { mutableStateOf("") }

    // everytime the component recomposes the isLoading's state will be saved before continuing
    var isLoading by remember { mutableStateOf(true) }

    // everytime the component recomposes the showDeleteDialog's state will be saved before continuing
    var showDeleteDialog by remember { mutableStateOf(false) }

    // everytime the component recomposes the asUnsavedChanges's state will be saved before continuing
    var hasUnsavedChanges by remember { mutableStateOf(false) }
    
    // when the noteIndex changes the launchedEffect function activates
    LaunchedEffect(noteIndex) {
        // if there is content in the note 
        // the note object exists outside of the function
        // update the note value
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

    // global update of the note object
    fun saveChanges() {
        if ((title.isNotBlank() || content.isNotBlank()) && noteIndex >= 0 && noteIndex < notes.size) {
            val existingNote = notes[noteIndex]
            existingNote.title = title.ifBlank { "Untitled" }
            existingNote.content = content
            hasUnsavedChanges = false
        }
        onNavigateBack()
    }

    // removes the note object at noteindex on a global scale
    fun deleteNote() {
        if (noteIndex >= 0 && noteIndex < notes.size) {
            notes.removeAt(noteIndex)
        }
        onNavigateBack()
    }

    // a function for a potential function to be executed when the user goes back in navigation
    fun handleBackNavigation() {
        // This could be improved with a dialog later
        onNavigateBack()
    }

    // if the activity is loading
    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    // the design of the ViewEditNoteActivity class
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
        //the properties of each button or textfield 
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // The textfield for the title field of the note
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
            
            //space between two text fields
            Spacer(modifier = Modifier.height(16.dp))
            
            // the textfield for the content field of the note
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
            
            //vertical space betweeen another textfield object
            Spacer(modifier = Modifier.height(16.dp))
            
            //the ui for the save changes button 
            Button(
                onClick = { saveChanges() },
                modifier = Modifier.fillMaxWidth(),
                enabled = title.isNotBlank() || content.isNotBlank()
            ) {
                Text("Save Changes")
            }
            
            //vertical space between a button and another button
            Spacer(modifier = Modifier.height(16.dp))
            
            //the ui for the delete note button
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

    //if the user presses the delete note button on the edit note screen it will show an alert
    if (showDeleteDialog) {

        //sets off an alert dialog
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = {
                Text("Delete Note")
            },
            text = {
                Text("Are you sure you want to delete this note? This action cannot be undone.")
            },
            //the confirm ui text button
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
            //the dismiss ui button
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

// it is a preview function
@Preview(showBackground = true)
// it is a ui composable function
// mockup screen does not run during development
@Composable
fun ViewEditNoteScreenPreview() {
    Kennethyeung_COMP304Lab1_Ex1Theme {
        ViewEditNoteScreen(
            noteIndex = 0,
            onNavigateBack = {}
        )
    }
}