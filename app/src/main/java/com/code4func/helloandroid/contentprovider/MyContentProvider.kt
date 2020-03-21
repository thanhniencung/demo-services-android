package com.code4func.helloandroid.contentprovider

import android.content.*
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import android.provider.BaseColumns

//https://developer.android.com/guide/topics/providers/content-provider-creating
private const val authority = "com.code4func.helloandroid"

private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
    //content://com.code4func.helloandroid/user
    addURI(authority, "user", 1)

    //content://com.code4func.helloandroid/user/545
    addURI(authority, "user/#", 2)
}

class MyContentProvider : ContentProvider() {
    private lateinit var dbHelper: DemoDbHelper

    override fun onCreate(): Boolean {
        // khởi tạo database muốn chia sẻ ở đây
        dbHelper = DemoDbHelper(context)
        return true
    }

    override fun getType(uri: Uri): String? {
        return when (uriMatcher.match(uri)) {
            1 -> ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + authority
            2 -> ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + authority
            else -> throw IllegalArgumentException("Unsupported URI: $uri")
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val db = dbHelper.writableDatabase

        val row: Long = db.insert(UserContract.UserEntry.TABLE_NAME, "", values)

        // If record is added successfully
        if (row > 0) {
            val newUri = ContentUris.withAppendedId(
                Uri.parse("content://$authority/user"), row)

            context.contentResolver.notifyChange(newUri, null)
            return newUri
        }
        throw SQLException("Fail to add a new record into $uri")
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {

        val sqlBuilder = SQLiteQueryBuilder()
        sqlBuilder.tables = UserContract.UserEntry.TABLE_NAME

        val c = sqlBuilder.query(
            dbHelper.readableDatabase,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )

        // --- register to watch a content URI for changes ---
        c.setNotificationUri(context.contentResolver, uri)
        return c
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("Implement this to handle requests to delete one or more rows")
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        TODO("Implement this to handle requests to update one or more rows.")
    }
}


// Database
object UserContract {
    object UserEntry : BaseColumns {
        const val TABLE_NAME = "user"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_EMAIL = "email"
    }
}
private const val SQL_CREATE_ENTRIES =
    "CREATE TABLE ${UserContract.UserEntry.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${UserContract.UserEntry.COLUMN_NAME_NAME} TEXT," +
            "${UserContract.UserEntry.COLUMN_NAME_EMAIL} TEXT)"

private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${UserContract.UserEntry.TABLE_NAME}"

class DemoDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "code4func.db"
    }
}
