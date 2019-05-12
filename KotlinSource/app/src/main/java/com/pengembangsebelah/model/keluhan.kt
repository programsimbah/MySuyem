package com.pengembangsebelah.model

import com.beust.klaxon.Json

class keluhan (
    @Json(name = "id")
    val id:String= ""
               ,@Json(name = "id_user") val id_user:String= ""
               ,@Json(name = "keluhan") val keluhan:String= ""
               ,@Json(name = "gambaran") val gambaran:String= ""
               ,@Json(name = "date") val date :String= ""
               ,@Json(name = "created_at") val created_at:String= ""
               ,@Json(name = "updated_at") val updated_at:String= ""
)