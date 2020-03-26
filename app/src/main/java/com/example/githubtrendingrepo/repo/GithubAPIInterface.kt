package com.example.githubtrendingrepo.repo

import com.example.githubtrendingrepo.data.GithubDevelopersData
import com.example.githubtrendingrepo.data.GithubRepoData
import io.reactivex.Observable
import retrofit2.http.GET

interface GithubAPIInterface {

    @GET("https://github-trending-api.now.sh/repositories/")
    fun getGihubReposList() :Observable<List<GithubRepoData.GithubRepoListItem>>


    @GET("https://github-trending-api.now.sh/developers/")
    fun getGithubDeveloeprs() :Observable<List<GithubDevelopersData.Data>>
}