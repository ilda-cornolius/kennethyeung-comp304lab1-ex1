package com.example.kennethyeung_comp304lab1_ex1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kennethyeung_comp304lab1_ex1.ui.theme.Kennethyeung_COMP304Lab1_Ex1Theme

/**
 * CreateNoteActivity
 */
class CreateNoteActivity : ComponentActivity() {
    
    //when the CreateNoteA
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Kennethyeung_COMP304Lab1_Ex1Theme {
                CreateNoteScreen(
                    onNavigateBack = { finish() }
                )
            }
        }
    }
    
    //lifecycle methods

    //when the activity becomes visible the onStart method is called
    override fun onStart() {
        super.onStart()
    }

    //when the activity becomes interactive then the OnResume method is called
    override fun onResume() {
        super.onResume()
    }

    //when the activity loses focus the onPause method is called
    override fun onPause() {
        super.onPause()
    }

    //when the activity becomes hidden the onStop method is called
    override fun onStop() {
        super.onStop()
    }

    //when the activity is destroyed the onDestroy method activates
    override fun onDestroy() {
        super.onDestroy()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
//denotes that it is a android ui function
@Composable
//the create note screen function
fun CreateNoteScreen(
    onNavigateBack: () -> Unit
) {

    //on a ui refresh the title, content and focusmanger states are saved
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    
    //the save note function
    fun saveNote() {
        if (title.isNotBlank() || content.isNotBlank()) {
            val newNote = Note(
                title = title.ifBlank { "Untitled" },
                content = content
            )
            notes.add(newNote)
        }
        onNavigateBack()
        // Could add success feedback here
    }

    //the design of the create note screen
    Scaffold(
        //the design of the top bar
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "New Note",
                        style = MaterialTheme.typography.headlineSmall
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    TextButton(
                        onClick = { saveNote() },
                        enabled = title.isNotBlank() || content.isNotBlank()
                    ) {
                        Text("Save")
                    }
                }
            )
        }
    ) { paddingValues ->
        //the value of each column
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            //the title text field values
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Enter note title...") },
                singleLine = true
            )
            
            //a vertical space
            Spacer(modifier = Modifier.height(16.dp))
            
            //the content text field values
            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Content") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                placeholder = { Text("Start writing your note...") },
                minLines = 10
            )
            
            //a horizontal space in the ui
            Spacer(modifier = Modifier.height(16.dp))
            
            //a button in the ui
            Button(
                onClick = { saveNote() },
                modifier = Modifier.fillMaxWidth(),
                enabled = title.isNotBlank() || content.isNotBlank()
            ) {
                Text("Save Note")
            }
        }
    }
}

// preview function that shows how the CreateNoteActivity screen will look like without running the app
@Preview(showBackground = true)
@Composable
fun CreateNoteScreenPreview() {
    Kennethyeung_COMP304Lab1_Ex1Theme {
        CreateNoteScreen(onNavigateBack = {})
    }
}
