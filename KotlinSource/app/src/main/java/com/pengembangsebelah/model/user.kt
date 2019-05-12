package com.pengembangsebelah.model

import com.beust.klaxon.Json

class user (
    @Json(name = "id")
    val id:String = "",
    @Json(name = "name")
    val name:String= "",
    @Json(name = "email")
    val email:String= "",
//    @Json(name = "password")
//            val password:String= "",
//    @Json(name = "remember_token")
//            val remember_token:String= "",
    @Json(name = "no_telp")
    val no_telp:String= "",
    @Json(name = "avatar")
    var avatar:String= "",
    @Json(name = "online")
    val online:String= "",
    @Json(name = "status")
    val status:String= "",
    @Json(name = "alamat")
    val alamat:String= "",
    @Json(name = "apikey")
    val apikey:String= "",
    @Json(name = "created_at")
    val created_at:String= "",
    @Json(name = "updated_at")
    val updated_at:String= "",
    @Json(name = "keluhan")
    val keluhan: ArrayList<keluhan>? = null,
    @Json(name = "obat")
    val obat: ArrayList<obat>?= null,
    @Json(name = "error")
    val error: String = "not"

            )