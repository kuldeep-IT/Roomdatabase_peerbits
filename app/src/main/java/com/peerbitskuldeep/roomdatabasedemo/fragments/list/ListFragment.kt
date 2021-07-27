package com.peerbitskuldeep.roomdatabasedemo.fragments.list

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.peerbitskuldeep.roomdatabasedemo.R
import com.peerbitskuldeep.roomdatabasedemo.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_list, container, false)



        val adapter = com.peerbitskuldeep.roomdatabasedemo.adapters.ListAdapter()
        val rv = view.recyclerView
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter =adapter

        //viewModel
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.readAllData.observe(viewLifecycleOwner, Observer {

            adapter.setData(it)

        })


        view.fb.setOnClickListener {

            findNavController().navigate(R.id.action_listFragment_to_addFragment)

        }

        //add menu
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_delete, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.delete_menu)
        {

            val builder = AlertDialog.Builder(requireContext())
                .apply {

                    setTitle("Clear All!")
                    setMessage("Are You sure to Delete all the Data?")
                    setPositiveButton("Yes", DialogInterface.OnClickListener(
                        {
                            dialog, which ->

                            userViewModel.deleteAll()
                            Toast.makeText(requireContext(),"Deleted All SuccessFully!", Toast.LENGTH_SHORT).show()
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