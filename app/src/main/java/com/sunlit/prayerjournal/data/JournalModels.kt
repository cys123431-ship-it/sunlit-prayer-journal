package com.sunlit.prayerjournal.data

enum class JournalCategory(val displayName: String, val hint: String) {
    PRAYER("기도", "예) 오늘 하루를 지켜주셔서 감사합니다."),
    GRATITUDE("감사", "예) 오늘 동료가 도와줘서 고마웠어요.")
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

