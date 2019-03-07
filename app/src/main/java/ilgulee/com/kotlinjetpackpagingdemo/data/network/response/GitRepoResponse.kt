package ilgulee.com.kotlinjetpackpagingdemo.data.network.response

import com.google.gson.annotations.SerializedName
import ilgulee.com.kotlinjetpackpagingdemo.data.model.GitRepo

class GitRepoResponse {
    @field:SerializedName("total_count")
    var totalCount: Int = 0

    var items: List<GitRepo>? = null
}