package com.example.a487_project.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.a487_project.Classes.LookDB
import com.example.a487_project.util.Constants

@Database(
    entities = [LookDB::class],
    version = 2,
    exportSchema = false
)

abstract class LookDBRoomDatabase : RoomDatabase() {
    abstract fun lookDBDao(): LookDBDAO

    companion object{
        @Volatile  //it makes that instance to visible to other threads
        private var INSTANCE:LookDBRoomDatabase?=null

        fun getDatabase(context:Context):LookDBRoomDatabase{
            val tempInstance = INSTANCE
            if(tempInstance !=null){
                return  tempInstance
            }
            /*
            everthing in this block protected from concurrent execution by multiple threads.In this block database instance is created
            same database instance will be used. If many instance are used, it will be so expensive
             */
            synchronized(this){
                val  instance =Room.databaseBuilder(context.applicationContext, LookDBRoomDatabase::class.java, Constants.DATABASENAME).build()
                INSTANCE = instance
                return instance
            }
        }

    }


}
