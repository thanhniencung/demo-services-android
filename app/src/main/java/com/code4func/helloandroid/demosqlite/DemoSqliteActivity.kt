package com.code4func.helloandroid.demosqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.code4func.helloandroid.R
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
    }
}
