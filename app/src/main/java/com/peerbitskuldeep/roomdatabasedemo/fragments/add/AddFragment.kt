package com.peerbitskuldeep.roomdatabasedemo.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.peerbitskuldeep.roomdatabasedemo.R
import com.peerbitskuldeep.roomdatabasedemo.room.User
import com.peerbitskuldeep.roomdatabasedemo.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {

    lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_add, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.btnAdd.setOnClickListener{

            insertDataToDatabase()

        }

        return view
    }

    private fun insertDataToDatabase() {

        val name = edtName.text.toString()
        val age = edtAge.text
        val designation = edtDesignation.text.toString()

        if (checkValue(name, age!!,designation))
        {
            val user = User(0, name, Integer.parseInt(age.toString()), designation)

            userViewModel.addUser(user)

            Toast.makeText(requireContext(),"Data added successful!", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        else
        {
            Toast.makeText(requireContext(),"Error!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun checkValue(name: String, age: Editable, designation: String ): Boolean{

        return !(TextUtils.isEmpty(name) && age.isEmpty() && TextUtils.isEmpty(designation))

    }


}