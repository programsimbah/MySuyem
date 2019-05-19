package com.pengembangsebelah.model

import com.beust.klaxon.Json

class obat (
    @Json(name = "id")
    var id:String= "",
    @Json(name = "id_user")
            val id_user:String= "",
@Json(name = "nama_obat")
    var nama_obat:String= "",
@Json(name = "jadwal_pemberian")
    var jadwal_pemberian:String= "",
@Json(name = "kegunaan")
    var kegunaan:String= "",
@Json(name = "dokter")
    var dokter:String= "",
@Json(name = "efek_samping")
    var efek_samping:String= "",
@Json(name = "date")
    var date:String= "",
@Json(name = "created_at")
    var created_at:String= "",
    @Json(name = "updated_at") var updated_at:String= ""
)