package com.example.contact_room_db


class Contact_Repository(private val contactDao:Contact_Dao) {
    suspend fun saveContact( contact:Contact ){
        contactDao.insertContact(contact)
    }

    val contact = contactDao.getAlleContact()

    suspend fun updateContact(contact:Contact){
        contactDao.updateContact(contact)
    }

    suspend fun deleteContact(contact:Contact){
        contactDao.deleteContact(contact)
    }

    suspend fun deleteAllContact(){
        contactDao.deleteAllContact()
    }
}