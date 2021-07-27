package com.peerbitskuldeep.roomdatabasedemo.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.ParcelField
import kotlinx.android.parcel.Parcelize

@Parcelize //for the update -> args
@Entity(tableName = "user_table")
data class User(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val name: String,

    val age: Int,

    val designation: String

) :Parcelable //edit the arguments
//go to my_nav -> and gives args -> currentUser
//https://www.youtube.com/watch?v=5rfBU75sguk&list=PLSrm9z4zp4mEPOfZNV9O-crOhoMa0G2-o&index=5&ab_channel=Stevdza-San
