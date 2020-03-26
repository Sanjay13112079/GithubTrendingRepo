package com.example.githubtrendingrepo.views.homepage

import android.view.View
import com.example.githubtrendingrepo.data.GithubDevelopersData
import com.example.imagesandvideo.recyclerview.GenericVH
import kotlinx.android.synthetic.main.github_developer_vh.view.*

class GithubDeveloperVH(itemView :View) :GenericVH<GithubDevelopersData.Data>(itemView) {

    var itemView1 :View

    init {
        itemView1=itemView
    }
    override fun bindData(data: GithubDevelopersData.Data?) {

        itemView1.developer_username.text="Username :"+data?.username
        itemView1.developer_name.text="Name :"+data?.name
        itemView1.developer_repo_name.text="Repo :"+data?.repo?.name
        itemView1.developer_repo_description.text="Description:"+data?.repo?.description
    }
}