package com.example.contact_room_db

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.contact_room_db.Interface.OnItemClickListener
import com.example.contact_room_db.databinding.LayoutCustomViewBinding

class ContactRecyclerViewAdapter(private val context: Context, private var contactList: List<Contact>, private val onItemClickListener: OnItemClickListener):Adapter<ContactRecyclerViewAdapter.ContactViewHolder>() {
    inner class ContactViewHolder(var layoutCustomViewBinding: LayoutCustomViewBinding): RecyclerView.ViewHolder(layoutCustomViewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        var layoutCustomViewBinding:LayoutCustomViewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.layout_custom_view, parent, false)
        return ContactViewHolder(layoutCustomViewBinding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        var contacts = contactList[position]
        val requestPermissionList = arrayOf(Manifest.permission.CALL_PHONE)
        val REQUEST_CODE = 1

        holder.layoutCustomViewBinding.tvFname.text = contacts.Fname
        holder.layoutCustomViewBinding.tvLname.text = contacts.Lname
        holder.layoutCustomViewBinding.tvNumber.text = contacts.Mobile_No
        val mobileNo:String = contacts.Mobile_No

        holder.layoutCustomViewBinding.btnDial.setOnClickListener{

            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$mobileNo"))
            context.startActivity(intent)
        }

        holder.layoutCustomViewBinding.btnCall.setOnClickListener{
            if(ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$mobileNo"))
                context.startActivity(intent)
            }
            else{
                ActivityCompat.requestPermissions(context as Activity, requestPermissionList, REQUEST_CODE)
            }

        }
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(contacts, position)
        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }
}