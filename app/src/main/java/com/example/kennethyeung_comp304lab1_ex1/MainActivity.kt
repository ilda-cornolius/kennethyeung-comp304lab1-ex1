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



 //main entry point of the application
class MainActivity : ComponentActivity() {
    //onCreate function 
    override fun onCreate(savedInstanceState: Bundle?) {
        //the super class is the parent class of the activity (Component Activity)
        // the parent class (componentactivity)
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
    
    //place holder lifecycle functions onResume, onStart, onPause, onStop, onDestroy

    //when the activity is visible to the user the onStart function is called
    override fun onStart() {
        super.onStart()
    }

    // when the activity is now interactive to the user
    override fun onResume() {
        super.onResume()
    }

    // when the user leaves the activity
    override fun onPause() {
        super.onPause()
    }                   

    // when the activity is hidden from the user
    override fun onStop() {
        super.onStop()
    }
    //when the activity is destroyed and the app is closed
    override fun onDestroy() {
        super.onDestroy()
    }


// Navigation Helper Methods
    private fun navigateToCreateNote() {
        //creates a message to android saying that it wants to start the CreateNoteActivity
        val intent = Intent(this, CreateNoteActivity::class.java)
        //starts the CreateNoteActivity
        startActivity(intent)
    }

    private fun navigateToEditNote(noteIndex: Int) {
        //creates a message to android to open the ViewEditNoteActivity from the Current Activity
        val intent = Intent(this, ViewEditNoteActivity::class.java)
        //adds additional data to the intent, showing the note key (label) and the note value (note position number)
        intent.putExtra("noteIndex", noteIndex)
        //starts the ViewEditNoteActivity class
        startActivity(intent)
    }
}

//Creating the 3 note objects to show on the list
val notes = mutableStateListOf(
    Note("Welcome to QuickNotes", "This is your first note. Tap the + button to create more notes!"),
    Note("Sample Note 2", "This is a sample note to demonstrate the app functionality."),
    Note("Sample Note 3", "You can edit any note by tapping on it from the home screen.")
)

//tells kotlin that experimental matieral 3 features are being used
@OptIn(ExperimentalMaterial3Api::class)
//makes HomeScreen a jetpack compuse ui function
//the homescreen ui screen function
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

                    //a ui text box
                    Text(
                        text = "No notes yet",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    //a spacer
                    Spacer(modifier = Modifier.height(8.dp))

                    //a ui textbox
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
                items(notes) { note ->
                    val index = notes.indexOf(note)
                    NoteCard(
                        note = note,
                        onClick = { onNavigateToEdit(index) }
                    )
                }
            }
        }
    }
}


//makes HomeScreen a jetpack compuse ui function
//ui screen function for the notecard
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
           //text field for the title 
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            
            //adds a space between the text fields
            Spacer(modifier = Modifier.height(8.dp))
            
            //a text field for the content
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

//a preview function that shows how the HomeScreen Activity will look like without running the app
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Kennethyeung_COMP304Lab1_Ex1Theme {
        HomeScreen()
    }
}
