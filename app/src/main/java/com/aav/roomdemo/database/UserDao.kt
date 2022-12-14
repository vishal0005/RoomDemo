package com.aav.roomdemo.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Query("Select * from UserTable")
    fun getAllUser(): List<User>

//    @Query("Select * from UserTable where uid In (:uid)")
//    fun loadAllByIds(uid: IntArray): ArrayList<User>
//
//    @Query("select * from UserTable where first_name like :fname And last_name like :lname limit 1")
//    fun getUserByName(fname: String, lname: String)

    @Insert
    fun addUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("delete from usertable")
    fun clearAllData()

}