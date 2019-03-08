package ilgulee.com.kotlinjetpackpagingdemo.data.adaptor

import androidx.paging.PageKeyedDataSource
import ilgulee.com.kotlinjetpackpagingdemo.data.model.GitRepo
import ilgulee.com.kotlinjetpackpagingdemo.data.network.GitRepoService
import ilgulee.com.kotlinjetpackpagingdemo.data.network.GitRepoServiceBuilder
import ilgulee.com.kotlinjetpackpagingdemo.data.network.response.GitRepoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/***
 * 3 data source you can use depending on your use case
 * PagedKeyedDataSource
 * ItemKeyedDataSource
 * PositionalDataSource
 */
class GitRepoDataSource : PageKeyedDataSource<Int, GitRepo>() {

    companion object {
        const val PAGE_SIZE = 10
        const val FIRST_PAGE = 1
        const val TOPIC = "android"
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, GitRepo>) {
        val service = GitRepoServiceBuilder.buildService(GitRepoService::class.java)
        val call = service.getRepositories(
            TOPIC,
            FIRST_PAGE,
            PAGE_SIZE
        )
        call.enqueue(object : Callback<GitRepoResponse> {
            override fun onResponse(call: Call<GitRepoResponse>, response: Response<GitRepoResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.items
                    responseItems?.let {
                        callback.onResult(it, null, FIRST_PAGE + 1)
                    }
                }
            }

            override fun onFailure(call: Call<GitRepoResponse>, t: Throwable) {
                //to fallback to database or connection error
            }
        })

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GitRepo>) {
        val service = GitRepoServiceBuilder.buildService(GitRepoService::class.java)
        val call = service.getRepositories(
            TOPIC,
            FIRST_PAGE,
            PAGE_SIZE
        )
        call.enqueue(object : Callback<GitRepoResponse> {
            override fun onResponse(call: Call<GitRepoResponse>, response: Response<GitRepoResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.items
                    val key = if (apiResponse.totalCount > params.key) params.key + 1 else apiResponse.totalCount
                    responseItems?.let {
                        callback.onResult(it, key)
                    }
                }
            }

            override fun onFailure(call: Call<GitRepoResponse>, t: Throwable) {
                //to fallback to database or connection error
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GitRepo>) {
        val service = GitRepoServiceBuilder.buildService(GitRepoService::class.java)
        val call = service.getRepositories(
            TOPIC,
            FIRST_PAGE,
            PAGE_SIZE
        )
        call.enqueue(object : Callback<GitRepoResponse> {
            override fun onResponse(call: Call<GitRepoResponse>, response: Response<GitRepoResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.items
                    val key = if (apiResponse.totalCount > 1) params.key - 1 else 0
                    responseItems?.let {
                        callback.onResult(it, key)
                    }
                }
            }

            override fun onFailure(call: Call<GitRepoResponse>, t: Throwable) {
                //to fallback to database or connection error
            }
        })
    }

}
