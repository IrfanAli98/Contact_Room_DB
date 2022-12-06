package com.example.contact_room_db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class Contact_Factory(val repository:Contact_Repository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(Contact_ViewModel::class.java)){
            return Contact_ViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown Class")
    }

}