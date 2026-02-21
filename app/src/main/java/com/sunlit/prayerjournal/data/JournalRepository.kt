package com.sunlit.prayerjournal.data

import com.sunlit.prayerjournal.data.local.JournalDao
import com.sunlit.prayerjournal.data.local.JournalEntryEntity
import kotlinx.coroutines.flow.Flow

class JournalRepository(private val dao: JournalDao) {
    val allEntries: Flow<List<JournalEntryEntity>> = dao.observeEntries()

    fun observeEntriesByCategory(category: JournalCategory): Flow<List<JournalEntryEntity>> =
        dao.observeEntriesByCategory(category)

    suspend fun addEntry(text: String, category: JournalCategory) {
        dao.insert(
            JournalEntryEntity(
                text = text,
                category = category,
                createdAtMillis = System.currentTimeMillis()
            )
        )
    }

    suspend fun removeEntry(entry: JournalEntryEntity) {
        dao.delete(entry)
    }
}

