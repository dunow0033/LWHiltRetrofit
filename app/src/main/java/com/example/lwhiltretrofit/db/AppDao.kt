package com.example.lwhiltretrofit.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lwhiltretrofit.model.RepositoryData

@Dao
interface AppDao {

    @Query("SELECT * FROM repositories")
    fun getAllRecords(): LiveData<List<RepositoryData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecords(repositoryData: RepositoryData)

    @Query("DELETE FROM repositories")
    fun deleteAllRecords()
}