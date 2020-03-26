package com.example.githubtrendingrepo.repo

import androidx.lifecycle.MutableLiveData
import com.example.githubtrendingrepo.data.GithubDevelopersData
import com.example.githubtrendingrepo.data.GithubRepoData
import com.example.githubtrendingrepo.data.Resource
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GithubAPIRepo {


    var githubRepoData :MutableLiveData<Resource<List<GithubRepoData.GithubRepoListItem>>>
    var githubDeveloeprsData :MutableLiveData<Resource<List<GithubDevelopersData.Data>>>

    init {
        githubRepoData=MutableLiveData()
        githubDeveloeprsData=MutableLiveData()
    }

    var compositeDisposable=CompositeDisposable()

    fun getGithubRepos()
    {
        githubRepoData.value=Resource.loading()

        var client = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor()).build()

        var retrofit= Retrofit.Builder()
            .baseUrl("https://github-trending-api.now.sh/repositories/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync()) // using retrofit with rxJava
            .client(client).build()

        var apiInterface=retrofit.create(GithubAPIInterface::class.java)

        compositeDisposable.add(apiInterface.getGihubReposList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {  it ->
                    githubRepoData.value=Resource.success(it)
                }, {
                     githubRepoData.value=Resource.error()
                }
            )
        )


    }


    fun getGithubDevelopers()
    {
        githubDeveloeprsData.value=Resource.loading()
        var client = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor()).build()

        var retrofit= Retrofit.Builder()
            .baseUrl("https://github-trending-api.now.sh/developers/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync()) // using retrofit with rxJava
            .client(client).build()

        var apiInterface=retrofit.create(GithubAPIInterface::class.java)

        compositeDisposable.add(apiInterface.getGithubDeveloeprs()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {  it ->
                    githubDeveloeprsData.value=Resource.success(it)
                }, {
                    githubDeveloeprsData.value=Resource.error()
                }
            )
        )


    }
}