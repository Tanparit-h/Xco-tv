package com.zicure.xcotv.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class FreeTVListModel(
    @SerializedName("free_tv")
    val freeTV: List<FreeTv>?
)

data class FreeTv(
    @SerializedName("type")
    val type: MediaListType,
    @SerializedName("name_list")
    val nameList: String,
    @SerializedName("media_list")
    val mediaList: List<Media>
)

data class Media(
    @SerializedName("name")
    val name: String,
    @SerializedName("sub_name")
    val subName: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("background")
    val background: String,
    @SerializedName("banner")
    val banner: String,
    @SerializedName("media_url")
    val mediaUrl: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("tag_list")
    val tagList: List<Tag>,
    @SerializedName("is_list")
    val isList: Boolean,
    @SerializedName("url")
    val url: String
)

class Tag(
    @SerializedName("tag")
    val tag: String,
    @SerializedName("name")
    val name: String
)

@Parcelize
enum class MediaListType : Parcelable {
    @SerializedName("SUGGEST_PROGRAM")
    SUGGEST_PROGRAM,

    @SerializedName("LIST_PROGRAM")
    LIST_PROGRAM,

    @SerializedName("LIST_BANNER")
    LIST_BANNER,
    NONE
}