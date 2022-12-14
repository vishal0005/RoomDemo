package com.aav.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.room.Room
import com.aav.roomdemo.database.AppDatabase
import com.aav.roomdemo.database.User
import com.aav.roomdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG: String = "AAA"
    lateinit var binding: ActivityMainBinding
    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)


        db = Room.databaseBuilder(
            applicationContext, AppDatabase::class.java, "UserTable"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries().build()

        click()

    }

    private fun click() {
        binding.btnadduser.setOnClickListener {
            addRandomUsers()
        }

        binding.btnclearall.setOnClickListener {
            clearAllData()
            getAllUser()
        }
    }

    private fun clearAllData() {
        db.userDao().clearAllData()
    }

    private fun addRandomUsers() {
        var name = getRandomString(5)
        var lname = getRandomString(6)
        var user: User = User(0, name, lname)
        db.userDao().addUser(user)
        getAllUser()
    }

    private fun getAllUser() {
        var allUser = db.userDao().getAllUser()
        Log.e(TAG, "user count : ${allUser.size}")
        for (user in allUser) {
            Log.e(TAG, "user : ${user.uid} Name : ${user.firstName} LastName : ${user.lastName}")
        }
    }

    fun getRandomString(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z')
        return (1..length).map { allowedChars.random() }.joinToString("")
    }

}