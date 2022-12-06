package com.example.contact_room_db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class Contact_ViewModel(private val repository:Contact_Repository):ViewModel() {

    fun saveContact(contact: Contact){
        viewModelScope.launch {
            repository.saveContact(contact)
        }
    }
    val contact = repository.contact

    fun updateContact(contact: Contact){
        viewModelScope.launch {
            repository.updateContact(contact) }
    }

    fun deleteConact(contact: Contact){
        viewModelScope.launch {
            repository.deleteContact(contact)
        }
    }

    fun deleteAllContact(){
        viewModelScope.launch {
            repository.deleteAllContact()
        }
    }
}