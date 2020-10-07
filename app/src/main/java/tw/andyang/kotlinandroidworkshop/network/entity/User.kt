package tw.andyang.kotlinandroidworkshop.network.entity

import com.google.gson.annotations.SerializedName

class User(
    @SerializedName("id") val id: Int,
    @SerializedName("email") var email: String,
    @SerializedName("first_name") var firstName: String,
    @SerializedName("last_name") var lastName: String,
    @SerializedName("avatar") var avatar: String
)