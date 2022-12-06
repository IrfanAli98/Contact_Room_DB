package com.example.contact_room_db

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.contact_room_db.databinding.ActivityUpdateContactBinding
import com.google.gson.Gson

class Update_Contact : AppCompatActivity() {
    private lateinit var dataBinding: ActivityUpdateContactBinding
    private lateinit var contact: Contact
    private lateinit var factory: Contact_Factory
    private lateinit var viewModel:Contact_ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding= DataBindingUtil.setContentView(this, R.layout.activity_update_contact)

        val contactDao = Contact_Database.getInstance(this).contactDao
        factory = Contact_Factory(Contact_Repository(contactDao))
        viewModel = ViewModelProvider(this, factory)[Contact_ViewModel::class.java]

        val intentData = intent.getStringExtra(Keys.CONTACT)
        contact = Gson().fromJson(intentData, Contact::class.java)

        dataBinding.etEdFname.setText(contact.Fname)
        dataBinding.etEdLname.setText(contact.Lname)
        dataBinding.etEdNumber.setText(contact.Mobile_No)

        dataBinding.btnUpdate.setOnClickListener {

            viewModel.updateContact(Contact(contact.id,
                dataBinding.etEdFname.text.toString(), dataBinding.etEdLname.text.toString(),
                dataBinding.etEdNumber.text.toString()))
            finish()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_contact_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.ed_edit -> {
                dataBinding.etEdFname.isFocusableInTouchMode = true
                dataBinding.etEdLname.isFocusableInTouchMode = true
                dataBinding.etEdNumber.visibility = View.VISIBLE

            }
            R.id.ed_delete -> {
                viewModel.deleteConact(contact)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}