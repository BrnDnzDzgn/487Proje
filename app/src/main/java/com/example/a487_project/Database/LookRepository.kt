package com.example.a487_project.Database


import androidx.lifecycle.LiveData
import com.example.a487_project.Classes.Look


/*
Used to access multiple data sources. It is used to seperate code and the architecture
 */
class LookRepository(private val lookDAO: LookDAO) {
    val readAlldata:LiveData<List<Look>> = lookDAO.getAllLooks()

    fun insertLook(look:Look){
        lookDAO.insertLook(look)
    }
    fun insertLooks(looks:ArrayList<Look>){
        lookDAO.insertAllLook(looks)
    }

    fun updateLook(look: Look){
        lookDAO.updateLook(look)
    }

    fun deleteLook(look: Look){
        lookDAO.deleteLook(look)
    }

    fun deleteAllLooks(){
        lookDAO.deleteAllLooks()
    }

    fun getAllLooks():LiveData<List<Look>>{
        return lookDAO.getAllLooks()
    }

    fun getCustomerById(id:Int):Look{
        return lookDAO.getLookById(id)
    }
    //fun getCustomersBySearchKey(searchKey:String): Flow<List<Customer>> {
     //  return customerDAO.getCustomersBySearchKey(searchKey)
    //}
}