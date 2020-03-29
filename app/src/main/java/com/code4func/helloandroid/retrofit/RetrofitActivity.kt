package com.code4func.helloandroid.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Visibility
import android.view.View
import android.widget.Toast
import com.code4func.helloandroid.R
import kotlinx.android.synthetic.main.activity_retrofit.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class RetrofitActivity : AppCompatActivity(), CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private val gitHubService: GitHubService by lazy {
        GitHubService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        //https://api.github.com/users/{thanhniencung}/repos

        launch {
            val listRepos = withContext(Dispatchers.IO) {
                gitHubService.listRepos("thanhniencung")
            }

            withContext(Dispatchers.Main) {
                loading.visibility = View.GONE
                tvResult.visibility = View.VISIBLE

                tvResult.text = "${listRepos[0]?.name} -- ${listRepos[0]?.full_name}"
            }

        }
    }


    override fun onDestroy() {
        super.onDestroy()

        job.cancel()
    }
}
