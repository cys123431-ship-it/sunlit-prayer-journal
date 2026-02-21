package com.sunlit.prayerjournal.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sunlit.prayerjournal.data.JournalCategory

@Entity(tableName = "journal_entries")
data class JournalEntryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val text: String,
    val category: JournalCategory,
    val createdAtMillis: Long
)

