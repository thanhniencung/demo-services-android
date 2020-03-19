package com.code4func.helloandroid.contentprovider

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.code4func.helloandroid.R
import kotlinx.android.synthetic.main.activity_content_resolver.*
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext


class ContentResolverActivity : AppCompatActivity(), CoroutineScope {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_resolver)

        val isPermissionGrantedToReadContacts =
            ContextCompat.checkSelfPermission(applicationContext,
                Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED

        if (!isPermissionGrantedToReadContacts) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_CONTACTS),
                1)
            return
        }

        showContact()
    }

    private fun showContact() {

        launch {
            val contacts = getContactFromContentProviders()

            withContext(Dispatchers.Main) {
                val stringBuffer = StringBuffer()
                contacts.forEach{
                    stringBuffer.append("${it.contact_name} \n")
                }
                tvResult.text = stringBuffer.toString()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray) {

        when (requestCode) {
            1 -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    showContact()
                }
            }
        }
    }

    private suspend fun getContactFromContentProviders() = withContext(Dispatchers.IO) {

        val contacts: ArrayList<Contact> = arrayListOf()

        val projection = arrayOf(
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME
        )

        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI, projection,
            null, null, null
        )!!

        try {
            if (cursor.moveToFirst()) {
                val idIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID)
                val nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)

                do {
                    val contactId = cursor.getString(idIndex)
                    val contactDisplayName = cursor.getString(nameIndex)

                    contacts.add(Contact(contactId, contactDisplayName))
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {

        } finally {
            cursor.close()
        }
        contacts
    }

    data class Contact(val id: String, val contact_name: String)

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}
