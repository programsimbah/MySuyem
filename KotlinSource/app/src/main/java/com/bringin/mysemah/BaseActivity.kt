package com.bringin.mysemah

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.bringin.mysemah.models.Datas
import com.bringin.mysemah.ui.activitys.DisplayUhuy
import com.bringin.mysemah.ui.activitys.MainChild
import com.bringin.mysemah.ui.activitys.SplashActivity
import com.google.firebase.auth.FirebaseAuth
import com.pengembangsebelah.auth.Initializ
import com.pengembangsebelah.auth.SucessLoginListener
import com.pengembangsebelah.model.JSON
import com.pengembangsebelah.network.DetailApp
import com.pengembangsebelah.network.Result
import android.net.NetworkInfo
import android.content.Context.CONNECTIVITY_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.net.ConnectivityManager
import com.pengembangsebelah.network.Function


abstract class BaseActivity : AppCompatActivity() {
    private lateinit var listenerBase: SucessLoginListener
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        initializ.ResultActivity(requestCode,resultCode,data)
    }

    private lateinit var initializ: Initializ
    private lateinit var appDetai: DetailApp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Cheking()
    }

    public fun GetUse(){
        Function.Logins(FirebaseAuth.getInstance().currentUser!!.uid).execute()
    }

    public fun Cheking(){
        if(isNetworkAvailable()) {
            initIa()
        }else{
            var builder:AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("Tidak ada Internet")
            builder.setMessage("Aplikasi ini membutuhkan koneksi internet")
            builder.setPositiveButton("Coba lagi") { dialog, which ->
                dialog.dismiss()
            }
            builder.setOnDismissListener { Cheking() }
            builder.show()
        }

    }

    fun initIa(){
        initializ = Initializ()
        initializ.init(this)
        appDetai = DetailApp()
        appDetai.Ascyn(this)
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    public fun Login(listener: SucessLoginListener){
        this.listenerBase = listener
        initializ.signIn(listenerBase)
    }
    public fun SignOut(){
        initializ.signOut()
    }

    public fun UserNow(): FirebaseAuth? {
        return initializ.userNow()
    }

    companion object {
        var TITLEMENU = "menrfeds"
        lateinit var datas:JSON
    }

    public fun OpenDisply(contex: Activity, data: Datas.Display){
        val intent = Intent(this, DisplayUhuy::class.java)
        intent.putExtra(TITLEMENU,data.title)
        startActivity(intent)
    }
    public fun OpenDisplyE(contex: Activity, data: Datas.Display){
        val intent = Intent(this, MainChild::class.java)
        intent.putExtra(TITLEMENU,data.title)
        startActivity(intent)
    }
}