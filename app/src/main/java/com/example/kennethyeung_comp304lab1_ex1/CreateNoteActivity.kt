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
    
    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNoteScreen(
    onNavigateBack: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    
    // Could add validation here later

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

    Scaffold(
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Enter note title...") },
                singleLine = true
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
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
            
            Spacer(modifier = Modifier.height(16.dp))
            
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

@Preview(showBackground = true)
@Composable
fun CreateNoteScreenPreview() {
    Kennethyeung_COMP304Lab1_Ex1Theme {
        CreateNoteScreen(onNavigateBack = {})
    }
}
