package com.code4func.helloandroid.demosqlite

import android.content.ContentValues

class UserRepository (private val code4FuncDb: Code4FuncDb) {
    fun insert(user: User) {
        val database = code4FuncDb.writableDatabase

        ContentValues().apply {
            put(DbConfig.UserTable.COL_NAME, user.name)
            put(DbConfig.UserTable.COL_EMAIL, user.email)
        }.also {
            database.insert(DbConfig.UserTable.TABLE_NAME, null, it)
        }
    }

    fun selectAll() : List<User> {
        val database = code4FuncDb.readableDatabase

        val cursor = database.query(true, DbConfig.UserTable.TABLE_NAME,
                    arrayOf(
                        DbConfig.UserTable.COL_ID,
                        DbConfig.UserTable.COL_NAME,
                        DbConfig.UserTable.COL_EMAIL
                    ),
            null, null, null, null, null, null)

        cursor?.let {
             if (cursor.moveToFirst()) {
                 val result = mutableListOf<User>()
                 do {
                     val idIndex = cursor.getColumnIndex(DbConfig.UserTable.COL_ID)
                     val nameIndex = cursor.getColumnIndex(DbConfig.UserTable.COL_NAME)
                     val emailIndex = cursor.getColumnIndex(DbConfig.UserTable.COL_EMAIL)

                     result.add(User(
                         id = cursor.getInt(idIndex),
                         name = cursor.getString(nameIndex),
                         email = cursor.getString(emailIndex)
                     ))
                 }while (cursor.moveToNext())

                 return result
             }
        }

        return listOf()
    }

    fun update(user: User) {

    }

    fun delete(userId: Int) {

    }
}