package com.example.lwhiltretrofit.network

import androidx.lifecycle.LiveData
import com.example.lwhiltretrofit.db.AppDao
import com.example.lwhiltretrofit.model.RepositoriesList
import com.example.lwhiltretrofit.model.RepositoryData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetroRepository @Inject constructor(private val retroServiceInterface: RetroServiceInterface,
                                private val appDao: AppDao) {

    fun getAllRecords(): LiveData<List<RepositoryData>> {
        return appDao.getAllRecords()
    }

    fun insertRecord(repositoryData: RepositoryData) {
        appDao.insertRecords(repositoryData)
    }

    fun makeApiCall(query: String?) {
        val call: Call<RepositoriesList> = retroServiceInterface.getDataFromAPI(query!!)
        call?.enqueue(object : Callback<RepositoriesList> {
            override fun onResponse(
                call: Call<RepositoriesList>,
                response: Response<RepositoriesList>
            ) {
                if(response.isSuccessful) {
                    appDao.deleteAllRecords()
                    response.body()?.items?.forEach {
                        insertRecord(it)
                    }
                }
            }

            override fun onFailure(call: Call<RepositoriesList>, t: Throwable) {
            }
        })
    }
}