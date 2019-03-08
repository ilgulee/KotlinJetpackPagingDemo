package ilgulee.com.kotlinjetpackpagingdemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import ilgulee.com.kotlinjetpackpagingdemo.R
import ilgulee.com.kotlinjetpackpagingdemo.data.adaptor.GitRepoAdaptor
import ilgulee.com.kotlinjetpackpagingdemo.data.viewmodel.GitRepoViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = GitRepoAdaptor()
        recycler_view.layoutManager = LinearLayoutManager(this)

        val viewiModel = ViewModelProviders.of(this).get(GitRepoViewModel::class.java)
        viewiModel.gitRepoPagedList.observe(this, Observer {
            adapter.submitList(it)
        })
        recycler_view.adapter = adapter
    }
}
