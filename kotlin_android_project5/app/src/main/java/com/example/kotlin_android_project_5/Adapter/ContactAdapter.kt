package com.example.kotlin_android_project_5.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_android_project_5.Model.ContactModel
import com.example.kotlin_android_project_5.databinding.ContactRowItemBinding

class ContactAdapter(
    private val contactList: ArrayList<ContactModel>,
    private val onItemClick: (ContactModel) -> Unit
) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    inner class ViewHolder(private val contactBinding: ContactRowItemBinding) : RecyclerView.ViewHolder(contactBinding.root) {
        fun onBind(contact: ContactModel, actionClick: (ContactModel) -> Unit) {
            contactBinding.tvName.text = contact.name
            contactBinding.root.setOnClickListener {
                actionClick(contact)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ContactRowItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.onBind(contact = this.contactList[position], actionClick = this.onItemClick)
    }

    override fun getItemCount() = contactList.size
}

