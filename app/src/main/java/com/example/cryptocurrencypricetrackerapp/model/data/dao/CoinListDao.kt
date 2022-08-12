package com.example.cryptocurrencypricetrackerapp.model.data.dao

import androidx.room.*
import com.example.cryptocurrencypricetrackerapp.model.CoinListResponse
import io.reactivex.Single

@Dao
interface CoinListDao {
   /* @Query("SELECT * FROM coin_list_table WHERE name LIKE '%' || :searchQuery || '%'")
    fun getTasks(searchQuery: String): Single<List<CoinListResponse>>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: CoinListResponse)

    @Update
    suspend fun update(task: CoinListResponse)

    @Delete
    suspend fun delete(task: CoinListResponse) */
}