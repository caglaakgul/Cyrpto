package com.example.cryptocurrencypricetrackerapp.model.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.cryptocurrencypricetrackerapp.model.CoinListResponse
import com.example.cryptocurrencypricetrackerapp.model.data.dao.CoinListDao
import javax.inject.Inject
import javax.inject.Provider

//@Database(entities = [CoinListResponse::class], version = 1)
abstract class CoinListDatabase : RoomDatabase() {

  /*  abstract fun coinListDao(): CoinListDao

    class Callback @Inject constructor(
        private val database: Provider<CoinListDatabase>,
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().coinListDao()


        }
    }

   */
}