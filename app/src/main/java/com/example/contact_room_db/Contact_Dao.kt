package com.example.contact_room_db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface Contact_Dao {
    @Insert
    suspend fun insertContact(vararg contact: Contact)

    @Query("SELECT * From contact")
    fun getAlleContact(): LiveData<List<Contact>>

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Query("DELETE FROM contact")
    suspend fun deleteAllContact()
}