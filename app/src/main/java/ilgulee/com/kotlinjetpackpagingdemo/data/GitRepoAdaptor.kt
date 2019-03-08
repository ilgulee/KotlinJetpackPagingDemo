package ilgulee.com.kotlinjetpackpagingdemo.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ilgulee.com.kotlinjetpackpagingdemo.R
import ilgulee.com.kotlinjetpackpagingdemo.data.model.GitRepo
import kotlinx.android.synthetic.main.list_item.view.*

class GitRepoAdaptor : PagedListAdapter<GitRepo, GitRepoAdaptor.GitRepoViewHolder>(
    REPO_COMPARATOR
) {
    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<GitRepo>() {
            override fun areItemsTheSame(oldItem: GitRepo, newItem: GitRepo): Boolean =
                oldItem.fullName == newItem.fullName

            override fun areContentsTheSame(oldItem: GitRepo, newItem: GitRepo): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitRepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return GitRepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: GitRepoViewHolder, position: Int) {
        val repo = getItem(position)
        repo?.let { holder.setData(repo) }
    }

    inner class GitRepoViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun setData(repo: GitRepo) {
            view.repo_name.text = repo.fullName
            view.repo_description.text = repo.description
            view.repo_language.text = repo.language
            view.repo_stars.text = repo.stars.toString()
            view.repo_forks.text = repo.forks.toString()
        }

    }

}