package com.test.kerja.frengit.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @SerializedName("total_count")
    val total_count: Int? = null,
    @SerializedName("incomplete_results")
    val incomplete_results: Boolean? = null,
    @SerializedName("items")
    val items: List<ItemsItem?>? = null
) : Parcelable

@Parcelize
data class ItemsItem(

    @SerializedName("gists_url")
    val gists_url: String? = null,
    val repos_url: String? = null,
    val following_url: String? = null,
    val starred_url: String? = null,
    @SerializedName("login")
    val login: String,
    val followers_url: String? = null,
    val type: String? = null,
    val url: String? = null,
    val subscriptions_url: String? = null,
    val score: Double? = null,
    val received_events_url: String? = null,
    @SerializedName("avatar_url")
    val avatar_url: String? = null,
    val events_url: String? = null,
    val html_url: String? = null,
    val site_admin: Boolean? = null,
    @SerializedName("id")
    val id: Int? = null,
    val gravatar_id: String? = null,
    val node_id: String? = null,
    val organizations_url: String? = null
) : Parcelable
