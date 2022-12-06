package com.example.contact_room_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 1 )
abstract class Contact_Database: RoomDatabase(){
    abstract val contactDao:Contact_Dao

    companion object{
        @Volatile
        private var INSTANCE: Contact_Database? = null
        fun getInstance(context: Context):Contact_Database{
            var instance= INSTANCE
            synchronized(this ){
                if (instance == null){
                    instance = Room.databaseBuilder(context, Contact_Database::class.java, "Contact_DATABASE").build()
                    INSTANCE = instance
                }
            }
            return instance!!
        }
    }
}