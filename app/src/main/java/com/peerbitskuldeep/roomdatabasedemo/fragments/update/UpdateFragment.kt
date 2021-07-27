package com.peerbitskuldeep.roomdatabasedemo.fragments.update

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.peerbitskuldeep.roomdatabasedemo.R
import com.peerbitskuldeep.roomdatabasedemo.room.User
import com.peerbitskuldeep.roomdatabasedemo.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_update, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.edtName_update.setText(args.currentUser.name.toString())
        view.edtAge_update.setText(args.currentUser.age.toString())
        view.edtDesignation_update.setText(args.currentUser.designation.toString())

        view.btnUpdate.setOnClickListener {

            updateData()

        }

        //add menu in fragment
        setHasOptionsMenu(true)

        return view
    }

    private fun updateData() {
        val name = edtName_update.text.toString()
        val age = edtAge_update.text
        val designation = edtDesignation_update.text.toString()

        if (checkValue(name, age!!, designation))
        {
            val user = User(args.currentUser.id, name, Integer.parseInt(age.toString()), designation)
            userViewModel.updateData(user)
            Toast.makeText(requireContext(),"Data Updated Successfully of $name", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        else{
            Toast.makeText(requireContext(),"Error in data of $name", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkValue(name: String, age: Editable, designation: String ): Boolean{

        return !(TextUtils.isEmpty(name) && age.isEmpty() && TextUtils.isEmpty(designation))

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_delete, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        if (item.itemId == R.id.delete_menu)
        {
            val builder = AlertDialog.Builder(requireContext()).apply {
                setTitle("Delete ${args.currentUser.name}")
                setMessage("Are you sure to DELETE data")
                setPositiveButton("Yes", DialogInterface.OnClickListener(
                    {
                        dialog, which ->

                        userViewModel.deleteData(args.currentUser)
                        Toast.makeText(requireContext(),"Data deleted successfully of ${args.currentUser.name}", Toast.LENGTH_SHORT).show()

                        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
                    }
                ))

                setNegativeButton("Cancel", DialogInterface.OnClickListener(
                    {
                        dialog, which ->

                        dialog.dismiss()
                    }
                ))
                }

            builder.create().show()
        }


        return super.onOptionsItemSelected(item)
    }
}