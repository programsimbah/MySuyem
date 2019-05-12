package com.pengembangsebelah.model

import com.beust.klaxon.Json

class obat (
    @Json(name = "id")
    val id:String= "",
    @Json(name = "id_user")
            val id_user:String= "",
@Json(name = "nama_obat")
            val nama_obat:String= "",
@Json(name = "jadwal_pemberian")
            val jadwal_pemberian:String= "",
@Json(name = "kegunaan")
            val kegunaan:String= "",
@Json(name = "dokter")
            val dokter:String= "",
@Json(name = "efek_samping")
            val efek_samping:String= "",
@Json(name = "date")
            val date:String= "",
@Json(name = "created_at")
            val created_at:String= "",
    @Json(name = "updated_at") val updated_at:String= ""
)