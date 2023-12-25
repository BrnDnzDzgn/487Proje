package com.example.a487_project.Database


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.a487_project.Classes.Look
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
it provides data to the UI and survive configuration changes. It acts as a communication center between
repository and the UI
 */
class LookViewModel(application:Application):AndroidViewModel(application) {
    val readAllData: LiveData<List<Look>>
    private val repository:LookRepository
    init {
        val lookDAO= LookRoomDatabase.getDatabase(application).lookDao()
        repository = LookRepository(lookDAO)
        readAllData = repository.readAlldata
    }
    fun addLook(look:Look){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.insertLook(look)
        }
    }
    fun addLooks(looks: List<Look>){
        viewModelScope.launch(Dispatchers.IO) { // that code will be run in background thread, coroutine scope
            looks.forEach{
                repository.insertLook(it)
            }
        }
    }
    fun deleteLook(look:Look){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.deleteLook(look)
        }
    }
    fun deleteAllLook(){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.deleteAllLooks()
        }
    }
    fun updateLook(look:Look){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.updateLook(look)
        }
    }
    //fun searchCustomer(searchkey:String):LiveData<List<Look>>{
     //   return repository.getLooksBySearchKey(searchkey).asLiveData()
    //}
}