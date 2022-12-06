package com.example.contact_room_db

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contact_room_db.Interface.OnItemClickListener
import com.example.contact_room_db.databinding.ActivityMainBinding
import com.example.contact_room_db.databinding.CreateContactDialogueBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var factory: Contact_Factory
    private lateinit var viewModel: Contact_ViewModel
    private lateinit var adapter: ContactRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val contactDao = Contact_Database.getInstance(this).contactDao
        factory = Contact_Factory(Contact_Repository(contactDao))
        viewModel = ViewModelProvider(this, factory)[Contact_ViewModel::class.java]

        dataBinding.recyclerView.layoutManager  = LinearLayoutManager(this)
        viewModel.contact.observe(this, Observer {
            adapter= ContactRecyclerViewAdapter(this, it, object : OnItemClickListener{
                override fun onItemClick(contact: Contact, position: Int) {
                    val intent = Intent(this@MainActivity, Update_Contact::class.java)
                    intent.putExtra(Keys.CONTACT, Gson().toJson(contact))
                    startActivity(intent)
                }

            })
            dataBinding.recyclerView.adapter = adapter
        })

        dataBinding.addContact.setOnClickListener {


            val dialogBinding : CreateContactDialogueBinding = DataBindingUtil.inflate(LayoutInflater.from(this),R.layout.create_contact_dialogue, null, false)
            val dialog = Dialog(this)
            dialog.setContentView(dialogBinding.root)

            val manager = WindowManager.LayoutParams()
            manager.width = WindowManager.LayoutParams.MATCH_PARENT
            manager.height = WindowManager.LayoutParams.WRAP_CONTENT
            dialog.window!!.attributes = manager

            dialog.show()


            dialogBinding.btnCreate.setOnClickListener {

                val contact = Contact(0, dialogBinding.etFname.text.toString(), dialogBinding.etLname.text.toString(),
                dialogBinding.etNumber.text.toString())
                viewModel.saveContact(contact)
                dialog.dismiss()
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.delete_all_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.it_delete_all->{
                viewModel.deleteAllContact()

            }
        }
        return super.onOptionsItemSelected(item)
    }
}