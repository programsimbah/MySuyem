package com.bringin.mysemah.ui.activitys

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AlertDialog
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.EditText
import com.bringin.mysemah.BaseActivity
import com.bringin.mysemah.R
import com.bringin.mysemah.ui.adapters.AdapterList
import com.bringin.mysemah.ui.adapters.OnClickCustom
import com.pengembangsebelah.network.DetailApp
import com.pengembangsebelah.network.Function
import com.pengembangsebelah.network.Result
import kotlinx.android.synthetic.main.activity_list_catatan.*
import kotlinx.android.synthetic.main.dialog_login_splash.view.*
import kotlinx.android.synthetic.main.dialog_nambah_list_keluhan.*
import kotlinx.android.synthetic.main.dialog_nambah_list_keluhan.view.*
import kotlinx.android.synthetic.main.dialog_nambah_obat.view.*

class ListCatatanAct : AppCompatActivity(),SwipeRefreshLayout.OnRefreshListener {

    companion object {
        var code:Int = 1
        fun OpenActivity(co:Int,act:Activity) {
            code=co
            val intent = Intent(act, ListCatatanAct::class.java)
            act.startActivity(intent)
        }
    }

    override fun onRefresh() {
        class listess:Result{
            override fun Succes(message: String) {
                onRefresh()
            }

            override fun Failed(message: String) {
                onRefresh()
            }
        }
        val liep = listess()

        Log.d("YAYA"," dasd")
        swipePiew.isRefreshing = false
        if(code==1) {
                val lm = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                RecyclePiew.layoutManager = lm
                RecyclePiew.adapter = AdapterList(BaseActivity.datas,1,this,liep)
        }else{
            val lm = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            RecyclePiew.layoutManager = lm
            RecyclePiew.adapter = AdapterList(BaseActivity.datas,2,this,liep)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_catatan)
        button_kembali.setOnClickListener { finish() }
        if(code==1){
            title_activity_dds.text = "Catatan Keluahan Pasien"
            tambah_list.setOnClickListener { ShowDialKeluh("","",false) }
        }else if (code == 2){
            title_activity_dds.text = "Catatan Pemberian Obat"
            tambah_list.setOnClickListener { ShowDialObat() }
        }
        Log.d("YAYA"," dasd"+ code)
        swipePiew.setOnRefreshListener(this)
        onRefresh()
    }
    private lateinit var alertDialog :AlertDialog

    fun ShowDialKeluh(keluhan: String,gambaran:String,update:Boolean){
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_nambah_list_keluhan, null)
        dialogBuilder.setView(dialogView)
        val keluhanss = dialogView.keluhan_dial
        val gambaranss = dialogView.gambaran_dial
        val savpe = dialogView.save_up_dial
        val delti = dialogView.delet
        val canceling = dialogView.cancel_dial
        if(keluhan!=""){
            keluhanss.setText(keluhan)
        }

        if(gambaran!=""){
            gambaranss.setText(gambaran)
        }
        if(!update) {
            savpe.setOnClickListener {
                if (keluhanss.text==null) {
                    keluhanss.setError("keluhan kosong")
                } else if (gambaranss.text==null) {
                    gambaranss.setError("gambaran kosong")
                } else {
                    var ds = Obj(keluhanss.text.toString(),gambaranss.text.toString())
                    Save(1,ds)
                }
            }
            delti.visibility=View.GONE
        }else{
            savpe.setText("Update")
            delti.setOnClickListener {
                if (keluhan_dial.text == null) {
                    keluhan_dial.setError("keluhan kosong")
                } else if (gambaran_dial.text == null) {
                    gambaran_dial.setError("gambaran kosong")
                } else {

                 //   Update(1)
                }
            }
            delti.setOnClickListener {  }
        }

        canceling.setOnClickListener { alertDialog.dismiss() }
        alertDialog = dialogBuilder.create()
        alertDialog.setCancelable(false)
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.setOnDismissListener { }
        alertDialog.show()
    }
    fun ShowDialObat(){
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_nambah_obat, null)
        dialogBuilder.setView(dialogView)
        val namaOb = dialogView.name_obat
        val jdwalPemberian = dialogView.jadwal_pwmbwerian
        val kegunana = dialogView.kegunaan
        val dokter = dialogView.dokter
        val epekSamping = dialogView.epek_samping
        val savpe = dialogView.save_up_diald
        val delti = dialogView.deletd
        val canceling = dialogView.cancel_diald

            savpe.setOnClickListener {
                if (namaOb.text==null) {
                    namaOb.setError("field ini kosong")
                } else if (jdwalPemberian.text==null) {
                    jdwalPemberian.setError("field ini kosong")
                } else if (kegunana.text==null) {
                    kegunana.setError("field ini kosong")
                }else if (dokter.text==null) {
                    dokter.setError("field ini kosong")
                }else if (epekSamping.text==null) {
                    epekSamping.setError("field ini kosong")
                }

                else {
                    var ds = ObjObat(namaOb.text.toString(),jdwalPemberian.text.toString(),kegunana.text.toString(),dokter.text.toString(),epekSamping.text.toString())
                    Savee(ds)
                }
            }
            delti.visibility=View.GONE

        canceling.setOnClickListener { alertDialog.dismiss() }
        alertDialog = dialogBuilder.create()
        alertDialog.setCancelable(false)
        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.setOnDismissListener { }
        alertDialog.show()
    }

    class Obj(var keluhan:String,var gambaran: String)
    class ObjObat(var a:String,var b: String,var c: String,var d: String,var e: String)

    fun Save(Code:Int,g:Obj){
        if(Code==1){
            class liste:Result{
                override fun Succes(message: String) {
                    onRefresh()
                    alertDialog.dismiss()
                }

                override fun Failed(message: String) {
                    onRefresh()
                    alertDialog.dismiss()
                }
            }
            val lip = liste()
            Function.CreateKeluhan(BaseActivity.datas.user[0].apikey,g.keluhan,g.gambaran,lip).execute()
        }
    }
    fun Savee(g:ObjObat){
            class liste:Result{
                override fun Succes(message: String) {
                    onRefresh()
                    alertDialog.dismiss()
                }

                override fun Failed(message: String) {
                    onRefresh()
                    alertDialog.dismiss()
                }
            }
            val lip = liste()
            Function.CreateObat(BaseActivity.datas.user[0].apikey,g.a,g.b,g.c,g.d,g.e,lip).execute()
    }
}
