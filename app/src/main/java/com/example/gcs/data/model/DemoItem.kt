package com.example.gcs.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "demo_items")
data class DemoItem(
    @PrimaryKey
    val id: Int,
    val title: String,
    val body: String
)
