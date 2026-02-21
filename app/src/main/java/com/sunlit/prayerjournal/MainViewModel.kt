package com.sunlit.prayerjournal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.sunlit.prayerjournal.data.JournalCategory
import com.sunlit.prayerjournal.data.JournalEntry
import com.sunlit.prayerjournal.data.JournalRepository
import com.sunlit.prayerjournal.data.local.JournalDatabase
import com.sunlit.prayerjournal.data.local.JournalEntryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.stateIn

data class MainUiState(
    val selectedCategory: JournalCategory = JournalCategory.PRAYER,
    val entries: List<JournalEntry> = emptyList(),
    val draftText: String = ""
)

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = JournalRepository(
        JournalDatabase.getInstance(application).journalDao()
    )

    private val selectedCategory = MutableStateFlow(JournalCategory.PRAYER)
    private val draftText = MutableStateFlow("")

    private val allEntries = repository.allEntries

    val uiState: StateFlow<MainUiState> = combine(
        selectedCategory,
        allEntries,
        draftText
    ) { category, entries, text ->
        MainUiState(
            selectedCategory = category,
            entries = entries
                .filter { it.category == category }
                .map { entity ->
                    JournalEntry(
                        id = entity.id,
                        text = entity.text,
                        category = entity.category,
                        createdAtMillis = entity.createdAtMillis
                    )
                },
            draftText = text
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MainUiState()
    )

    fun onCategoryChange(category: JournalCategory) {
        selectedCategory.value = category
        draftText.value = ""
    }

    fun onDraftTextChanged(value: String) {
        draftText.value = value.take(80)
    }

    fun saveEntry() {
        val text = draftText.value.trim()
        if (text.isBlank()) return

        viewModelScope.launch(Dispatchers.IO) {
            repository.addEntry(text = text, category = selectedCategory.value)
        }
        draftText.value = ""
    }

    fun deleteEntry(entry: JournalEntry) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeEntry(
                JournalEntryEntity(
                    id = entry.id,
                    text = entry.text,
                    category = entry.category,
                    createdAtMillis = entry.createdAtMillis
                )
            )
        }
    }
}
