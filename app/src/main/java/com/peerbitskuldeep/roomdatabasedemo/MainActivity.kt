package com.peerbitskuldeep.roomdatabasedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(findNavController(R.id.navHostFrag))

    }

    override fun onSupportNavigateUp(): Boolean {

        val navController = findNavController(R.id.navHostFrag)

        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}