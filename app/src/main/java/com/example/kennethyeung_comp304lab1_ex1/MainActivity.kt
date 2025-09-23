package com.example.kennethyeung_comp304lab1_ex1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kennethyeung_comp304lab1_ex1.ui.theme.Kennethyeung_COMP304Lab1_Ex1Theme

/**
 * MainActivity - Home Activity for QuickNotes App
 */
class MainActivity : ComponentActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Kennethyeung_COMP304Lab1_Ex1Theme {
                HomeScreen(
                    onNavigateToCreate = { navigateToCreateNote() },
                    onNavigateToEdit = { noteIndex -> navigateToEditNote(noteIndex) }
                )
            }
        }
    }
    
    // Only implementing the lifecycle methods that are actually needed
    override fun onResume() {
        super.onResume()
    }

    private fun navigateToCreateNote() {
        val intent = Intent(this, CreateNoteActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToEditNote(noteIndex: Int) {
        val intent = Intent(this, ViewEditNoteActivity::class.java)
        intent.putExtra("noteIndex", noteIndex)
        startActivity(intent)
        // TODO: Maybe add some animation here
    }
}

val notes: MutableList<Note> = mutableStateListOf(
    Note("Welcome to QuickNotes", "This is your first note. Tap the + button to create more notes!"),
    Note("Sample Note 2", "This is a sample note to demonstrate the app functionality."),
    Note("Sample Note 3", "You can edit any note by tapping on it from the home screen.")
    // TODO: Maybe load from database later
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToCreate: () -> Unit = {},
    onNavigateToEdit: (Int) -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "QuickNotes",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToCreate,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Note"
                )
            }
        }
    ) { paddingValues ->
        if (notes.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "No notes yet",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Tap the + button to create your first note",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(notes.size) { index ->
                    NoteCard(
                        note = notes[index],
                        onClick = { onNavigateToEdit(index) }
                    )
                }
            }
        }
    }
}

@Composable
fun NoteCard(
    note: Note,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = note.content.truncate(150),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

private fun String.truncate(maxLength: Int = 100): String {
    if (length <= maxLength) return this
    return "${substring(0, maxLength)}..."
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Kennethyeung_COMP304Lab1_Ex1Theme {
        HomeScreen()
    }
}
