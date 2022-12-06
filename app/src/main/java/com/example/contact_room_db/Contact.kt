package com.example.contact_room_db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "Id")
    var id: Int,

    @ColumnInfo(name = "FName")
    var Fname: String,

    @ColumnInfo(name = " LName")
    var Lname: String,

    @ColumnInfo(name= "MbNo")
    var Mobile_No: String
)