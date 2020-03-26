package com.example.githubtrendingrepo.data

import com.google.gson.annotations.SerializedName



class GithubDevelopersData{

    data class Data(
        @SerializedName("username") val username : String?,
        @SerializedName("name") val name : String?,
        @SerializedName("url") val url : String?,
        @SerializedName("sponsorUrl") val sponsorUrl : String?,
        @SerializedName("avatar") val avatar : String?,
        @SerializedName("repo") val repo : Repo?
    )

    data class Repo(
        @SerializedName("name") val name : String?,
        @SerializedName("description") val description : String?,
        @SerializedName("url") val url : String?
    )


}
