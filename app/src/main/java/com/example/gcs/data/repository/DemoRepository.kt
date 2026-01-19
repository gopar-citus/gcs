package com.example.gcs.data.repository

import com.example.gcs.data.local.DemoItemDao
import com.example.gcs.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import java.io.IOException

class DemoRepository(
    private val apiService: ApiService,
    private val demoItemDao: DemoItemDao
) {

    val items = demoItemDao.getAllItems()

    suspend fun refreshItems() {
        try {
            val networkItems = apiService.getItems()
            demoItemDao.insertAll(networkItems)
        } catch (e: IOException) {
            // Handle network errors, maybe log them
            // For this demo, we'll just let the local data be served
            e.printStackTrace()
        }
    }
}
