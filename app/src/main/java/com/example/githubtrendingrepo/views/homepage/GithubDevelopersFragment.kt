package com.example.githubtrendingrepo.views.homepage

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
import com.example.githubtrendingrepo.data.GithubDevelopersData
import com.example.githubtrendingrepo.data.Resource
import com.example.githubtrendingrepo.data.Status
import com.example.githubtrendingrepo.viewmodel.GithubVM
import com.example.imagesandvideo.recyclerview.FeedItem
import com.example.imagesandvideo.recyclerview.GenericRVAdapter
import kotlinx.android.synthetic.main.fragment_github_developers.*
import kotlinx.android.synthetic.main.fragment_github_developers.loaderErrorView

class GithubDevelopersFragment :Fragment(), View.OnClickListener {


    lateinit var mViewModel : GithubVM
    lateinit var mDevelopersList :ArrayList<FeedItem<GithubDevelopersData.Data>>
    lateinit var mAdapter : GenericRVAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_github_developers,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        setUpGithubRepoObserver()
        mViewModel.fetchGithubDeveloeprs()

    }


    fun initViews()
    {
        mViewModel= ViewModelProvider(activity?.viewModelStore!!, ViewModelProvider.AndroidViewModelFactory.getInstance(activity?.application!!)).get(GithubVM::class.java)
        mDevelopersList=ArrayList()
        mAdapter= GenericRVAdapter(mDevelopersList,this)
        var layoutManger= LinearLayoutManager(this.activity)
        github_developers_rv.layoutManager=layoutManger
        github_developers_rv.adapter=mAdapter

        developer_searchView.setOnQueryTextListener(searchViewListener)
        developer_searchView.visibility=View.GONE
        loaderErrorView?.visibility=View.GONE
    }



    fun setUpGithubRepoObserver()
    {
        mViewModel.githubDeveloeprsData.observe(this, Observer<Resource<List<GithubDevelopersData.Data>>> {
                it ->
            when(it?.status)
            {
                Status.LOADING ->{
                    developer_searchView.visibility=View.GONE
                    loaderErrorView?.showLoading()
                    loaderErrorView?.visibility=View.VISIBLE


                }

                Status.ERROR ->{
                    developer_searchView.visibility=View.GONE
                    loaderErrorView?.showError(this)
                    loaderErrorView?.visibility=View.VISIBLE

                }

                Status.SUCCESS ->{
                    developer_searchView.visibility=View.VISIBLE
                    loaderErrorView?.hideLoading()
                    loaderErrorView?.visibility=View.GONE
                   mapDevelopers(it?.data)
                }
            }

        })

        mViewModel.fetchGithubRepos()
    }


    fun mapDevelopers(developerList : List<GithubDevelopersData.Data>?)
    {
        if(developerList==null || developerList.isEmpty()) return
        mDevelopersList.addAll(mViewModel.getDeveloeprsFeedItemList(developerList))
        mAdapter.notifyDataSetChanged()

    }

    override fun onClick(v: View?) {


        when(v?.id)
        {
            R.id.retry_btn ->
            {
                mViewModel.fetchGithubDeveloeprs()
            }
        }
    }


    var searchViewListener=object : SearchView.OnQueryTextListener{
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            mDevelopersList.clear()
            mDevelopersList.addAll(mViewModel.fiterDeveloperSearch(newText))
            mAdapter.notifyDataSetChanged()
            return true
        }
    }
}