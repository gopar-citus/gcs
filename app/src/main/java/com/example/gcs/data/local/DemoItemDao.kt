package com.example.gcs.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gcs.data.model.DemoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface DemoItemDao {
    @Query("SELECT * FROM demo_items")
    fun getAllItems(): Flow<List<DemoItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<DemoItem>)
}
