package com.code4func.helloandroid.recyclerview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.code4func.helloandroid.R
import kotlinx.android.synthetic.main.activity_recycler.*

class RecyclerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        val dataSet = mutableListOf<Letter>()
        dataSet.add(Letter("A", Color.RED))
        dataSet.add(Letter("B", Color.BLUE))
        dataSet.add(Letter("C", Color.CYAN))
        dataSet.add(Letter("D", Color.YELLOW))
        dataSet.add(Letter("E", Color.GREEN))
        dataSet.add(Letter("F", Color.GRAY))
        dataSet.add(Letter("F", Color.GRAY))
        dataSet.add(Letter("F", Color.GRAY))
        dataSet.add(Letter("F", Color.GRAY))
        dataSet.add(Letter("F", Color.GRAY))

        // adapter
        val letterAdapter = LetterAdapter(dataSet) {
            Toast.makeText(applicationContext, it.name, Toast.LENGTH_SHORT).show()
        }

        val linearLayoutManager = LinearLayoutManager(this)

        //GridLayoutManager
        //StaggeredGridLayoutManager

        recyclerView.apply {
            //setItemViewCacheSize(dataSet.size)
            layoutManager = linearLayoutManager
            adapter = letterAdapter
        }
    }
}

