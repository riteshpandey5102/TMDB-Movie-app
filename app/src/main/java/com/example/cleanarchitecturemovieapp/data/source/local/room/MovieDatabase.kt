package com.example.cleanarchitecturemovieapp.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.cleanarchitecturemovieapp.data.source.local.entity.MovieEntity
import com.example.cleanarchitecturemovieapp.uttilities.Constants.DB_VERSION
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Database(
    entities = [MovieEntity::class],
    version = DB_VERSION,
    exportSchema = false
)

@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDAO
}

class Converters {
    @TypeConverter
    fun fromString(value: String?): List<Int>? {
        if (value == null) {
            return emptyList()
        }
        val listType = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toString(list: List<Int>?): String {
        return Gson().toJson(list)
    }
}