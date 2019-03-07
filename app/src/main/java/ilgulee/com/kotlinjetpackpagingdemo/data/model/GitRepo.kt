package ilgulee.com.kotlinjetpackpagingdemo.data.model

import com.google.gson.annotations.SerializedName

class GitRepo {

    @field:SerializedName("full_name")
    var fullName: String? = null

    @field:SerializedName("description")
    val description: String? = null

    @field:SerializedName("stargazers_count")
    val stars: Int = 0

    @field:SerializedName("forks_count")
    val forks: Int = 0

    @field:SerializedName("language")
    val language: String? = null
}