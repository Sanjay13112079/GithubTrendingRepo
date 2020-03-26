package com.example.githubtrendingrepo.views.homepage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubtrendingrepo.R
import com.example.githubtrendingrepo.data.GithubRepoData
import com.example.githubtrendingrepo.data.Resource
import com.example.githubtrendingrepo.data.Status
import com.example.githubtrendingrepo.viewmodel.GithubVM
import com.example.githubtrendingrepo.views.repoDetailsPage.GithubRepoDetailsActivity
import com.example.imagesandvideo.recyclerview.FeedItem
import com.example.imagesandvideo.recyclerview.GenericRVAdapter
import kotlinx.android.synthetic.main.fragment_github_repos.*

class GithubRepoFragment :Fragment(),View.OnClickListener {

    lateinit var mViewModel :GithubVM
    lateinit var mRepoList :ArrayList<FeedItem<GithubRepoData.GithubRepoListItem>>
    lateinit var mAdapter :GenericRVAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_github_repos,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        setUpGithubRepoObserver()
        mViewModel.fetchGithubRepos()
    }

    fun initViews()
    {
        mViewModel=ViewModelProvider(activity?.viewModelStore!!,ViewModelProvider.AndroidViewModelFactory.getInstance(activity?.application!!)).get(GithubVM::class.java)
        mRepoList=ArrayList()
        mAdapter= GenericRVAdapter(mRepoList,this)
        var layoutManger=LinearLayoutManager(this.activity)
        github_repo_rv.layoutManager=layoutManger
        github_repo_rv.adapter=mAdapter

        repos_searchView?.setOnQueryTextListener(searchViewListener)
        repos_searchView?.visibility=View.GONE
        loaderErrorView?.visibility=View.GONE

    }

    fun setUpGithubRepoObserver()
    {
        mViewModel.githubRepoData.observe(this, Observer<Resource<List<GithubRepoData.GithubRepoListItem>>> {
                it ->
            when(it?.status)
            {
                Status.LOADING ->{
                    repos_searchView?.visibility=View.GONE
                    loaderErrorView?.showLoading()
                    loaderErrorView?.visibility=View.VISIBLE
                }

                Status.ERROR ->{
                    repos_searchView?.visibility=View.GONE
                    loaderErrorView.showError(this)
                    loaderErrorView?.visibility=View.VISIBLE
                }

                Status.SUCCESS ->{
                    repos_searchView?.visibility=View.VISIBLE
                    loaderErrorView?.hideLoading()
                    loaderErrorView?.visibility=View.GONE
                   mapRepos(it?.data)
                }
            }

        })
    }


    fun mapRepos(repoList :List<GithubRepoData.GithubRepoListItem>?)
    {
        if(repoList==null || repoList.isEmpty()) return
        mRepoList.addAll(mViewModel.getRepoFeedItemList(repoList))
        mAdapter.notifyDataSetChanged()
    }

    override fun onClick(v: View?) {

        when(v?.id)
        {
            R.id.github_repo_vh_root ->
            {
                var data=v?.getTag(R.id.github_repo_click) as GithubRepoData.GithubRepoListItem

                var bundle=Bundle()
                bundle.putParcelable("repoData",data)
                var intent=Intent(activity!!, GithubRepoDetailsActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)

            }

            R.id.retry_btn ->
            {
                mViewModel.fetchGithubRepos()
            }
        }
    }


    var searchViewListener=object :SearchView.OnQueryTextListener{
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            mRepoList.clear()
            mRepoList.addAll(mViewModel.fiterRepSearch(newText))
            mAdapter.notifyDataSetChanged()
            return true
        }
    }
}