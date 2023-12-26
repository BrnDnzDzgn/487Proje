package com.example.a487_project.Database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.a487_project.Classes.Look
import com.example.a487_project.util.Constants

/*
If you change anything on the database like adding a field to table, chnaging type of a filed, deleting a filed, changing the name of the field
exportSchema: to have a version of history of your schema in your caode base, it is not required so assigned as false
 */
@Database(
    entities = [Look::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class LookRoomDatabase : RoomDatabase() {
    abstract fun lookDao(): LookDAO

    companion object{
        @Volatile  //it makes that instance to visible to other threads
        private var INSTANCE:LookRoomDatabase?=null

        fun getDatabase(context:Context):LookRoomDatabase{
            val tempInstance = INSTANCE
            if(tempInstance !=null){
                return  tempInstance
            }
            /*
            everthing in this block protected from concurrent execution by multiple threads.In this block database instance is created
            same database instance will be used. If many instance are used, it will be so expensive
             */
            synchronized(this){
                val  instance =Room.databaseBuilder(context.applicationContext, LookRoomDatabase::class.java, Constants.DATABASENAME).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}
