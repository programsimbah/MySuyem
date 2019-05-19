package com.bringin.mysemah.ui.activitys

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Switch
import com.bringin.mysemah.BaseActivity
import com.bringin.mysemah.R
import com.bringin.mysemah.models.Datas
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_child.*

class MainChild : BaseActivity() {
    companion object {
        val MERAWATPASIEN :Int = 1323
        val PENANGANANGEJALA :Int = 785
        val CATATAN :Int = 680
        var LEONARDO : Int = 1323


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_child)
        var hallokuy:String = intent.getStringExtra(BaseActivity.TITLEMENU)
        if(hallokuy=="Perawatan Pasien"){
            Init(MERAWATPASIEN)
        }else if(hallokuy=="Penanganan Gejala"){
            Init(PENANGANANGEJALA)
        }else{
            Init(CATATAN)
        }
        button_kembali.setOnClickListener { finish() }
    }

    fun Init(code:Int){
        if(code == MERAWATPASIEN){
            LEONARDO = MERAWATPASIEN
            title_activity_dd.text = "Merawat Pasien"

            btn11.text = "Kebersihan"
            btn11.setOnClickListener { Open(Datas.Display("Kebersihan")) }
            btn12.text = "Nutrisi / Zat Gizi"
            btn12.setOnClickListener { Open(Datas.Display("Nutrisi / Zat Gizi")) }
            btn13.text = "Minum Obat"
            btn13.setOnClickListener { Open(Datas.Display("Minum Obat")) }
            btn14.text = "Spiritual"
            btn14.setOnClickListener { Open(Datas.Display("Spiritual")) }
            btn15.text = "Kenyamanan"
            btn15.setOnClickListener { Open(Datas.Display("Kenyamanan")) }

            //Gej
            btn21.visibility = View.GONE
            btn22.visibility = View.GONE
            btn23.visibility = View.GONE
            btn24.visibility = View.GONE
            btn25.visibility = View.GONE

            //Cat
            btn31.visibility = View.GONE
            btn32.visibility = View.GONE
        }else if(code == PENANGANANGEJALA){
            LEONARDO = PENANGANANGEJALA
            title_activity_dd.text = "Penanganan Gejala"

            btn21.text = "Nyeri"
            btn21.setOnClickListener { Open(Datas.Display("Nyeri")) }
            btn22.text = "Mual Muntah"
            btn22.setOnClickListener { Open(Datas.Display("Mual Muntah")) }
            btn23.text = "Kelelahan"
            btn23.setOnClickListener { Open(Datas.Display("Kelelahan")) }
            btn24.text = "Penurunan Nafsu Makan"
            btn24.setOnClickListener { Open(Datas.Display("Penurunan Nafsu Makan")) }
            btn25.text = "Sesak Nafas"
            btn25.setOnClickListener { Open(Datas.Display("Sesak Nafas")) }
            //Mer
            btn11.visibility = View.GONE
            btn12.visibility = View.GONE
            btn13.visibility = View.GONE
            btn14.visibility = View.GONE
            btn15.visibility = View.GONE

            //Cat
            btn31.visibility = View.GONE
            btn32.visibility = View.GONE
        }else{
            LEONARDO = CATATAN
            title_activity_dd.text = "Catatan"
            btn31.text = "Keluhan Pasien"
            btn32.text = "Pemberian Obat"

            btn31.setOnClickListener { ListCatatanAct.OpenActivity(1,this) }
            btn32.setOnClickListener { ListCatatanAct.OpenActivity(2,this) }

            //Mer
            btn11.visibility = View.GONE
            btn12.visibility = View.GONE
            btn13.visibility = View.GONE
            btn14.visibility = View.GONE
            btn15.visibility = View.GONE

            //Gej
            btn21.visibility = View.GONE
            btn22.visibility = View.GONE
            btn23.visibility = View.GONE
            btn24.visibility = View.GONE
            btn25.visibility = View.GONE
        }
    }

    fun Open(data : Datas.Display){
        OpenDisply(this,data)
    }
}
