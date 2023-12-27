package com.example.a487_project.Database


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.a487_project.Classes.LookDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/*
it provides data to the UI and survive configuration changes. It acts as a communication center between
repository and the UI
 */

class LookDBViewModel(application:Application):AndroidViewModel(application) {
    val readAllData: LiveData<List<LookDB>>
    private val repository:LookDBRepository

    init {
        val lookDBDAO= LookDBRoomDatabase.getDatabase(application).lookDBDao()
        repository = LookDBRepository(lookDBDAO)
        readAllData = repository.readAllData
    }
    fun addLook(lookDB:LookDB){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.insertLookDB(lookDB)
        }
    }
    fun addLooks(looks: List<LookDB>){
        viewModelScope.launch(Dispatchers.IO) { // that code will be run in background thread, coroutine scope
            looks.forEach{
                repository.insertLookDB(it)
            }
        }
    }
    fun deleteLook(lookDB:LookDB){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.deleteLookDB(lookDB)
        }
    }
    fun deleteAllLook(){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.deleteAllLookDBs()
        }
    }
    fun updateLook(lookDB:LookDB){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.updateLookDB(lookDB)
        }
    }
    //fun searchCustomer(searchkey:String):LiveData<List<Look>>{
    //   return repository.getLooksBySearchKey(searchkey).asLiveData()
    //}


}

