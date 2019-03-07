package ilgulee.com.kotlinjetpackpagingdemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ilgulee.com.kotlinjetpackpagingdemo.data.network.GitRepoService
import ilgulee.com.kotlinjetpackpagingdemo.data.network.GitRepoServiceBuilder
import ilgulee.com.kotlinjetpackpagingdemo.data.network.response.GitRepoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = GitRepoServiceBuilder.buildService(GitRepoService::class.java)
        val call = service.getRepositories("android", 1, 10)
        call.enqueue(object : Callback<GitRepoResponse> {
            override fun onResponse(call: Call<GitRepoResponse>, response: Response<GitRepoResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.items

                    val size = responseItems?.let {
                        responseItems.size.toString()
                    }
                    Toast.makeText(this@MainActivity, size, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<GitRepoResponse>, t: Throwable) {

            }
        })
    }
}
