package com.example.kotlin_android_project_5.MainActivity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_android_project_5.AddContactActivity.AddContactActivity
import com.example.kotlin_android_project_5.Adapter.ContactAdapter
import com.example.kotlin_android_project_5.DetailContactActivity.DetailContactActivity
import com.example.kotlin_android_project_5.EditContactActivity.EditContactActivity
import com.example.kotlin_android_project_5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var viewModel: MainActivityViewModel? = null
    private lateinit var binding: ActivityMainBinding
    private var contactAdapter: ContactAdapter? = null
    private var isSearch = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.initUI()
        this.initAction()
    }

    private fun initUI() {
        // init binding
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.supportActionBar?.hide()
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        this.setContentView(this.binding.root)
        this.binding.searchEt.isVisible = false
        // init view model
        this.viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(MainActivityViewModel::class.java)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initLayoutRecyclerView() {
        this.binding.rvContact.layoutManager = LinearLayoutManager(this@MainActivity)
        this.viewModel?.loadAllContacts(this@MainActivity)
        this.contactAdapter = this.viewModel?.contactLists?.value?.let { ArrayList(it) }?.let {
            ContactAdapter(it) { contact ->
                val intent = Intent(this@MainActivity, DetailContactActivity::class.java)
                intent.putExtra("contactModel", contact)
                this@MainActivity.startActivity(intent)
            }
        }
        this.binding.rvContact.adapter = this.contactAdapter
        this.contactAdapter?.notifyDataSetChanged()
    }

    override fun onStart() {
        super.onStart()
        this.initLayoutRecyclerView()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initAction() {
        this.binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AddContactActivity::class.java)
            this@MainActivity.startActivityForResult(intent, 1)
        }

        this.binding.searchBtn.setOnClickListener {
            this.isSearch = !this.isSearch
            this.binding.imageButton.isVisible = this.isSearch
            this.binding.contactTv.isVisible = this.isSearch
            this.binding.searchEt.isVisible = !this.isSearch
        }

        this.binding.searchEt.addTextChangedListener {
            this.binding.rvContact.layoutManager = LinearLayoutManager(this@MainActivity)
            this.viewModel?.getContact(this@MainActivity, this.binding.searchEt.text.toString())
            this.contactAdapter = this.viewModel?.contactLists?.value?.let { ArrayList(it) }?.let {
                ContactAdapter(it) { contact ->
                    val intent = Intent(this@MainActivity, DetailContactActivity::class.java)
                    intent.putExtra("contactModel", contact)
                    this@MainActivity.startActivity(intent)
                }
            }
            this.binding.rvContact.adapter = this.contactAdapter
            this.contactAdapter?.notifyDataSetChanged()
        }
    }
}
