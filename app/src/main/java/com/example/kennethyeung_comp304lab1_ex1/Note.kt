package com.example.kennethyeung_comp304lab1_ex1

data class Note(
    var title: String = "",
    var content: String = ""
) {
    fun hasContent(): Boolean {
        return title.isNotBlank() || content.isNotBlank()
    }
    
    fun getPreview(maxLength: Int = 100): String {
        if (content.isEmpty()) return "No content"
        if (content.length <= maxLength) return content
        return "${content.substring(0, maxLength)}..."
    }
}