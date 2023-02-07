package com.devnuts.ruflu.data.database

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class OrmConverter {

    /** List<String> -> String 인코딩 **/
    @TypeConverter
    fun fromList(value: List<String>) = Json.encodeToString(value)

    /** String -> List<String> 디코딩 **/
    @TypeConverter
    fun toList(value: String) = Json.decodeFromString<List<String>>(value)
}