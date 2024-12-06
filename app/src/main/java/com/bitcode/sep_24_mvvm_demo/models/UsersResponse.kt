package com.bitcode.sep_24_mvvm_demo.models

import com.google.gson.annotations.SerializedName

data class UsersResponse(
    var page : Int,

    @SerializedName("per_page")
    var perPage : Int,

    var total : Int,

    @SerializedName("total_pages")
    var totalPages : Int,

    @SerializedName("data")
    var users : ArrayList<User>,

    var support : Support
){
    override fun toString(): String {
        return "Total = $total -- TotalPages = $totalPages"
    }
}

data class Support(
    var url : String,
    var text : String
)