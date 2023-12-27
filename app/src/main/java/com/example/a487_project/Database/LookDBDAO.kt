package com.example.a487_project.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.a487_project.Classes.LookDB
import com.example.a487_project.util.Constants



//Data Access Object: It contains all the methods used for accessing to the database. Inside it all the required queries will be created
@Dao
interface LookDBDAO {
    // The conflict strategy defines what happens,if there is an existing entry.
    // The default action is ABORT.
    //@Insert(onConflict = OnConflictStrategy.IGNORE) //if there is a conflict it will be ignored, if there is a new customer with the same data it will jut ignored
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLookDB(lookdb: LookDB) // suspend is written because it will be used with coroutine

    @Update
    fun updateLookDB(lookdb: LookDB)

    @Delete
    fun deleteLookDB(lookdb: LookDB)

    @Query("DELETE FROM ${Constants.TABLENAME}")
    fun deleteAllLookDBs()

    @Query("SELECT * FROM ${Constants.TABLENAME} ORDER BY id ASC")
    fun getAllLookDBs():LiveData<List<LookDB>>

    @Query("SELECT * FROM ${Constants.TABLENAME} WHERE id =:id")
    fun getLookDBById(id:Int):LookDB

    //@Query("SELECT * FROM ${Constants.TABLENAME} WHERE name LIKE :searchKey OR lastname LIKE :searchKey")
    //fun getLookBySearchKey(searchKey:String): Flow<List<LookDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllLookDB(looks: ArrayList<LookDB>){
        looks.forEach{
            insertLookDB(it)
        }
    }

}