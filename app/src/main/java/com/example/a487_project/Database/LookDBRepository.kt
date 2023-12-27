package com.example.a487_project.Database

import androidx.lifecycle.LiveData
import com.example.a487_project.Classes.LookDB

class LookDBRepository(private val lookDBDAO: LookDBDAO) {
    val readAllData:LiveData<List<LookDB>> = lookDBDAO.getAllLookDBs()

    fun insertLookDB(lookDB:LookDB){
        lookDBDAO.insertLookDB(lookDB)
    }
    fun insertLookDBs(looks:ArrayList<LookDB>){
        lookDBDAO.insertAllLookDB(looks)
    }

    fun updateLookDB(lookDB: LookDB){
        lookDBDAO.updateLookDB(lookDB)
    }

    fun deleteLookDB(lookDB: LookDB){
        lookDBDAO.deleteLookDB(lookDB)
    }

    fun deleteAllLookDBs(){
        lookDBDAO.deleteAllLookDBs()
    }

    fun getAllLookDBs():LiveData<List<LookDB>>{
        return lookDBDAO.getAllLookDBs()
    }

    fun getCustomerById(id:Int):LookDB{
        return lookDBDAO.getLookDBById(id)
    }

    //fun getCustomersBySearchKey(searchKey:String): Flow<List<Customer>> {
    //  return customerDAO.getCustomersBySearchKey(searchKey)
    //}

}
