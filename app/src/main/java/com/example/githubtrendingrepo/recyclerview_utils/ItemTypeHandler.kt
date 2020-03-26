package com.example.imagesandvideo.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.githubtrendingrepo.R
import com.example.githubtrendingrepo.views.homepage.GithubDeveloperVH
import com.example.githubtrendingrepo.views.homepage.GithubRepoVH

class ItemTypeHandler {

    //using enum to classify viewholder types
     enum class ItemViewType(id :Int)
     {
         GITHUB_REPO_VH(0), GITHUB_DEVELOPER_VH(1);

         var id :Int?=null

         init {
             this.id=id
         }
     }

     companion object
     {

         fun getType(id :Int) :Int?
         {
             var itemtypeArray=
                 ItemViewType.values()
             for(itemType in itemtypeArray)
             {
                 if(itemType?.id==id) return itemType.id
             }

             return null
         }

     //create view holder for recycler view
     fun createViewHolder (inflater :LayoutInflater,parent :ViewGroup,type :Int? ): GenericVH<Any>?
     {

             if(type==null) return null
             var viewHolder : GenericVH<Any>?=null

             when(type)
             {
                 ItemViewType.GITHUB_REPO_VH.id ->
                 {
                     viewHolder= GithubRepoVH(
                         inflater.inflate(R.layout.github_repo_vh, parent, false)
                     ) as GenericVH<Any>
                 }

                 ItemViewType.GITHUB_DEVELOPER_VH.id ->
                 {
                     viewHolder= GithubDeveloperVH(
                         inflater.inflate(R.layout.github_developer_vh, parent, false)
                     ) as GenericVH<Any>
                 }

             }

             return viewHolder
         }
     }





}