package com.example.contact_room_db.Interface

import com.example.contact_room_db.Contact

interface OnItemClickListener {
    fun onItemClick(contact:Contact, position: Int)
}