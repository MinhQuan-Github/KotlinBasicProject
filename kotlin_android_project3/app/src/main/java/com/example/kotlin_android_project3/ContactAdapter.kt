package com.example.kotlin_android_project3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(
    contactList: ArrayList<ContactModel>
) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    private var contactList: ArrayList<ContactModel>? = contactList


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            textView = view.findViewById(R.id.tv_name)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.contact_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = this.contactList?.get(position)?.getName() ?: ""
    }

    override fun getItemCount() = contactList?.size ?: 0
}