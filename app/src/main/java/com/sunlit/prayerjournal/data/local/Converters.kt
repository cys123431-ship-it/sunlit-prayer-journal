package com.sunlit.prayerjournal.data.local

import androidx.room.TypeConverter
import com.sunlit.prayerjournal.data.JournalCategory

class Converters {
    @TypeConverter
    fun fromCategory(category: JournalCategory): String = category.name

    @TypeConverter
    fun toCategory(value: String): JournalCategory = JournalCategory.valueOf(value)
}

