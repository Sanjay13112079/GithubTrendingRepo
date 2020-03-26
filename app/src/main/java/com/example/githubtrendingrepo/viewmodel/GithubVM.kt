package com.example.githubtrendingrepo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubtrendingrepo.data.GithubDevelopersData
import com.example.githubtrendingrepo.data.GithubRepoData
import com.example.githubtrendingrepo.data.Resource
import com.example.githubtrendingrepo.repo.GithubAPIRepo
import com.example.imagesandvideo.recyclerview.FeedItem
import com.example.imagesandvideo.recyclerview.ItemTypeHandler

class GithubVM :ViewModel() {

    var githubRepoData : MutableLiveData<Resource<List<GithubRepoData.GithubRepoListItem>>>
    var githubDeveloeprsData : MutableLiveData<Resource<List<GithubDevelopersData.Data>>>

    var repo :GithubAPIRepo

    var repoFeedItemList :ArrayList<FeedItem<GithubRepoData.GithubRepoListItem>>
    var developerFeedItemList :ArrayList<FeedItem<GithubDevelopersData.Data>>


    init {
        repo=GithubAPIRepo()
        githubRepoData=repo.githubRepoData
        githubDeveloeprsData=repo.githubDeveloeprsData
        repoFeedItemList=ArrayList()
        developerFeedItemList= ArrayList()
    }


    fun fetchGithubRepos()
    {
        repo.getGithubRepos()
    }


    fun fetchGithubDeveloeprs()
    {
        repo.getGithubDevelopers()
    }



    fun getRepoFeedItemList(repoList :List<GithubRepoData.GithubRepoListItem>) :List<FeedItem<GithubRepoData.GithubRepoListItem>>
    {
        var list=ArrayList<FeedItem<GithubRepoData.GithubRepoListItem>>()
        for(item in repoList)
        {
            list.add(FeedItem(item,ItemTypeHandler.ItemViewType.GITHUB_REPO_VH))
        }
        repoFeedItemList=list
        return list
    }

    fun getDeveloeprsFeedItemList(repoList :List<GithubDevelopersData.Data>) :List<FeedItem<GithubDevelopersData.Data>>
    {
        var list=ArrayList<FeedItem<GithubDevelopersData.Data>>()
        for(item in repoList)
        {
            list.add(FeedItem(item,ItemTypeHandler.ItemViewType.GITHUB_DEVELOPER_VH))
        }
        developerFeedItemList=list

        return list
    }



    fun fiterRepSearch(query :String?) :List<FeedItem<GithubRepoData.GithubRepoListItem>>
    {
        if(query==null || query.equals(" ")) return repoFeedItemList

        var list=ArrayList<FeedItem<GithubRepoData.GithubRepoListItem>>()

        for(item in repoFeedItemList)
        {
            if((item.data!=null && item.data?.name!=null && item.data?.name?.contains(query,true)!!) ||
                (item.data!=null && item.data?.language!=null && item.data?.language?.contains(query,true)!!))
            {
                list.add(item)
            }
        }

        return list

    }


    fun fiterDeveloperSearch(query :String?) :List<FeedItem<GithubDevelopersData.Data>>
    {
        if(query==null || query.equals(" ")) return developerFeedItemList

        var list=ArrayList<FeedItem<GithubDevelopersData.Data>>()

        for(item in developerFeedItemList)
        {
            if((item.data!=null && item.data?.name!=null && item.data?.name?.contains(query,true)!!) ||
                (item.data!=null && item.data?.username!=null && item.data?.username?.contains(query,true)!!) ||
                (item.data!=null && item.data?.repo!=null && item.data?.repo?.name!=null && item.data?.repo?.name?.contains(query,true)!!))
            {
                list.add(item)
            }
        }

        return list

    }




}