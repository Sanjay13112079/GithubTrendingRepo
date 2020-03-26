package com.example.githubtrendingrepo.views.homepage

import android.view.View
import com.example.githubtrendingrepo.R
import com.example.githubtrendingrepo.data.GithubRepoData
import com.example.imagesandvideo.recyclerview.GenericVH
import kotlinx.android.synthetic.main.github_repo_vh.view.*

class GithubRepoVH( itemView :View):GenericVH<GithubRepoData.GithubRepoListItem>(itemView){

    var itemView1 :View

    init {
        itemView1=itemView
    }

    override fun bindData(data: GithubRepoData.GithubRepoListItem?) {

        itemView1.repo_name.text="Name : "+data?.name
        itemView1.repo_language.text="language : "+data?.language
        itemView1.repo_stars.text="xstars : "+data?.stars?.toString()

        itemView1.setTag(R.id.github_repo_click,data)
        itemView1.setOnClickListener(clickListner)

    }
}