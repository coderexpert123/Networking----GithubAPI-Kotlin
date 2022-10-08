package com.singh.covid19tracker

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.net.IDN

interface GithubService {

    @GET ("users")
    suspend fun getUsers():Response<List<Uuser>>



    @GET("users/{id}")
    suspend fun getUserById(id: String?): Response<Uuser>


    @GET("search/user")
    suspend fun searchUser(@Query("q")query: String):Response<List<Uuser>>


}