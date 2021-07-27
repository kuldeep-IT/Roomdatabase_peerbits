package com.peerbitskuldeep.roomdatabasedemo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.peerbitskuldeep.roomdatabasedemo.R
import com.peerbitskuldeep.roomdatabasedemo.fragments.list.ListFragmentDirections
import com.peerbitskuldeep.roomdatabasedemo.room.User
import kotlinx.android.synthetic.main.custom_rec.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.RecViewHolder>(){

    private var userList = emptyList<User>()

    inner class RecViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_rec, parent, false)
        return RecViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecViewHolder, position: Int) {

        holder.itemView.apply {

            id_rec.text = userList[position].id.toString()
            name_rec.text = userList[position].name.toString()
            age_rec.text = userList[position].age.toString()
            designation_rec.text = userList[position].designation.toString()

            //click on list and open update fragment
            //first parcelize User class
            //nav_host -> args
            //then below
            rootLayout.setOnClickListener {

                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(userList[position])
                holder.itemView.findNavController().navigate(action)

            }
        }

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(allUser: List<User>)
    {
        this.userList =allUser
        notifyDataSetChanged()
    }

}