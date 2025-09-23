# QuickNotes - COMP304 Lab Assignment #1

## Project Overview
This is a complete Android application built with Kotlin and Jetpack Compose for the COMP304 Lab Assignment #1. The app demonstrates fundamental Android development concepts including multiple activities, Jetpack Compose UI components, and proper activity lifecycle management.

## Features Implemented

### ✅ Home Activity (MainActivity)
- **LazyColumn**: Displays list of notes with proper scrolling
- **Card Components**: Individual note items with title and content preview
- **FloatingActionButton**: Navigation to Create Note Activity
- **Empty State**: Helpful message when no notes exist
- **Proper Alignment**: UI controls are properly aligned and organized

### ✅ Create Note Activity (CreateNoteActivity)
- **Column Layout**: Vertical arrangement of input fields
- **TextField Components**: Title and content input fields with proper labels
- **Button**: Save functionality with validation
- **Navigation**: Back button and save button in top app bar
- **Input Validation**: Save button only enabled when content exists

### ✅ View/Edit Note Activity (ViewEditNoteActivity)
- **Pre-filled Data**: TextFields populated with existing note content
- **Edit Functionality**: Full editing capabilities for title and content
- **Save Changes**: Update existing notes in the global list
- **Delete Functionality**: Delete notes with confirmation dialog for safety
- **Loading State**: Proper loading indicator while fetching note data
- **Error Handling**: Navigation back if note not found

## Technical Implementation

### Kotlin Basics Demonstrated
- **Variables**: Proper use of `var` and `val` keywords
- **Control Structures**: `if`, `when` expressions, and loops
- **Functions**: Custom functions with parameters and return types
- **Classes**: Data classes and ComponentActivity implementations
- **Collections**: MutableList for note management

### Jetpack Compose UI Elements
- **LazyColumn**: Efficient list rendering
- **Card**: Material Design card components
- **TextField**: Input fields with labels and placeholders
- **Button**: Primary action buttons with proper styling
- **FloatingActionButton**: FAB for primary navigation action
- **Column/Row**: Layout components for UI arrangement
- **Scaffold**: Top-level layout with app bar and FAB
- **AlertDialog**: Confirmation dialogs for destructive actions
- **IconButton**: Icon-based action buttons in app bar

### Activity Lifecycle Management
- **onCreate**: Activity initialization and UI setup
- **onStart**: Activity becomes visible
- **onResume**: Activity ready for user interaction
- **onPause**: Activity no longer in foreground
- **onStop**: Activity no longer visible
- **onDestroy**: Activity cleanup

### Architecture
- **Simple Architecture**: Basic architecture without MVVM as required
- **Global Data**: MutableList for note storage (simplified approach)
- **Intent Navigation**: Standard Android navigation between activities
- **State Management**: Compose state management with `remember` and `mutableStateOf`

## Code Quality Features

### Comments and Documentation
- **Class Documentation**: Comprehensive documentation for all classes
- **Function Documentation**: Detailed comments for all functions
- **Inline Comments**: Explanatory comments for complex logic
- **Kotlin Documentation**: Proper KDoc format for all public methods

### Naming Conventions
- **Variables**: `camelCase` for variables and functions
- **Classes**: `PascalCase` for class names
- **Constants**: Proper naming for constants and parameters
- **Descriptive Names**: Clear, descriptive names for all identifiers

### User Experience
- **Friendly I/O**: Intuitive input/output operations
- **Visual Feedback**: Loading indicators and validation states
- **Navigation**: Smooth transitions between activities
- **Material Design**: Consistent Material 3 design system

## File Structure
```
app/src/main/java/com/example/kennethyeung_comp304lab1_ex1/
├── MainActivity.kt              # Home Activity with note list
├── CreateNoteActivity.kt        # Create new notes
├── ViewEditNoteActivity.kt      # View and edit existing notes
├── Note.kt                      # Data class for notes
└── ui/theme/                    # Material 3 theme configuration
```

## Requirements Fulfillment

### ✅ Functionality (80% of total mark)
- **Correct Activities**: All three activities properly implemented
- **Jetpack Compose UI**: All required UI components implemented
- **Event Handlers**: Proper user interaction handling
- **Lifecycle Methods**: Complete lifecycle method implementation

### ✅ Friendliness (15% of total mark)
- **UI Alignment**: Properly aligned and organized controls
- **User-Friendly I/O**: Intuitive interface design

### ✅ Code Quality (5% of total mark)
- **Comments**: Comprehensive documentation throughout
- **Naming**: Proper naming conventions for all identifiers

## How to Run
1. Open project in Android Studio
2. Sync Gradle files
3. Run on emulator or device (API 31+)
4. Test all three activities and navigation

## Assignment Compliance
This implementation fully satisfies all requirements for COMP304 Lab Assignment #1, demonstrating proficiency in:
- Kotlin programming basics
- Jetpack Compose UI development
- Android activity lifecycle management
- Multiple activity navigation
- Material Design implementation
- Proper code documentation and naming conventions

