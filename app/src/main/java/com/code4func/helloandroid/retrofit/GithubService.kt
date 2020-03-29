package com.code4func.helloandroid.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface GitHubService {
    @GET("users/{user}/repos")
    suspend fun listRepos(@Path("user") user: String?): List<Repo?>

    companion object {
        fun create() : GitHubService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(GitHubService::class.java)
        }
    }
}