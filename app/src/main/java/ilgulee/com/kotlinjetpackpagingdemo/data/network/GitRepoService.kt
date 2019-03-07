package ilgulee.com.kotlinjetpackpagingdemo.data.network

import ilgulee.com.kotlinjetpackpagingdemo.data.network.response.GitRepoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GitRepoService {
    @GET("search/repositories?sortby=stars")
    fun getRepositories(
        @Query("q") topic: String,
        @Query("page") page: Int,
        @Query("per_page") size: Int
    ): Call<GitRepoResponse>
}