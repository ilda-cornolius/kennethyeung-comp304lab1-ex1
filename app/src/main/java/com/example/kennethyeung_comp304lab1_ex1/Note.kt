package com.example.kennethyeung_comp304lab1_ex1

//data class for a single note 
data class Note(
    var title: String = "",
    var content: String = ""
) {
    //function to check if the title or content of the note is blank
    fun hasContent(): Boolean {
        return title.isNotBlank() || content.isNotBlank()
    }
    //Preview function for each note to be shown as a list in the main screen
    fun getPreview(maxLength: Int = 100): String {
        if (content.isEmpty()) return "No content"
        if (content.length <= maxLength) return content
        return "${content.substring(0, maxLength)}..."
    }
}