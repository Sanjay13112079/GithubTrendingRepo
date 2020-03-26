package com.example.githubtrendingrepo.views.repoDetailsPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubtrendingrepo.R
import com.example.githubtrendingrepo.data.GithubRepoData
import kotlinx.android.synthetic.main.activity_github_repo_details.*

class GithubRepoDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_github_repo_details)
        var data=intent?.extras?.getParcelable<GithubRepoData.GithubRepoListItem>("repoData")
        mapData(data)
    }


    fun mapData(repoData : GithubRepoData.GithubRepoListItem?)
    {
        if(repoData==null) return

        repo_details_name?.text="Name : "+repoData?.name
        repo_details_language?.text="Language : "+repoData?.language
        repo_details_stars?.text="Stars : "+repoData?.stars.toString()+"*"
        repo_details_description?.text="description : "+repoData?.description
        repo_details_avatar?.text="avatar : "+repoData?.avatar


    }


}
