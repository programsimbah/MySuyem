package com.pengembangsebelah.model

import com.beust.klaxon.Json

class keluhan (
    @Json(name = "id")
    var id:String= ""
               ,@Json(name = "id_user") val id_user:String= ""
               ,@Json(name = "keluhan") var keluhan:String= ""
               ,@Json(name = "gambaran") var gambaran:String= ""
               ,@Json(name = "date") var date :String= ""
               ,@Json(name = "created_at") var created_at:String= ""
               ,@Json(name = "updated_at") var updated_at:String= ""
)