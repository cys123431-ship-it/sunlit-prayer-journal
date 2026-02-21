package com.sunlit.prayerjournal.data

enum class JournalCategory(val displayName: String, val hint: String) {
    PRAYER("기도", "오늘의 기도 제목을 한 줄로 적어보세요."),
    GRATITUDE("감사", "오늘 고마웠던 순간을 한 줄로 적어보세요.")
}

data class JournalEntry(
    val id: Long,
    val text: String,
    val category: JournalCategory,
    val createdAtMillis: Long
) {
    val displayDate: String
        get() = java.text.SimpleDateFormat("MM/dd (E) HH:mm", java.util.Locale.getDefault())
            .format(createdAtMillis)
}
