package com.example.words.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import org.intellij.lang.annotations.Language

@Entity(tableName = "words_table")
@Parcelize
data class Word(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val word: String,
    val meaning: String
): Parcelable
