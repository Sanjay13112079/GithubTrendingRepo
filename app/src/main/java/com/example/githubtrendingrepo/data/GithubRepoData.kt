package com.example.githubtrendingrepo.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


class GithubRepoData {

    @Parcelize
    data class GithubRepoListItem(
        @SerializedName("author") val author : String?,
        @SerializedName("name") val name : String?,
        @SerializedName("avatar") val avatar : String?,
        @SerializedName("description") val description : String?,
        @SerializedName("language") val language : String?,
        @SerializedName("languageColor") val languageColor : String?,
        @SerializedName("stars") val stars : Int?,
        @SerializedName("forks") val forks : Int?,
        @SerializedName("currentPeriodStars") val currentPeriodStars : Int?,
        @SerializedName("builtBy") val builtBy : List<BuiltByItem>?
    ) :Parcelable


    @Parcelize
    data class BuiltByItem(
        @SerializedName("username") val username : String?,
        @SerializedName("href") val href : String?,
        @SerializedName("avatar") val avatar : String?
    ) :Parcelable

}

