package com.code4func.helloandroid.demosqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbConfig {
    companion object {
        const val DB_NAME = "code4func.db"
        const val DB_VERSION = 1
    }

    object UserTable {
        const val TABLE_NAME = "users"
        const val COL_ID = "_id"
        const val COL_NAME = "name"
        const val COL_EMAIL = "email"

        fun buildSchema() = """
            CREATE TABLE $TABLE_NAME (
                 $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                 $COL_NAME TEXT,
                 $COL_EMAIL TEXT
            ) 
        """

        fun dropTable() = "DROP TABLE IF EXISTS $TABLE_NAME"
    }
}

class Code4FuncDb(context: Context?) :
    SQLiteOpenHelper(context, DbConfig.DB_NAME, null, DbConfig.DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(DbConfig.UserTable.buildSchema())
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DbConfig.UserTable.dropTable())
        db?.execSQL(DbConfig.UserTable.buildSchema())
    }
}

