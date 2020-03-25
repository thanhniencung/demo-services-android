package com.code4func.helloandroid.demosqlite

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.code4func.helloandroid.R
import com.code4func.helloandroid.sharepref.get
import com.code4func.helloandroid.sharepref.put
import kotlinx.android.synthetic.main.activity_demo_sqlite.*

class DemoSqliteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_sqlite)

        val code4FuncDb = Code4FuncDb(this)
        val userRepository = UserRepository(code4FuncDb)

        insert.setOnClickListener {
            userRepository.insert(
                User(name = "Ryan", email="ryan@gmail.com")
            )

            userRepository.insert(
                User(name = "Alice", email="alice@gmail.com")
            )
        }

        selectAll.setOnClickListener {
            userRepository.selectAll().forEach {
                Log.d("USER_DATA", it.toString())
            }
        }

        val sharedPref = getSharedPreferences(
            "com.code4func.helloandroid.pref",
            Context.MODE_PRIVATE)

        sharedPref.put("KEY", "this is string")
        Toast.makeText(this, sharedPref.get("KEY", ""), Toast.LENGTH_SHORT).show()


        /*with (sharedPref.edit()) {
            putLong("KEY_NAME", 10)
            commit()
        }

        val value = sharedPref.getInt("KEY_NAME_X", 0)
        Toast.makeText(this, "$value", Toast.LENGTH_SHORT).show()*/
    }
}
